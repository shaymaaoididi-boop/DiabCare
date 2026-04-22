package com.diabcare.models;

public class Patient {

    private int id;
    private String nom;
    private double glycemie;

    public Patient() {}

    public Patient(int id, String nom, double glycemie) {
        this.id = id;
        this.nom = nom;
        this.glycemie = glycemie;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getGlycemie() {
        return glycemie;
    }

    public void setGlycemie(double glycemie) {
        this.glycemie = glycemie;
    }

    public void afficherDetails() {
        System.out.println("Patient : " + nom + " | Glycémie : " + glycemie);
    }
}
