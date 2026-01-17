package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
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

    private int id_ville;
    private String role;

    @Column(unique = true , nullable = false , length = 255)
    private Date date_naissance;
    private Date created_at;
    private Date updated_at;

    private boolean active;
    private boolean verified;

    public void setId(long id) {
        this.id = id;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user" , orphanRemoval = true)
    @JsonManagedReference
    private List<Commande> commandes;



    @ManyToOne
    @JoinColumn(name = "ville_id" , nullable = false)
    @JsonBackReference
    private Ville ville;

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getId_ville() {
        return id_ville;
    }

    public String getRole() {
        return role;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isVerified() {
        return verified;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public User() {

    }


    public User(int id, String nom, String prenom, String email, String password, String phone, int id_ville, String role, Date date_naissance, Date created_at, Date updated_at, boolean active, boolean verified) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.id_ville = id_ville;
        this.role = role;
        this.date_naissance = date_naissance;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
        this.verified = verified;
    }
}
