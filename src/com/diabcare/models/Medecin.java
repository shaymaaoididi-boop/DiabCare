package com.diabcare.models;

public class Medecin {
    private String nom;
    private String specialite;
    private String adresse;
    private boolean disponible;

    public Medecin(String nom, String specialite, String adresse) {
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.disponible = true;
    }

    public boolean estDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getNom() { return nom; }
    public String getSpecialite() { return specialite; }
    public String getAdresse() { return adresse; }

    @Override
    public String toString() {
        return nom + " - " + specialite + " (" + adresse + ")";
    }
}