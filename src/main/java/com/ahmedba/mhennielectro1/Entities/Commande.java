package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Commande")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_commande;



    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonBackReference("user-commandes")
    private User user;



    @ManyToOne
    @JoinColumn(name = "livreur_id" , nullable = true)
    @JsonBackReference("commandes-livreur")
    private Livreur livreur;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "commande" , orphanRemoval = true)
    @JsonManagedReference("lignecommande-commande")
    private List<LigneCommande> ligneCommandes;


    private double total;

}
