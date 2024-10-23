//package com.tp3.application;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.tp3.application.Modele.Proprietaire;
//import com.tp3.application.Modele.Voiture;
//import com.tp3.application.Repository.VoitureRepository;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
////Si le test concerne uniquement les composantes JPA
////Lorsque cette annotation est utilisée, H2, Hibernate et Spring Data sont configurés //automatiquement pour le test.
//public class VoitureRepositoryTest {
//    @Autowired
//    private TestEntityManager entityManager;
//    //TestEntityManager est utilisée pour manipuler les entités persistantes
//    @Autowired
//    VoitureRepository voitureRepo;
//    @Test
//    public void ajouterVoiture() {
//        Proprietaire proprietaire = new Proprietaire("Ahmed" , "Hassan");
//        Voiture voiture = new Voiture ("MiolaCar","Uber","Blanche","M-2020", 2021, 180000, proprietaire);
//        entityManager.persistAndFlush(voiture);
//    }
//    @Test
//    public void supprimerVoiture() {
//        Proprietaire proprietaire1 = new Proprietaire("Ali" , "Hassan");
//        Proprietaire proprietaire2 = new Proprietaire("Najat" , "Bani");
//        entityManager.persistAndFlush(new Voiture("MiolaCar","Uber","Blanche","M-2020", 2021, 180000, proprietaire1));
//        entityManager.persistAndFlush(new Voiture ("MiniCooper","Uber","Rouge","C-2020", 2021, 180000, proprietaire2));
//        voitureRepo.deleteAll();
//        assertThat(voitureRepo.findAll()).isEmpty();
//    }
//}