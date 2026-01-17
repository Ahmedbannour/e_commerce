package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Dons")
public class Dons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String description;

    private String type;



    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    @JsonBackReference
    private Product product;




    @ManyToOne
    @JoinColumn(name = "evenement_id" , nullable = false)
    @JsonBackReference
    private Evenement evenement;


    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Dons(Long id, String label, String description, String type, Product product) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.type = type;
        this.product = product;
    }


    public Dons() {

    }
}
