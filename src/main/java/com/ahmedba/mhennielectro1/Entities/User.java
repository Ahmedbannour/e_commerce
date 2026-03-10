package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true , nullable = false , length = 255)
    private String nom;

    @Column(unique = true , nullable = false , length = 255)
    private String prenom;

    @Column(unique = true , nullable = false , length = 255)
    private String email;


    private String password;

    @Column(unique = true , nullable = false , length = 255)
    private String phone;

    @Column(unique = true , nullable = false , length = 255)
    private Date date_naissance;
    private Date created_at;
    private Date updated_at;

    private boolean active;
    private boolean verified;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user" , orphanRemoval = true)
    @JsonManagedReference("user-commandes")
    private List<Commande> commandes;



    @ManyToOne
    @JoinColumn(name = "ville_id", nullable = false)
    @JsonIgnoreProperties("users")
    private Ville ville;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnoreProperties("users")
    private Role role;



}
