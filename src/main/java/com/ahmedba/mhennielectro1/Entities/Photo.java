package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // chemin ou URL de l'image
    @Column(nullable = false, length = 255)
    private String url;

    // photo principale du produit
    @Column(name = "img_main")
    private boolean main;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("product-photo")
    private Product product;

    // -------- Constructors --------

    public Photo() {
    }

    public Photo(String url, boolean main, Product product) {
        this.url = url;
        this.main = main;
        this.product = product;
    }
}
