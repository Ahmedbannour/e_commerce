package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "DepotProduct")
public class DepotProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(length = 255, nullable = false)
    private  String description;


    @ManyToOne
    @JoinColumn(name = "depot_id" , nullable = false)
    @JsonBackReference
    private Depot depot;


    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    @JsonBackReference("depot-product")
    private Product product;


    @Column(nullable = false , length = 255 , precision = 0)
    private double quantity;


    public DepotProduct() {

    }

    public DepotProduct(long id, String description, Depot depot, Product product, double quantity) {
        this.id = id;
        this.description = description;
        this.depot = depot;
        this.product = product;
        this.quantity = quantity;
    }
}
