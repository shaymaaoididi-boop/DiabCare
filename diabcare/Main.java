package com.diabcare.models;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== DiabCare ===");

        RendezVous rdv = new RendezVous("20/04/2026");
        RendezVousService service = new RendezVousService();

        service.demanderRDV(rdv);
    }
