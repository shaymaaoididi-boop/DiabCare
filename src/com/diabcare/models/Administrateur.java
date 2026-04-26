package com.diabcare.models;

import java.util.List;
import java.util.stream.Collectors;

public class Administrateur extends Utilisateur {

    // ── Champs prives ─────────────────────────────────────────────
    private String niveauAcces;

    // ── Constructeur vide ─────────────────────────────────────────
    public Administrateur() {
        super();
        this.niveauAcces = "SUPER_ADMIN";
    }

    // ── Constructeur complet ──────────────────────────────────────
    public Administrateur(String id, String nom, String prenom,
                          String email, String motDePasse) {
        super(id, nom, prenom, email, motDePasse);
        this.niveauAcces = "SUPER_ADMIN";
    }

    // ════════════════════════════════════════════════════════════════
    // US-10 — Statistiques globales
    // ════════════════════════════════════════════════════════════════

    /**
     * Affiche le tableau de bord global de la plateforme (US-10).
     * Critere 30 : nb patients, mesures today, alertes critiques.
     * Critere 31 : liste des alertes non traitees avec nom patient.
     */
    public void afficherStatistiquesGlobales(List<Patient> tousPatients,
                                              List<Alerte> toutesAlertes) {
        // Calculs
        long alertesActives   = toutesAlertes.stream()
                .filter(a -> !a.isTraitee()).count();
        long alertesCritiques = toutesAlertes.stream()
                .filter(a -> !a.isTraitee()
                        && "CRITIQUE".equals(a.getNiveauGravite())).count();
        long alertesHypo      = toutesAlertes.stream()
                .filter(a -> !a.isTraitee()
                        && Alerte.TYPE_HYPOGLYCEMIE.equals(a.getTypeAlerte())).count();
        long alertesHyper     = toutesAlertes.stream()
                .filter(a -> !a.isTraitee()
                        && Alerte.TYPE_HYPERGLYCEMIE.equals(a.getTypeAlerte())).count();
        int nbMesuresToday    = tousPatients.stream()
                .mapToInt(p -> p.consulterHistorique(1).size()).sum();
        long nbPatientsActifs = tousPatients.stream()
                .filter(Utilisateur::isEstActif).count();

        System.out.println("  +----------------------------------------------------+");
        System.out.println("  | TABLEAU DE BORD ADMINISTRATEUR                     |");
        System.out.println("  +----------------------------------------------------+");
        System.out.println("  | Patients inscrits       : " + tousPatients.size()
                + " (" + nbPatientsActifs + " actifs)");
        System.out.println("  | Mesures aujourd'hui     : " + nbMesuresToday);
        System.out.println("  | Alertes actives         : " + alertesActives);
        System.out.println("  |   -> Hypoglycemies      : " + alertesHypo);
        System.out.println("  |   -> Hyperglycemies     : " + alertesHyper);
        System.out.println("  |   -> CRITIQUES          : " + alertesCritiques);
        System.out.println("  +----------------------------------------------------+");

        // Critere 31 : liste alertes non traitees
        List<Alerte> nonTraitees = toutesAlertes.stream()
                .filter(a -> !a.isTraitee())
                .collect(Collectors.toList());

        if (!nonTraitees.isEmpty()) {
            System.out.println("  Alertes non traitees (" + nonTraitees.size() + ") :");
            for (Alerte a : nonTraitees) {
                System.out.println("    [" + a.getNiveauGravite() + "] "
                        + a.getPatient().getPrenom() + " "
                        + a.getPatient().getNom()
                        + " : " + a.getMessage());
            }
        } else {
            System.out.println("  Aucune alerte non traitee.");
        }
        System.out.println("  +----------------------------------------------------+");
    }

    // ════════════════════════════════════════════════════════════════
    // Gestion des comptes utilisateurs
    // ════════════════════════════════════════════════════════════════

    /**
     * Desactive le compte d un utilisateur.
     */
    public void desactiverCompte(Utilisateur u) {
        if (!u.isEstActif()) {
            System.out.println("  [INFO] Compte deja desactive : " + u.getEmail());
            return;
        }
        u.setEstActif(false);
        System.out.println("  [OK] Compte desactive : "
                + u.getPrenom() + " " + u.getNom()
                + " (" + u.getEmail() + ")");
    }

    /**
     * Reactive le compte d un utilisateur.
     */
    public void reactiverCompte(Utilisateur u) {
        if (u.isEstActif()) {
            System.out.println("  [INFO] Compte deja actif : " + u.getEmail());
            return;
        }
        u.setEstActif(true);
        System.out.println("  [OK] Compte reactive : "
                + u.getPrenom() + " " + u.getNom()
                + " (" + u.getEmail() + ")");
    }

    /**
     * Retourne la liste des patients inactifs.
     */
    public List<Patient> listerPatientsInactifs(List<Patient> tousPatients) {
        return tousPatients.stream()
                .filter(p -> !p.isEstActif())
                .collect(Collectors.toList());
    }

    /**
     * Marque une alerte comme traitee.
     */
    public void traiterAlerte(Alerte a) {
        a.marquerTraitee();
    }

    /**
     * Traite toutes les alertes actives d un patient.
     */
    public void traiterToutesAlertesPatient(Patient patient,
                                             List<Alerte> toutesAlertes) {
        long count = toutesAlertes.stream()
                .filter(a -> !a.isTraitee()
                        && a.getPatient().getId().equals(patient.getId()))
                .peek(Alerte::marquerTraitee)
                .count();
        System.out.println("  [OK] " + count + " alerte(s) traitee(s) pour "
                + patient.getPrenom() + " " + patient.getNom());
    }

    @Override
    public void afficherDetails() {
        System.out.println("  Admin : " + getPrenom() + " " + getNom()
                + " | Acces : " + niveauAcces
                + " | Email : " + getEmail()
                + " | Actif : " + isEstActif());
    }

    // ── Getters / Setters ─────────────────────────────────────────
    public String getNiveauAcces() { return niveauAcces; }
    public void setNiveauAcces(String n) { this.niveauAcces = n; }
}
