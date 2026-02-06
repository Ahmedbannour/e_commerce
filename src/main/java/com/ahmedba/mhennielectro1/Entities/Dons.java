package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    @JsonBackReference("product-dons")
    private Product product;




    @ManyToOne
    @JoinColumn(name = "evenement_id" , nullable = false)
    @JsonBackReference
    private Evenement evenement;



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
