package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Evnement")
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String label;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_debut;

    @Column(length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_fin;



    @OneToMany(cascade = CascadeType.ALL , mappedBy = "evenement" , orphanRemoval = true)
    @JsonManagedReference
    private List<Dons> Dons;


    public Evenement() {

    }

    public Evenement(long id, String label, Date date_debut, Date date_fin) {
        this.id = id;
        this.label = label;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
}
