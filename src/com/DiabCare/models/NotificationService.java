package com.DiabCare.models;
import java.time.LocalDateTime;

public class NotificationService {

    public static void envoyer(int patientId, String message, TypeAlerte type) {

        System.out.println("================================");
        System.out.println("📩 NOTIFICATION SYSTEME DIABCARE");
        System.out.println("Patient ID : " + patientId);
        System.out.println("Type : " + type);
        System.out.println("Message : " + message);
        System.out.println("Date : " + LocalDateTime.now());
        System.out.println("Statut : envoyé avec succès");
        System.out.println("================================");
    }
}