package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
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



    public List<Dons> getDons() {
        return Dons;
    }

    public void setDons(List<Dons> dons) {
        Dons = dons;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Evenement() {

    }

    public Evenement(long id, String label, Date date_debut, Date date_fin) {
        this.id = id;
        this.label = label;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
}
