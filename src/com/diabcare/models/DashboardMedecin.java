package com.diabcare.models;

public class DashboardMedecin {

    private Medecin medecin;

    public DashboardMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public void afficherDashboard() {
        System.out.println("\n=== DASHBOARD MÉDECIN ===");
        medecin.afficherDetails();

        System.out.println("\n📊 Statistiques globales :");
        System.out.println("Nombre de patients : " +
                medecin.getPatients().size());

        medecin.analyserPatients();
    }
}