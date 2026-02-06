package com.ahmedba.mhennielectro1.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Assurance")
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String label;

    @Column(length = 255)
    private String matricule;



    public Assurance(int id, String label, String matricule) {
        this.id = id;
        this.label = label;
        this.matricule = matricule;
    }

    public Assurance() {}
}
