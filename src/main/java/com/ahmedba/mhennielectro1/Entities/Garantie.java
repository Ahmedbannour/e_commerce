package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Garantie")
public class Garantie {

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
    private Date date_expiration;


    @OneToOne(mappedBy = "garantie")
    @JsonBackReference("product-garantie")
    private Product product;


    public Garantie(long id, String label, Date date_debut, Date date_expiration, Product product) {
        this.id = id;
        this.label = label;
        this.date_debut = date_debut;
        this.date_expiration = date_expiration;
        this.product = product;
    }


    public Garantie() {
    }
}
