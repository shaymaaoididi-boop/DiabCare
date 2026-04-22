package com.DiabCare.models;

public class NotificationService {

    public static void envoyer(int patientId, String message) {
        System.out.println("📩 Notification envoyée au patient " 
            + patientId + " : " + message);
    }
}