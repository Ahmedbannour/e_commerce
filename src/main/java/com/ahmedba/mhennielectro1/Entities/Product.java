package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = false , nullable = false , length = 255)
    private String description;

    @Column(length = 255)
    private double price;

    private String image;

    @Column
    private String ref;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_achat;




    @OneToOne(optional = true)
    @JoinColumn(name = "garantie_id")
    @JsonManagedReference("product-garantie")
    private Garantie garantie;



    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference("depot-product")
    private List<DepotProduct> depotProducts;



    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonManagedReference("commande-product")
    private List<LigneCommande> ligneCommandes;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product" , orphanRemoval = true)
    @JsonIgnore
    private List<Dons> Dons;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    @JsonIgnoreProperties("products")
    private Categorie categorie;
}
