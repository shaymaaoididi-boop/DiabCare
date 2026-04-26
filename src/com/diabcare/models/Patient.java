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

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getGlycemie() {
        return glycemie;
    }

    public double getDerniereMesure() {
        return derniereMesure;
    }

    // Setters
    public void setDerniereMesure(double valeur) {
        this.derniereMesure = valeur;
    }

    // 🔍 Analyse médicale
    public boolean estAlerte() {
        return glycemie > 1.8;
    }

    public String getStatut() {
        if (glycemie > 2.0) return "Critique";
        else if (glycemie > 1.8) return "Élevé";
        else return "Stable";
    }

    // 🔄 Mise à jour
    public void mettreAJourGlycemie(double nouvelleValeur) {
        this.glycemie = nouvelleValeur;
        this.derniereMesure = nouvelleValeur;
    }

    // 📊 Moyenne (simplifiée)
    public double calculerMoyenne() {
        return derniereMesure;
    }

    // 📋 Affichage
    public void afficherDetails() {
        System.out.println("Patient : " + nom +
                " | Glycémie : " + glycemie +
                " | Statut : " + getStatut());
    }
}