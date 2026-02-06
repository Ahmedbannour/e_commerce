package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String nom;

    @Column(nullable = false, length = 255)
    private String prenom;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 255)
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateNaissance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean active;
    private boolean verified;

    // ================= Relations =================

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Commande> commandes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference("role-users")
    private Role role;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ville_id", nullable = true)
    @JsonBackReference
    private Ville ville;

    // ================= Constructors =================

    public Users() {
    }

    public Users(String nom, String prenom, String email, String password,
                 String phone, Date dateNaissance, Role role, Ville ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateNaissance = dateNaissance;
        this.role = role;
        this.ville = ville;
        this.active = true;
        this.verified = false;
        this.createdAt = new Date();
    }

}
