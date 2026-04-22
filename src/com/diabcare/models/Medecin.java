package com.diabcare.models;

import java.util.ArrayList;

public class Medecin {

    private String nom;
    private ArrayList<Patient> patients;

    public Medecin(String nom) {
        this.nom = nom;
        this.patients = new ArrayList<>();
    }

    public void ajouterPatient(Patient p) {
        patients.add(p);
    }

    public void afficherDashboard() {

        System.out.println("=== 🩺 Espace Médecin ===");

        // 🔝 Stats
        int total = patients.size();
        int alertes = 0;

        for (Patient p : patients) {
            if (p.getDerniereMesure() > 13.9 || p.getDerniereMesure() < 3.9) {
                alertes++;
            }
        }

        int taux = (total == 0) ? 0 : (int)(((total - alertes) * 100.0) / total);

        System.out.println("Patients : " + total +
                " | Alertes : " + alertes +
                " | Taux contrôle : " + taux + "%");

        System.out.println("--------------------------------------------------");

        // 📋 Tableau
        System.out.printf("%-10s %-15s %-10s %-10s %-10s\n",
                "Nom", "Dernière", "Statut", "Moyenne", "Alertes");

        System.out.println("--------------------------------------------------");

        for (Patient p : patients) {

            double val = p.getDerniereMesure();
            String statut = (val > 13.9 || val < 3.9) ? "Critique" : "Normal";
            int nbAlertes = statut.equals("Critique") ? 1 : 0;

            System.out.printf("%-10s %-15.1f %-10s %-10.1f %-10d\n",
                    p.getNom(),
                    val,
                    statut,
                    p.calculerMoyenne(),
                    nbAlertes);
        }

        System.out.println("--------------------------------------------------");
    }
}