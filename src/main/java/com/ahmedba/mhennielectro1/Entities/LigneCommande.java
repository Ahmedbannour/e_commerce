package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ligne_commande")
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


    public LigneCommande(Long id, int quantite, Commande commande, Product product) {
        this.id = id;
        this.quantite = quantite;
        this.commande = commande;
        this.product = product;
    }


    public LigneCommande() {

    }
}
