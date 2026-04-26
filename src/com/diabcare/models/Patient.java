package com.diabcare.models;

public class Patient {

    private int id;
    private String nom;
    private double glycemie;
    private double derniereMesure;

    public Patient(int id, String nom, double glycemie) {
        this.id = id;
        this.nom = nom;
        this.glycemie = glycemie;
        this.derniereMesure = glycemie;
    }

    // 🔍 Analyse
    public boolean estAlerte() {
        return glycemie > 1.8;
    }

    public String getStatut() {
        if (glycemie > 2.0) return "Critique";
        else if (glycemie > 1.😎 return "Élevé";
        else return "Stable";
    }

    // 🔄 Mise à jour
    public void mettreAJourGlycemie(double nouvelleValeur) {
        this.glycemie = nouvelleValeur;
        this.derniereMesure = nouvelleValeur;
    }

    // 📊 Dashboard
    public double getDerniereMesure() {
        return derniereMesure;
    }

    public double calculerMoyenne() {
        return derniereMesure; // simplifié
    }

    // 📋 Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getGlycemie() { return glycemie; }

    public void afficherDetails() {
        System.out.println("Patient : " + nom +
                " | Glycémie : " + glycemie +
                " | Statut : " + getStatut());
    }
}