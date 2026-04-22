package com.DiabCare.models;

public class Notification {

    String contenu;

    public Notification(String contenu) {
        this.contenu = contenu;
    }

    public void afficherNotification() {
        System.out.println("Notification : " + contenu);
    }
}