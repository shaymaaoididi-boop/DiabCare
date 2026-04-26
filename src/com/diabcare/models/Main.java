package com.diabcare.models;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DIABCARE — SPRINT 2 FINAL ===");

        // 👨‍⚕️ Création médecin
        Medecin medecin = new Medecin(1, "Yasmine", "yasmine@mail.com");

        // 👥 Création patients
        Patient p1 = new Patient(1, "Ahmed", 1.8);
        Patient p2 = new Patient(2, "Sara", 2.3);
        Patient p3 = new Patient(3, "Ali", 1.2);

        // ➕ Ajout patients
        medecin.ajouterPatient(p1);
        medecin.ajouterPatient(p2);
        medecin.ajouterPatient(p3);

        // 📊 Dashboard
        medecin.afficherDashboard();

        // 🔍 Analyse initiale
        medecin.analyserPatients();

        // 🔄 Simulation
        System.out.println("\n🔄 Mise à jour glycémie...");
        p1.mettreAJourGlycemie(2.1);

        // 📈 Nouvelle analyse
        System.out.println("\n📊 Nouvelle analyse :");
        medecin.analyserPatients();

        System.out.println("\n=== FIN DÉMONSTRATION ===");
    }
}