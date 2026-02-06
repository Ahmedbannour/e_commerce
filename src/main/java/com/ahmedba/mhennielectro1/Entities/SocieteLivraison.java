package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "SocieteLivraison")
public class SocieteLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String tel;

    private String matricule_fiscale;

    private String email;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "societeLivraison" , orphanRemoval = true)
    @JsonIgnore
    private List<Livreur> livreurs;



    @ManyToOne
    @JoinColumn(name = "ville_id" , nullable = false)
    @JsonIgnore
    private Ville ville;


}
