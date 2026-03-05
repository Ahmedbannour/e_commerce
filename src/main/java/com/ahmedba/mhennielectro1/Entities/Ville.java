package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Ville")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    private int code_postal;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonBackReference("ville-region")
    private Region region;




    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ville" , orphanRemoval = true)
    @JsonIgnoreProperties("ville")
    private List<User> users;


    @OneToMany(mappedBy = "ville")
    @JsonIgnoreProperties("ville") // Très important pour couper la boucle Ville -> Depot -> Ville
    private List<Depot> depots;

}

