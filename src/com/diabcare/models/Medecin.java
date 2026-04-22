package com.diabcare.models;

public class Medecin {

    private int id;
    private String nom;
    private String email;

    public Medecin() {}

    public Medecin(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void afficherDetails() {
        System.out.println("Médecin : " + nom + " | Email : " + email);
    }
}