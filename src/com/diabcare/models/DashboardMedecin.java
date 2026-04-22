package com.diabcare.models;

public class DashboardMedecin {

    private Medecin medecin;

    public DashboardMedecin() {}

    public DashboardMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public void afficherDashboard() {
        System.out.println("=== Dashboard Médecin ===");
        medecin.afficherDetails();
    }
}