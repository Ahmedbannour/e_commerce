package com.ahmedba.mhennielectro1.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SocieteLivraison")
public class SocieteLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String tel;

    private String matricule_fiscale;

    private String email;


    public List<Livreur> getLivreurs() {
        return livreurs;
    }

    public void setLivreurs(List<Livreur> livreurs) {
        this.livreurs = livreurs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricule_fiscale() {
        return matricule_fiscale;
    }

    public void setMatricule_fiscale(String matricule_fiscale) {
        this.matricule_fiscale = matricule_fiscale;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "societeLivraison" , orphanRemoval = true)
    @JsonManagedReference
    private List<Livreur> livreurs;



    @ManyToOne
    @JoinColumn(name = "ville_id" , nullable = false)
    @JsonBackReference
    private Ville ville;


}
