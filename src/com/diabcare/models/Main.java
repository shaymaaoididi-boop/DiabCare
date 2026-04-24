package com.diabcare;

import com.diabcare.models.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== DIABCARE — FINAL DEMO =====");

        // =========================
        // 🟢 1. INSCRIPTION + PROFIL
        // =========================
        Patient p1 = new Patient(
                "P001", "Ali", "Ben Ali",
                "ali@gmail.com", "Test1234A",
                LocalDate.of(1998, 5, 10),
                "Type 1", 75.5
        );

        p1.sInscrire();

        Medecin medProfil = new Medecin("M001", "Haddad", "Leila", "Endocrinologue");
        p1.mettreAJourProfil(78.2, "Metformine", medProfil);

        // =========================
        // 🟢 2. DASHBOARD MÉDECIN
        // =========================
        System.out.println("\n=== DASHBOARD ===");

        Medecin medecin = new Medecin(1, "Dr Yasmine", "yasmine@mail.com");

        Patient dp1 = new Patient(1, "Ahmed", 1.8);
        Patient dp2 = new Patient(2, "Sara", 2.3);

        medecin.ajouterPatient(dp1);
        medecin.ajouterPatient(dp2);

        DashboardMedecin dashboard = new DashboardMedecin(medecin);
        dashboard.afficherDashboard();

        // =========================
        // 🟢 3. RENDEZ-VOUS
        // =========================
        System.out.println("\n=== RENDEZ-VOUS ===");


        Medecin m1 = new Medecin("Dr Fatma Karoui", "Endocrinologue", "Tunis");
        List<Medecin> medecins = new ArrayList<>();
        medecins.add(m1);

        Patient patientRDV = new Patient("Ali Ben Ali");

        RendezVous rdv = service.demanderRDV(patientRDV, m1, "22/04/2026", "10:00");

        if (rdv != null) {
            rdv.confirmer();
            System.out.println(rdv.getRecapitulatif());
        }

        // =========================
        // 🟢 4. ALERTES
        // =========================
        System.out.println("\n=== ALERTES ===");

        Alerte a1 = new Alerte(1, 65, 101);
        Alerte a2 = new Alerte(2, 150, 102);

        a1.traiter();
        a2.traiter();

        Alerte.afficherHistorique();

        // =========================
        // 🟢 5. HISTORIQUE GLYCÉMIE
        // =========================
        System.out.println("\n=== HISTORIQUE ===");

        HistoriqueGlycemie historique = new HistoriqueGlycemie(1);

        historique.ajouterMesure(new MesureGlycemique(1, 5.2, "mmol/L", "A_JEUN", "2026-04-20 08:00", 1));
        historique.ajouterMesure(new MesureGlycemique(2, 8.5, "mmol/L", "APRES_REPAS", "2026-04-20 12:30", 1));

        historique.afficherHistorique();
        historique.genererBilanSemaine();

        System.out.println("\n===== FIN DEMO =====");
    }
}