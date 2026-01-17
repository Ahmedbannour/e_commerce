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
    private String matricule_fiscale;

}
