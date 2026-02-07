package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // --- RELATION RÉCURSIVE ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"subCategories", "products"}) // Évite la récursion infinie
    private Categorie parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Categorie> subCategories;

    // --- RELATION AVEC PRODUITS ---

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("categorie")
    private List<Product> products;
}