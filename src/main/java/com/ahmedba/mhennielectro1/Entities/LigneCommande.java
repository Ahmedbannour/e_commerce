package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ligne_commande")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private int quantite;


    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    @JsonBackReference("lignecommande-commande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("commande-product")
    private Product product;

    private double prixUnitaire;
}
