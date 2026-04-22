package com.diabcare.models;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== DiabCare - Gestion des Rendez-vous ===");

        // Création médecins
        Medecin m1 = new Medecin("Dr Fatma Karoui", "Endocrinologue", "Tunis");
        Medecin m2 = new Medecin("Dr Mehdi Touati", "Nutritionniste", "Manar");

        // Service
        RendezVousService service = new RendezVousService();

        // Demande de RDV
        RendezVous rdv1 = service.demanderRDV(m1, "22/04/2026", "10:00");

        // Récapitulatif (comme dans ta maquette)
        System.out.println("\n--- Récapitulatif ---");
        System.out.println(rdv1.getRecapitulatif());

        // Confirmation
        rdv1.confirmer();

        System.out.println("\n--- Après confirmation ---");
        System.out.println(rdv1.getRecapitulatif());

        // Afficher tous les RDV
        System.out.println("\n--- Liste des rendez-vous ---");
        service.afficherRDV();
    }
}