package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Depot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String label;

    @Column(length = 255)
    private String ref;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "depot" , orphanRemoval = true)
    @JsonManagedReference
    private List<DepotProduct> depotProducts;


    @ManyToOne
    @JoinColumn(name = "ville_id" , nullable = false)
    @JsonManagedReference
    private Ville ville;


}
