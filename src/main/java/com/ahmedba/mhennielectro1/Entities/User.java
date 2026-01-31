package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference
    private List<Commande> commandes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference("role-users")
    private Role role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ville_id", nullable = false)
    @JsonBackReference
    private Ville ville;

    // ================= Constructors =================

    public User() {}

    public User(String nom, String prenom, String email, String password,
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

    // ================= Getters =================

    public long getId() { return id; }

    public String getNom() { return nom; }

    public String getPrenom() { return prenom; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getPhone() { return phone; }

    public Date getDateNaissance() { return dateNaissance; }

    public Date getCreatedAt() { return createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public boolean isActive() { return active; }

    public boolean isVerified() { return verified; }

    public List<Commande> getCommandes() { return commandes; }

    public Role getRole() { return role; }

    public Ville getVille() { return ville; }

    // ================= Setters =================

    public void setId(long id) { this.id = id; }

    public void setNom(String nom) { this.nom = nom; }

    public void setPrenom(String prenom) { this.prenom = prenom; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public void setActive(boolean active) { this.active = active; }

    public void setVerified(boolean verified) { this.verified = verified; }

    public void setCommandes(List<Commande> commandes) { this.commandes = commandes; }

    public void setRole(Role role) { this.role = role; }

    public void setVille(Ville ville) { this.ville = ville; }
}
