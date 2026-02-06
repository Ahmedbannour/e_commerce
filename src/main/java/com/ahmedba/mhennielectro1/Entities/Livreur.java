package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
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
