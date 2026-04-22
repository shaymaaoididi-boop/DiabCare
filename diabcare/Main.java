package com.diabcare.models;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== DiabCare ===");

        RendezVousService service = new RendezVousService();

        RendezVous rdv1 = new RendezVous(1, "20/04/2026");
        RendezVous rdv2 = new RendezVous(2, "21/04/2026");

        service.demanderRDV(rdv1);
        service.demanderRDV(rdv2);

        service.consulterRDV();
    }
}
