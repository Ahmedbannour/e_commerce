package com.ahmedba.mhennielectro1.Services;

import com.ahmedba.mhennielectro1.Entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String email;
    @JsonIgnore
    private String password;
    private String nom;
    private String prenom;
    private String phone;
    private boolean verified;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String email, String password, String nom, String prenom,
                           String phone, boolean verified, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.verified = verified;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())
        );

        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword(),
                user.getNom(),
                user.getPrenom(),
                user.getPhone(),
                user.isVerified(), // Assure-toi que cette méthode existe dans ton entité User
                authorities);
    }

    // Getters pour tes champs personnalisés
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getPhone() { return phone; }
    public boolean isVerified() { return verified; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return email; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}