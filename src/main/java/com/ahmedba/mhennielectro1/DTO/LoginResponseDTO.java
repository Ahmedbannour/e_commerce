package com.ahmedba.mhennielectro1.DTO;

public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;

    public LoginResponseDTO(String token, long id, String nom, String prenom, String email, String role) {
        this.token = token;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getType() { return type; }
    public long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
