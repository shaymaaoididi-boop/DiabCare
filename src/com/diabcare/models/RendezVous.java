package com.diabcare.models;

public class RendezVous {
    private Medecin medecin;
    private String date;
    private String heure;
    private String statut; // "En attente", "Confirmé", "Annulé"

    public RendezVous(Medecin medecin, String date, String heure) {
        this.medecin = medecin;
        this.date = date;
        this.heure = heure;
        this.statut = "En attente";
    }

    public void confirmer() {
        this.statut = "Confirmé";
    }

    public void annuler() {
        this.statut = "Annulé";
    }

    public String getRecapitulatif() {
        return "Médecin: " + medecin.getNom() +
               "\nSpécialité: " + medecin.getSpecialite() +
               "\nDate: " + date +
               "\nHeure: " + heure +
               "\nStatut: " + statut;
    }
}