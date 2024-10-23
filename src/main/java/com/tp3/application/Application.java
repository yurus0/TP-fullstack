package com.tp3.application;

import com.tp3.application.Modele.Proprietaire;
import com.tp3.application.Modele.Voiture;
import com.tp3.application.Repository.ProprietaireRepository;
import com.tp3.application.Repository.VoitureRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	private final VoitureRepository repository;

	public Application(VoitureRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(ProprietaireRepository proprietaireRepository) {
		return args -> {
			Proprietaire proprietaire1 = new Proprietaire("Ali" , "Hassan");
			Proprietaire proprietaire2 = new Proprietaire("Najat" , "Bani");
			proprietaireRepository.save(proprietaire1);
			proprietaireRepository.save(proprietaire2);
			repository.save(new Voiture("Toyota", "Corolla", "Grise", "A-1-9090", 2018, 95000, proprietaire1));
			repository.save(new Voiture("Ford", "Fiesta", "Rouge", "A-2-8090", 2015, 90000, proprietaire1));
			repository.save(new Voiture("Honda", "CRV", "Bleu", "A-3-7090", 2016, 140000, proprietaire2));
		};
	}
}
