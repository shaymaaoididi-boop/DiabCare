package com.diabcare;

import com.diabcare.models.*;
import com.diabcare.enums.ContexteMesure;

public class Main {

    public static void main(String[] args) {

        // ── Création historique patient ─────────────────────────
        HistoriqueGlycemie historique = new HistoriqueGlycemie(1);

        // ── Création des mesures ────────────────────────────────
        MesureGlycemique m1 = new MesureGlycemique(
                1, 5.2, "mmol/L",
                ContexteMesure.A_JEUN,
                "2026-04-20 08:00", 1);

        MesureGlycemique m2 = new MesureGlycemique(
                2, 140, "mg/dL",
                ContexteMesure.APRES_REPAS,
                "2026-04-20 13:00", 1);

        MesureGlycemique m3 = new MesureGlycemique(
                3, 3.2, "mmol/L",
                ContexteMesure.A_JEUN,
                "2026-04-21 07:45", 1);

        MesureGlycemique m4 = new MesureGlycemique(
                4, 15.0, "mmol/L",
                ContexteMesure.APRES_REPAS,
                "2026-04-21 14:10", 1);

        MesureGlycemique m5 = new MesureGlycemique(
                5, 6.8, "mmol/L",
                ContexteMesure.A_JEUN,
                "2026-04-22 08:10", 1);

        // ── Ajouter des notes (Sprint 2) ───────────────────────
        m3.setNotePatient("Fatigue le matin");
        m4.setNotePatient("Repas riche en sucre");

        // ── Ajout dans l’historique ────────────────────────────
        historique.ajouterMesure(m1);
        historique.ajouterMesure(m2);
        historique.ajouterMesure(m3);
        historique.ajouterMesure(m4);
        historique.ajouterMesure(m5);

        // ── Affichage complet ──────────────────────────────────
        System.out.println("\n==============================");
        historique.afficherHistorique();

        // ── Bilan Sprint 2 ─────────────────────────────────────
        System.out.println("\n==============================");
        historique.genererBilanSemaine();

        // ── Tests supplémentaires ──────────────────────────────
        System.out.println("\n===== TESTS =====");

        System.out.println("Dernières 3 mesures :");
        for (MesureGlycemique m : historique.getMesures()) {
            m.afficherDetails();
        }

        System.out.println("\nFiltre A JEUN :");
        for (MesureGlycemique m : historique.filtrerParContexte(ContexteMesure.A_JEUN)) {
            m.afficherDetails();
        }
    }
}