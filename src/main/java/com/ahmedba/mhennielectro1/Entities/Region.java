package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "pays_id" , nullable = false)
    @JsonBackReference
    private Pays pays;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "region" , orphanRemoval = true)
    @JsonManagedReference
    private List<Ville> villes;



    public Region(long id, String label, Pays pays, List<Ville> villes) {
        this.id = id;
        this.label = label;
        this.pays = pays;
        this.villes = villes;
    }


    public Region() {

    }
}
