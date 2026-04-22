package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class Medecin {

    private int id;
    private String nom;
    private String email;
    private List<Patient> patients = new ArrayList<>();

    public Medecin(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public void ajouterPatient(Patient p) {
        patients.add(p);
        System.out.println("✅ Patient ajouté : " + p.getNom());
    }

    public void analyserPatients() {
        System.out.println("\n🧠 Analyse médicale du Dr " + nom);

        for (Patient p : patients) {
            if (p.estAlerte()) {
                System.out.println("⚠ " + p.getNom() + " - RISQUE ÉLEVÉ");
            } else {
                System.out.println("✅ " + p.getNom() + " - Stable");
            }
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void afficherDetails() {
        System.out.println("Médecin : " + nom + " | Email : " + email);
    }
}