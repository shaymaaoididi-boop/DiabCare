package com.diabcare.models;

public class Patient {

    private int id;
    private String nom;
    private double glycemie;

    public Patient(int id, String nom, double glycemie) {
        this.id = id;
        this.nom = nom;
        this.glycemie = glycemie;
    }

    public boolean estAlerte() {
        return glycemie > 1.8;
    }

    public String getStatut() {
        if (glycemie > 2.0) return "Critique";
        else if (glycemie > 1.8) return "Élevé";
        else return "Stable";
    }

    public void mettreAJourGlycemie(double nouvelleValeur) {
        this.glycemie = nouvelleValeur;
    }

    public void afficherDetails() {
        System.out.println("Patient : " + nom +
                " | Glycémie : " + glycemie +
                " | Statut : " + getStatut());
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getGlycemie() { return glycemie; }
}