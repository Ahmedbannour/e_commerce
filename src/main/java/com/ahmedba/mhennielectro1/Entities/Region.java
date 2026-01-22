package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "pays_id" , nullable = false)
    @JsonBackReference("pays-region")
    private Pays pays;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "region" , orphanRemoval = true)
    @JsonManagedReference("ville-region")
    private List<Ville> villes;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }


    public Region(long id, String label, Pays pays, List<Ville> villes) {
        this.id = id;
        this.label = label;
    }


    public Region() {

    }
}
