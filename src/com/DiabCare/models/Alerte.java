package com.DiabCare.models;

public class Alerte {

    String message;

    public Alerte(String message) {
        this.message = message;
    }

    public void afficherAlerte() {
        System.out.println("Alerte : " + message);
    }
}