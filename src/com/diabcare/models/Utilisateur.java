package com.diabcare.models;

import java.time.LocalDateTime;

/**
 * Classe abstraite représentant un utilisateur de la plateforme DiabCare.
 * Classe parente pour Patient, Medecin et Administrateur.
 */
public abstract class Utilisateur {

    // ─── Champs privés ───────────────────────────────────────────────────────
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private LocalDateTime dateCreation;
    private boolean estActif;

    // ─── Constructeur vide ────────────────────────────────────────────────────
    public Utilisateur() {
        this.dateCreation = LocalDateTime.now();
        this.estActif = true;
    }

    // ─── Constructeur complet ─────────────────────────────────────────────────
    public Utilisateur(String id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateCreation = LocalDateTime.now();
        this.estActif = true;
    }

    // ─── Méthodes métier ──────────────────────────────────────────────────────

    /**
     * Connecte l'utilisateur avec email et mot de passe.
     * @return true si connexion réussie
     */
    public boolean seConnecter(String email, String motDePasse) {
        if (this.email.equals(email) && this.motDePasse.equals(motDePasse) && this.estActif) {
            System.out.println("Connexion réussie : " + prenom + " " + nom);
            return true;
        }
        System.out.println("Échec de connexion pour : " + email);
        return false;
    }

    /**
     * Déconnecte l'utilisateur de la session.
     */
    public void seDeconnecter() {
        System.out.println(" Déconnexion de " + prenom + " " + nom);
    }

    /**
     * Valide le format de l'email.
     */
    public boolean validerEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    /**
     * Valide la complexité du mot de passe (min 8 chars, 1 majuscule, 1 chiffre).
     */
    public boolean validerMotDePasse(String motDePasse) {
        if (motDePasse == null || motDePasse.length() < 8) return false;
        boolean majuscule = false, chiffre = false;
        for (char c : motDePasse.toCharArray()) {
            if (Character.isUpperCase(c)) majuscule = true;
            if (Character.isDigit(c)) chiffre = true;
        }
        return majuscule && chiffre;
    }

    /**
     * Affiche les détails de l'utilisateur.
     */
    public abstract void afficherDetails();

    // ─── Getters / Setters ────────────────────────────────────────────────────
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public boolean isEstActif() { return estActif; }
    public void setEstActif(boolean estActif) { this.estActif = estActif; }

    @Override
    public String toString() {
        return prenom + " " + nom + " <" + email + ">";
    }
}