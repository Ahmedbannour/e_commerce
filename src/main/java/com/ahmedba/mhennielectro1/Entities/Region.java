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
@Table(name = "Region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "pays_id" , nullable = false)
    @JsonBackReference("pays-region")
    private Pays pays;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region", fetch = FetchType.EAGER)
    @JsonManagedReference("ville-region")
    private List<Ville> villes;


}