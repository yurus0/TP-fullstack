package com.tp3.application.Controller;

import com.tp3.application.Modele.Voiture;
import com.tp3.application.Repository.VoitureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VoitureController {

    private final VoitureRepository voitureRepository;
    public VoitureController(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    @RequestMapping("/voitures")
    public Iterable<Voiture> getVoitures(){
        return voitureRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path="/voitures")
    public Voiture createVoiture(@RequestBody Voiture voiture){
        return voitureRepository.save(voiture);
    }
    @RequestMapping(method = RequestMethod.GET, path="/voitures/{id}")
    public Voiture getVoiture(@PathVariable Long id){
        return voitureRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/voitures/{id}")
    public Voiture updateVoiture(@PathVariable Long id, @RequestBody Voiture voiture){
        Voiture v1= voitureRepository.findById(id).orElse(null);
        Objects.requireNonNull(v1).setId(voiture.getId());
        v1.setAnnee(voiture.getAnnee());
        v1.setCouleur(voiture.getCouleur());
        v1.setImmatricule(voiture.getImmatricule());
        v1.setMarque(voiture.getMarque());
        v1.setModele(voiture.getModele());
        return voitureRepository.save(v1);
    }

}
