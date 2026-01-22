package com.ahmedba.mhennielectro1.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Ville")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    private int code_postal;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonBackReference("ville-region")
    private Region region;




    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ville" , orphanRemoval = true)
    @JsonBackReference("ville-user")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Ville(long id, String label, int code_postal, Region region) {
        this.id = id;
        this.label = label;
        this.code_postal = code_postal;
        this.region = region;
    }

    public Ville() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}

