package com.example.gesecole.model;

public class Cours {
    private int id;
    private String nom;
    private String description;
    private int enseignantId;

    public Cours() {
    }

    public Cours(int id, String nom, String description, int enseignantId) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.enseignantId = enseignantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(int enseignantId) {
        this.enseignantId = enseignantId;
    }
}
