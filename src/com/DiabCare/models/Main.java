package com.DiabCare.models;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test projet");
        System.out.println("--- Alertes ---");

        Alerte a = new Alerte("Glycémie élevée !");
        a.afficherAlerte();

        Notification n = new Notification("Prenez votre médicament !");
        n.afficherNotification();
    }
}