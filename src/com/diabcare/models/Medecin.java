package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class Medecin {

    private int id;
    private String nom;
    private String email;
    private String specialite;
    private String adresse;
    private boolean disponible;

    private List<Patient> patients;

    public Medecin(int id, String nom, String email, String specialite, String adresse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.specialite = specialite;
        this.adresse = adresse;
        this.disponible = true;
        this.patients = new ArrayList<>();
    }

    // getters
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getSpecialite() { return specialite; }
    public String getAdresse() { return adresse; }

    public boolean estDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    // Ajouter patient
    public void ajouterPatient(Patient p) {
        patients.add(p);
        System.out.println("Patient ajouté : " + p.getNom());
    }

    // Analyse médicale (from HEAD)
    public void analyserPatients() {
        System.out.println("\nAnalyse médicale du Dr " + nom);

        for (Patient p : patients) {
            if (p.estAlerte()) {
                System.out.println(p.getNom() + " - RISQUE ÉLEVÉ");
            } else {
                System.out.println(p.getNom() + " - Stable");
            }
        }
    }

    // Dashboard (from origin/main amélioré + stats HEAD)
    public void afficherDashboard() {

        System.out.println("=== 🩺 Espace Médecin ===");
        System.out.println("Médecin : Dr " + nom + " | Email : " + email);

        int total = patients.size();
        int alertes = 0;

        for (Patient p : patients) {
            if (p.getDerniereMesure() > 13.9 || p.getDerniereMesure() < 3.9) {
                alertes++;
            }
        }

        int taux = (total == 0) ? 0 : (int) (((total - alertes) * 100.0) / total);

        System.out.println("Patients : " + total +
                " | Alertes : " + alertes +
                " | Taux contrôle : " + taux + "%");

        System.out.println("--------------------------------------------------");

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

    @Override
    public String toString() {
        return nom + " - " + specialite + " (" + adresse + ")";
    }
}