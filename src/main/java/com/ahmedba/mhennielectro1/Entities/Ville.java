package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Ville")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    private int code_postal;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @JsonBackReference
    private Region region;


    public Ville(long id, String label, int code_postal, Region region) {
        this.id = id;
        this.label = label;
        this.code_postal = code_postal;
        this.region = region;
    }

    public Ville() {
    }


}
