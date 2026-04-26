package com.diabcare.models;

public class Patient {

    private String nom;
    private double derniereMesure;

    public Patient(String nom) {
        this.nom = nom;
    }

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
    }
}