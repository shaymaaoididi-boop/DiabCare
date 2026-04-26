<<<<<<< HEAD
package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DiabCare Sprint 2 ===");

        // Médecins
        Medecin m1 = new Medecin("Dr Fatma Karoui", "Endocrinologue", "Tunis");
        Medecin m2 = new Medecin("Dr Mehdi Touati", "Nutritionniste", "Manar");

        List<Medecin> medecins = new ArrayList<>();
        medecins.add(m1);
        medecins.add(m2);

        // Services
        RendezVousService service = new RendezVousService();

        // Patient
        Patient p1 = new Patient("Ali Ben Ali");

        System.out.println("\n--- Recherche Médecin ---");
        List<Medecin> resultats = service.rechercher(medecins, "Endocrinologue");

        for (Medecin m : resultats) {
            System.out.println("Trouvé: " + m);
        }

        System.out.println("\n--- Création RDV ---");
        RendezVous rdv1 = service.demanderRDV(p1, m1, "22/04/2026", "10:00");

        if (rdv1 != null) {
            System.out.println(rdv1.getRecapitulatif());
        }

        System.out.println("\n--- Confirmation RDV ---");
        rdv1.confirmer();
        System.out.println(rdv1.getRecapitulatif());

        System.out.println("\n--- Annulation exemple ---");
        rdv1.annuler();
        System.out.println(rdv1.getRecapitulatif());

        System.out.println("\n--- Tous les RDV ---");
        service.afficherRDV();
    }
}
=======
System.out.println("--- Dashboard Médecin (Yassmine) ---");

Patient p1 = new Patient("Khalil", "k@mail.com", "123");
p1.setDerniereMesure(18.5);

Patient p2 = new Patient("Amira", "a@mail.com", "123");
p2.setDerniereMesure(7.2);

Patient p3 = new Patient("Leila", "l@mail.com", "123");
p3.setDerniereMesure(14.0);

Medecin med = new Medecin("Dr. Yassmine");

med.ajouterPatient(p1);
med.ajouterPatient(p2);
med.ajouterPatient(p3);

med.afficherDashboard();
>>>>>>> 4c8e9f207bb0f56808005cb6e644ceaebe84d75e
