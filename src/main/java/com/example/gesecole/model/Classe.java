package com.example.gesecole.model;

public class Classe {
    private int id;
    private String nom;
    private String niveau;

    public Classe() {
    }

    public Classe(int id, String nom, String niveau) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
