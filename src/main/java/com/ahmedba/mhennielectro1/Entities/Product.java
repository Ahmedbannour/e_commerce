package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = false , nullable = false , length = 255)
    private String description;

    @Column(length = 255)
    private double price;

    @Column(length = 255)
    private String image;

    @Column
    private String ref;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_achat;




    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "garantie_id")
    @JsonManagedReference
    private Garantie garantie;

    public Garantie getGarantie() {
        return garantie;
    }

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

    public List<DepotProduct> getDepotProducts() {
        return depotProducts;
    }

    public void setDepotProducts(List<DepotProduct> depotProducts) {
        this.depotProducts = depotProducts;
    }

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference
    private List<DepotProduct> depotProducts;



    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference
    private List<LigneCommande> ligneCommandes;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference
    private List<Dons> Dons;

    public List<Dons> getDons() {
        return Dons;
    }

    public void setDons(List<Dons> dons) {
        Dons = dons;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getRef() {
        return ref;
    }

    public Date getDate_achat() {
        return date_achat;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }


    public Product(long id, String description, double price, String image, String ref, Date date_achat) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ref = ref;
        this.date_achat = date_achat;
    }

    public Product() {

    }
}
