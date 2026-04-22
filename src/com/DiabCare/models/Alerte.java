package com.DiabCare.models;

public class Alerte {

    private int id;
    private double valeur;
    private TypeAlerte type;
    private String message;
    private int patientId;

    
    public Alerte(int id, double valeur, int patientId) {
        this.id = id;
        this.valeur = valeur;
        this.patientId = patientId;
    }

    
    public void verifierSeuil() {

        if (valeur < 70) {
            type = TypeAlerte.HYPOGLYCEMIE;
            message = "⚠️ Hypoglycémie détectée";
        } 
        else if (valeur <= 250) {
            type = TypeAlerte.HYPERGLYCEMIE_LEGERE;
            message = "⚠️ Hyperglycémie légère";
        } 
        else {
            type = TypeAlerte.HYPERGLYCEMIE_SEVERE;
            message = "🚨 Hyperglycémie sévère";
        }
    }

    public void afficherAlerte() {
        System.out.println("Alerte #" + id + 
            " | Type: " + type + 
            " | Valeur: " + valeur);
    }

    public void envoyerNotification() {
        NotificationService.envoyer(patientId, message);
    }
}