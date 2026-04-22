package com.DiabCare.models;

import java.util.ArrayList;
import java.util.List;

public class Alerte {

    private int id;
    private double valeur;
    private TypeAlerte type;
    private String message;
    private int patientId;

    
    private static List<String> historique = new ArrayList<>();

    public Alerte(int id, double valeur, int patientId) {
        this.id = id;
        this.valeur = valeur;
        this.patientId = patientId;
    }

    
    public TypeAlerte analyser() {

        if (valeur < 70) {
            type = TypeAlerte.HYPOGLYCEMIE;
            message = "Danger: glycémie trop basse";
        } 
        else if (valeur <= 180) {
            type = TypeAlerte.HYPERGLYCEMIE_MODEREE;
            message = "Attention: glycémie élevée";
        } 
        else {
            type = TypeAlerte.HYPERGLYCEMIE_SEVERE;
            message = "URGENT: hyperglycémie sévère";
        }

        return type;
    }

    
    public void traiter() {

        analyser();

        String log = "Alerte #" + id +
                " | Patient " + patientId +
                " | Type: " + type +
                " | Valeur: " + valeur;

        System.out.println(log);

        
        historique.add(log);

       
        NotificationService.envoyer(patientId, message, type);
    }

    public static void afficherHistorique() {

        System.out.println("===== HISTORIQUE ALERTES =====");

        for (String h : historique) {
            System.out.println(h);
        }
    }
}