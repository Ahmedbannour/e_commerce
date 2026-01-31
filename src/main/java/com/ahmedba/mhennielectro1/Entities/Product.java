package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String image;

    @Column
    private String ref;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_achat;

    @OneToOne(optional = true)
    @JoinColumn(name = "garantie_id")
    @JsonManagedReference("product-garantie")
    private Garantie garantie;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference("depot-product")
    private List<DepotProduct> depotProducts;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference("commande-product")
    private List<LigneCommande> ligneCommandes;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonIgnore
    private List<Dons> Dons;

    // ✅ NEW: Caracteristiques du produit
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    @JsonManagedReference("product-caracteristique")
    private List<Caracteristique> caracteristiques;

    // ✅ NEW: Photos du produit
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    @JsonManagedReference("product-photo")
    private List<Photo> photos;

    // ---------------- Getters & Setters ----------------

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

    public Garantie getGarantie() {
        return garantie;
    }

    public List<DepotProduct> getDepotProducts() {
        return depotProducts;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public List<Dons> getDons() {
        return Dons;
    }

    // ✅ NEW getters
    public List<Caracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public List<Photo> getPhotos() {
        return photos;
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

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

    public void setDepotProducts(List<DepotProduct> depotProducts) {
        this.depotProducts = depotProducts;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public void setDons(List<Dons> dons) {
        Dons = dons;
    }

    // ✅ NEW setters
    public void setCaracteristiques(List<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    // ---------------- Constructors ----------------

    public Product() {}

    public Product(String description, double price, String ref) {
        this.description = description;
        this.price = price;
        this.ref = ref;
    }

    public Product(long id, String description, double price, String image, String ref, Date date_achat) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ref = ref;
        this.date_achat = date_achat;
    }
}
