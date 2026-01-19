package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
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

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


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
