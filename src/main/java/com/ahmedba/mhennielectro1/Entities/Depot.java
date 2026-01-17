package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Depot")
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String label;

    @Column(length = 255)
    private String ref;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "depot" , orphanRemoval = true)
    @JsonManagedReference
    private List<DepotProduct> depotProducts;


    @ManyToOne
    @JoinColumn(name = "ville_id" , nullable = false)
    @JsonBackReference
    private Ville ville;



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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }


    public Depot(long id, String label, String ref) {
        this.id = id;
        this.label = label;
        this.ref = ref;
    }

    public Depot() {

    }
}
