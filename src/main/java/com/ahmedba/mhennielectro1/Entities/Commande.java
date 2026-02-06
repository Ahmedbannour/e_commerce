package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_commande;



    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonIgnore
    private Users users;



    @ManyToOne
    @JoinColumn(name = "livreur_id" , nullable = false)
    @JsonBackReference("commandes-livreur")
    private Livreur livreur;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "commande" , orphanRemoval = true)
    @JsonManagedReference("lignecommande-commande")
    private List<LigneCommande> ligneCommandes;


    public Commande() {
    }

    public Commande(long id, Date date_commande, Users users, Livreur livreur, List<LigneCommande> ligneCommandes) {
        this.id = id;
        this.date_commande = date_commande;
        this.users = users;
        this.livreur = livreur;
        this.ligneCommandes = ligneCommandes;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
