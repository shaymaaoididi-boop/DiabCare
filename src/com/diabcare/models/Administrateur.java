package com.diabcare.models;

import java.util.List;

/**
 * Représente l'administrateur de la plateforme DiabCare.
 * US-10 — Responsable : Ahmed
 */
public class Administrateur extends Utilisateur {

    // ─── Champs privés ────────────────────────────────────────────────────────
    private String niveauAcces;

    // ─── Constructeur vide ────────────────────────────────────────────────────
    public Administrateur() {
        super();
        this.niveauAcces = "SUPER_ADMIN";
    }

    // ─── Constructeur complet ─────────────────────────────────────────────────
    public Administrateur(String id, String nom, String prenom, String email, String motDePasse) {
        super(id, nom, prenom, email, motDePasse);
        this.niveauAcces = "SUPER_ADMIN";
    }

    // ─── Méthodes métier ──────────────────────────────────────────────────────

    /**
     * Affiche les statistiques globales de la plateforme (US-10).
     */
    public void afficherStatistiquesGlobales(List<Patient> tousPatients, List<Alerte> toutesAlertes) {
        int nbMesuresToday = 0;
        for (Patient p : tousPatients) {
            nbMesuresToday += p.consulterHistorique(1).size();
        }
        long alertesActives = toutesAlertes.stream().filter(a -> !a.isTraitee()).count();

        System.out.println("┌─── Tableau de bord Administrateur ────────");
        System.out.println("│ Patients inscrits     : " + tousPatients.size());
        System.out.println("│ Mesures aujourd'hui   : " + nbMesuresToday);
        System.out.println("│ Alertes critiques     : " + alertesActives);
        System.out.println("│ Alertes non traitées  :");
        for (Alerte a : toutesAlertes) {
            if (!a.isTraitee()) {
                System.out.println("│   🔴 " + a.getPatient().getPrenom() + " "
                        + a.getPatient().getNom() + " — " + a.getMessage());
            }
        }
        System.out.println("└───────────────────────────────────────────");
    }

    /**
     * Désactive un compte utilisateur.
     */
    public void desactiverCompte(Utilisateur utilisateur) {
        utilisateur.setEstActif(false);
        System.out.println(" Compte désactivé : " + utilisateur.getEmail());
    }

    /**
     * Réactive un compte utilisateur.
     */
    public void reactiverCompte(Utilisateur utilisateur) {
        utilisateur.setEstActif(true);
        System.out.println(" Compte réactivé : " + utilisateur.getEmail());
    }

    @Override
    public void afficherDetails() {
        System.out.println("┌─── Administrateur ─────────────────────────");
        System.out.println("│ " + getPrenom() + " " + getNom());
        System.out.println("│ Accès : " + niveauAcces);
        System.out.println("└───────────────────────────────────────────");
    }

    // ─── Getters / Setters ────────────────────────────────────────────────────
    public String getNiveauAcces() { return niveauAcces; }
    public void setNiveauAcces(String niveauAcces) { this.niveauAcces = niveauAcces; }
}