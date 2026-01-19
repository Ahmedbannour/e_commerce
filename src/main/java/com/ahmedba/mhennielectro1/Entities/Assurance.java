package com.ahmedba.mhennielectro1.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Assurance")
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String label;

    @Column(length = 255)
    private String matricule;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Assurance(int id, String label, String matricule) {
        this.id = id;
        this.label = label;
        this.matricule = matricule;
    }

    public Assurance() {}
}
