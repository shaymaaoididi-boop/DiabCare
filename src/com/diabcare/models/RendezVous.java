package com.diabcare.models;

public class RendezVous {

    private Patient patient;
    private Medecin medecin;
    private String date;
    private String heure;
    private String statut;

    public RendezVous(Patient patient, Medecin medecin, String date, String heure) {
        this.patient = patient;
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

    public String getStatut() {
        return statut;
    }

    public String getRecapitulatif() {
        return "Patient: " + patient.getNom() +
                "\nMédecin: " + medecin.getNom() +
                "\nSpécialité: " + medecin.getSpecialite() +
                "\nDate: " + date +
                "\nHeure: " + heure +
                "\nStatut: " + statut;
    }
}