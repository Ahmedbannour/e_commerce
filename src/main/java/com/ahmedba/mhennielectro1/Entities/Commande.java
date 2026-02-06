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
@Table(name = "Commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_commande;



    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
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


}
