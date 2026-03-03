package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DepotProduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JsonIgnoreProperties({"depotProducts", "ligneCommandes", "Dons", "categorie"})
    private Product product;


    @Column(nullable = false , length = 255 , precision = 0)
    private double quantity;

}
