package com.ahmedba.mhennielectro1.DTO;

import java.util.Date;

public class RegisterRequestDTO {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String phone;
    private Date dateNaissance;

    // rôle optionnel (CLIENT par défaut)
    private String role;

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public Date getDateNaissance() { return dateNaissance; }
    public String getRole() { return role; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setRole(String role) { this.role = role; }
}
