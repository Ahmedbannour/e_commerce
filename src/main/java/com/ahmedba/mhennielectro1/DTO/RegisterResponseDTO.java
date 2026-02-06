package com.ahmedba.mhennielectro1.DTO;

public class RegisterResponseDTO {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String message;

    public RegisterResponseDTO(long id, String nom, String prenom, String email, String role, String ville) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMessage() { return message; }
}
