# Dockerfile
# Frontend build stage
FROM node:16 AS frontend-build

WORKDIR /app/webapp
COPY src/main/webapp/package.json src/main/webapp/package-lock.json ./
RUN npm install
COPY src/main/webapp/ ./
RUN npm run build

# Backend build stage
FROM openjdk:21-jdk-slim AS backend-build

WORKDIR /app/backend
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw ./
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Final stage
FROM openjdk:21-jdk-slim

# Install nginx
RUN apt-get update && \
    apt-get install -y nginx && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy backend jar
COPY --from=backend-build /app/backend/target/*.jar app.jar

# Remove default nginx config and copy frontend build
RUN rm -rf /etc/nginx/sites-enabled/default
COPY --from=frontend-build /app/webapp/build /var/www/html

# Configure nginx
# In your Dockerfile, update the nginx configuration
RUN echo 'server { \n\
    listen 80; \n\
    server_name localhost; \n\
    \n\
    root /var/www/html; \n\
    index index.html; \n\
    \n\
    location / { \n\
        try_files $uri $uri/ /index.html; \n\
        add_header Cache-Control "no-cache"; \n\
    } \n\
    \n\
    location /api { \n\
        proxy_pass http://localhost:8080; \n\
        proxy_set_header Host $host; \n\
        proxy_set_header X-Real-IP $remote_addr; \n\
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for; \n\
        proxy_set_header X-Forwarded-Proto $scheme; \n\
    } \n\
    \n\
    location /actuator { \n\
        proxy_pass http://localhost:8080; \n\
        proxy_set_header Host $host; \n\
        proxy_set_header X-Real-IP $remote_addr; \n\
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for; \n\
        proxy_set_header X-Forwarded-Proto $scheme; \n\
    } \n\
}' > /etc/nginx/conf.d/default.conf

# Create startup script
RUN echo '#!/bin/bash\n\
service nginx start\n\
java -jar /app/app.jar & prometheus --config.file=/etc/prometheus/prometheus.yml & grafana-server"' > /app/start.sh

RUN chmod +x /app/start.sh

# Expose ports
EXPOSE 80 8080
EXPOSE 3000
EXPOSE 9090
EXPOSE 3001

CMD ["/app/start.sh"]