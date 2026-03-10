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

    private String code_postal; // Changé en String si vous avez des codes postaux avec 0 au début (ex: 0123)


    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonBackReference("ville-region")
    private Region region;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ville", orphanRemoval = true)
    @JsonIgnoreProperties("ville")
    private List<User> users;

    @OneToMany(mappedBy = "ville")
    @JsonIgnoreProperties("ville")
    private List<Depot> depots;

}
