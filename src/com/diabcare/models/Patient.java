<<<<<<< HEAD
package com.diabcare.models;

public class Patient {
    private String nom;

    public Patient(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
=======
pprivate double derniereMesure;

public String getNom() {
    return nom;
}

public double getDerniereMesure() {
    return derniereMesure;
}

public void setDerniereMesure(double valeur) {
    this.derniereMesure = valeur;
}

public double calculerMoyenne() {
    return derniereMesure; // simplifié Sprint 1
>>>>>>> 4c8e9f207bb0f56808005cb6e644ceaebe84d75e
}