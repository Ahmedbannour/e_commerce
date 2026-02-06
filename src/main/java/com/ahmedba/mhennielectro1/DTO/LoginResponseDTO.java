package com.ahmedba.mhennielectro1.DTO;

public class LoginResponseDTO {

    private String accessToken;
    private String refreshToken;
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;

    public LoginResponseDTO(
            String accessToken,
            String refreshToken,
            long id,
            String nom,
            String prenom,
            String email,
            String role
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
    }

    // getters
    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
