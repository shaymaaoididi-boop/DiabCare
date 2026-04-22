package com.diabcare.models;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DiabCare — Sprint 1 ===");

        // --- Dashboard Médecin (Yasmine) ---
        System.out.println("--- Dashboard Médecin (Yasmine) ---");

        Medecin m1 = new Medecin(1, "Dr Yasmine", "yasmine@mail.com");
        DashboardMedecin dashboard = new DashboardMedecin(m1);

        dashboard.afficherDashboard();

        // --- Suivi Patient ---
        System.out.println("--- Suivi Patient ---");

        Patient p1 = new Patient(1, "Ahmed", 1.8);
        p1.afficherDetails();

        System.out.println("=== Fin démo ===");
    }
}