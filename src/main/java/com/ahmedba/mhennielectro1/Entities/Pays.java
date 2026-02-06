package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Pays")
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    private String code;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "pays" , orphanRemoval = true)
    @JsonManagedReference
    private List<Region> regions;



    public Pays(long id, String label, String code, List<Region> regions) {
        this.id = id;
        this.label = label;
        this.code = code;
        this.regions = regions;
    }


    public Pays() {
    }
}
