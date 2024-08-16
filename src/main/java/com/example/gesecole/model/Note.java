package com.example.gesecole.model;

public class Note {
    private int id;
    private int etudiantId;
    private int coursId;
    private double valeur;

    public Note() {
    }

    public Note(int id, int etudiantId, int coursId, double valeur) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.coursId = coursId;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }

    public int getCoursId() {
        return coursId;
    }

    public void setCoursId(int coursId) {
        this.coursId = coursId;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
}
