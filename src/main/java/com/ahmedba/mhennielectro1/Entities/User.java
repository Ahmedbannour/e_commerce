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
    @JoinColumn(name = "ville_id" , nullable = false)
    @JsonBackReference("ville-user")
    private Ville ville;


    @ManyToOne
    @JoinColumn(name = "role_id" , nullable = false)
    @JsonBackReference("role-user")
    private Role role;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId_ville() {
        return id_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(long id, String nom, String prenom, String email, String password, String phone, int id_ville, Date date_naissance, Date created_at, Date updated_at, boolean active, boolean verified, List<Commande> commandes, Ville ville, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.id_ville = id_ville;
        this.date_naissance = date_naissance;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
        this.verified = verified;
        this.commandes = commandes;
        this.ville = ville;
        this.role = role;
    }


    public User() {

    }
}
