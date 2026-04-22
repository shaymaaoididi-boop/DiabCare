package com.DiabCare.models;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DiabCare — SPRINT 2 ===");

        Alerte a1 = new Alerte(1, 65, 101);
        a1.traiter();

        Alerte a2 = new Alerte(2, 150, 102);
        a2.traiter();

        Alerte a3 = new Alerte(3, 300, 103);
        a3.traiter();

        System.out.println("\n📊 AFFICHAGE HISTORIQUE:");
        Alerte.afficherHistorique();
    }
}