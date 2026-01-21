package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Livreur")
public class Livreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false , length = 255)
    private String nom;

    @Column(nullable = false , length = 255)
    private String prenom;

    @Column(unique = true , nullable = false , length = 255)
    private String email;


    private String password;

    @Column(nullable = false , length = 255)
    private String phone;

    private int id_ville;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_naissance;



    @OneToMany(cascade = CascadeType.ALL , mappedBy = "livreur" , orphanRemoval = true)
    @JsonManagedReference("commandes-livreur")
    private List<Commande> commandes;



    @ManyToOne
    @JoinColumn(name = "societe_id" , nullable = true )
    @JsonIgnore
    private SocieteLivraison societeLivraison;


    public SocieteLivraison getSocieteLivraison() {
        return societeLivraison;
    }

    public void setSocieteLivraison(SocieteLivraison societeLivraison) {
        this.societeLivraison = societeLivraison;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId_ville() {
        return id_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Livreur() {

    }


    public Livreur(Long id, String nom, String prenom, String email, String password, String phone, int id_ville, Date date_naissance, List<Commande> commandes, SocieteLivraison societeLivraison) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.id_ville = id_ville;
        this.date_naissance = date_naissance;
        this.commandes = commandes;
        this.societeLivraison = societeLivraison;
    }


    public Livreur(String phone, String email, String password, Date date_naissance, String nom, String prenom) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.date_naissance = date_naissance;
        this.nom = nom;
        this.prenom = prenom;
    }
}
