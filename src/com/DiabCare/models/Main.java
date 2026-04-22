package com.DiabCare.models;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DiabCare — Sprint 1 ===");

        System.out.println("--- Alertes & Notifications (Ines) ---");

        Alerte a1 = new Alerte(1, 65, 101);
        a1.verifierSeuil();
        a1.afficherAlerte();
        a1.envoyerNotification();

        System.out.println("----------------");

        Alerte a2 = new Alerte(2, 300, 102);
        a2.verifierSeuil();
        a2.afficherAlerte();
        a2.envoyerNotification();
    }
}