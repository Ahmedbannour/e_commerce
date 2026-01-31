package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "caracteristique")
public class Caracteristique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ex: "Couleur", "Puissance", "Capacité"
    @Column(nullable = false, length = 100)
    private String nom;

    // ex: "Rouge", "1200W", "300L"
    @Column(nullable = false, length = 255)
    private String valeur;

    // ✅ NEW : type (BENEFITS / CONSEIL)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CaracteristiqueType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("product-caracteristique")
    private Product product;


    // -------- Constructors --------

    public Caracteristique() {}

    public Caracteristique(String nom, String valeur, Product product) {
        this.nom = nom;
        this.valeur = valeur;
        this.product = product;
    }

    // -------- Getters & Setters --------

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getValeur() {
        return valeur;
    }

    public CaracteristiqueType getType() {
        return type;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public void setType(CaracteristiqueType type) {
        this.type = type;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
