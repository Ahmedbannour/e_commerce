package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ligne_commande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private int quantite;


    @ManyToOne
    @JoinColumn(name = "commande_id" , nullable = false)
    @JsonBackReference
    private Commande commande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    @JsonBackReference
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
