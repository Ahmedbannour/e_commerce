package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

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
    @JsonBackReference
    private User user;



    @ManyToOne
    @JoinColumn(name = "livreur_id" , nullable = false)
    @JsonBackReference
    private Livreur livreur;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
