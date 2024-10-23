package com.tp3.application.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Voiture {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @NonNull
    @Getter
    @Setter
    private String marque;
    @NonNull
    @Getter
    @Setter
    private String modele;
    @NonNull
    @Getter
    @Setter
    private String couleur;
    @NonNull
    @Getter
    @Setter
    private String immatricule;
    @NonNull
    @Getter
    @Setter
    private Integer annee;
    @NonNull
    @Getter
    @Setter
    private Integer prix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="proprietaire")
    @JsonIgnore
    @NonNull
    private Proprietaire proprietaire;

}