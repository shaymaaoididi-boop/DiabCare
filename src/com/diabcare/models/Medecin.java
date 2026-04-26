package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class Medecin {

    private int id;
    private String nom;
    private String email;
    private List<Patient> patients;

    public Medecin(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.patients = new ArrayList<>();
    }

    public void ajouterPatient(Patient p) {
        patients.add(p);
        System.out.println(" Patient ajouté : " + p.getNom());
    }

    // 🔍 Analyse médicale
    public void analyserPatients() {
        System.out.println("\n Analyse médicale du Dr " + nom);

        for (Patient p : patients) {
            if (p.estAlerte()) {
                System.out.println(" " + p.getNom() + " - RISQUE ÉLEVÉ");
            } else {
                System.out.println(" " + p.getNom() + " - Stable");
            }
        }
    }

    // 📊 Dashboard
    public void afficherDashboard() {

        System.out.println("=== DASHBOARD MÉDECIN ===");
        System.out.println("Médecin : Dr " + nom + " | Email : " + email);

        int total = patients.size();
        int alertes = 0;

        for (Patient p : patients) {
            if (p.getDerniereMesure() > 13.9 || p.getDerniereMesure() < 3.9) {
                alertes++;
            }
        }

        int taux = (total == 0) ? 0 : (int)(((total - alertes) * 100.0) / total);

        System.out.println("\n Statistiques globales :");
        System.out.println("Nombre de patients : " + total);

        System.out.println("\n--------------------------------------------------");

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

    public List<Patient> getPatients() {
        return patients;
    }

    public void afficherDetails() {
        System.out.println("Médecin : " + nom + " | Email : " + email);
    }
}