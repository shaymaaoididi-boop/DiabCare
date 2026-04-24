package com.diabcare;

import com.diabcare.models.*;

public class Main {

    public static void main(String[] args) {

        // — Création historique patient
        HistoriqueGlycemie historique = new HistoriqueGlycemie(1);

        // — Création des mesures
        MesureGlycemique m1 = new MesureGlycemique(
                1, 5.2, "mmol/L", "A_JEUN", "2026-04-20 08:00", 1);

        MesureGlycemique m2 = new MesureGlycemique(
                2, 8.5, "mmol/L", "APRES_REPAS", "2026-04-20 12:30", 1);

        MesureGlycemique m3 = new MesureGlycemique(
                3, 3.5, "mmol/L", "A_JEUN", "2026-04-21 07:00", 1);

        MesureGlycemique m4 = new MesureGlycemique(
                4, 14.5, "mmol/L", "APRES_REPAS", "2026-04-21 13:00", 1);

        MesureGlycemique m5 = new MesureGlycemique(
                5, 6.1, "mmol/L", "A_JEUN", "2026-04-22 08:00", 1);

        // — Ajout des mesures
        System.out.println("===== ENREGISTREMENT DES MESURES =====");
        historique.ajouterMesure(m1);
        historique.ajouterMesure(m2);
        historique.ajouterMesure(m3);
        historique.ajouterMesure(m4);
        historique.ajouterMesure(m5);

        // — Affichage historique
        historique.afficherHistorique();

        // — Bilan
        historique.genererBilanSemaine();

        // — Filtre A_JEUN
        System.out.println("\n===== FILTRE A JEUN =====");
        for (MesureGlycemique m : historique.filtrerParContexte("A_JEUN")) {
            m.afficherDetails();
        }
    }
}