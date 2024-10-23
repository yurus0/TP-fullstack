# Use Node.js for the build stage
FROM node:16 AS frontend-build

WORKDIR /app/webapp
COPY src/main/webapp/package.json src/main/webapp/package-lock.json ./
RUN npm install
COPY src/main/webapp/ ./
RUN npm run build

# Use Nginx to serve the React app
FROM nginx:alpine AS frontend

COPY --from=frontend-build /app/webapp/build /usr/share/nginx/html
EXPOSE 80

# Use OpenJDK for the backend stage
FROM openjdk:21-jdk-slim AS backend-build

WORKDIR /app/backend
COPY src/main/java/pom.xml ./
COPY src/main/java/src ./src
RUN ./mvnw clean package -DskipTests

# Final image to run both frontend and backend
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=backend-build /app/backend/target/*.jar app.jar
COPY --from=frontend /usr/share/nginx/html /usr/share/nginx/html

# Expose the ports
EXPOSE 8080
EXPOSE 80

# Start the backend and serve the frontend
CMD ["sh", "-c", "java -jar app.jar & nginx -g 'daemon off;'"]
