package com.diabcare.models;

import java.time.LocalDateTime;

public abstract class Utilisateur {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private LocalDateTime dateCreation;
    private boolean estActif;

    public Utilisateur() {
        this.dateCreation = LocalDateTime.now();
        this.estActif = true;
    }

    public Utilisateur(String id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateCreation = LocalDateTime.now();
        this.estActif = true;
    }

    public boolean seConnecter(String email, String motDePasse) {
        if (this.email.equals(email) && this.motDePasse.equals(motDePasse) && this.estActif) {
            System.out.println("  [OK] Connexion reussie : " + prenom + " " + nom);
            return true;
        }
        System.out.println("  [ERREUR] Echec connexion pour : " + email);
        return false;
    }

    public void seDeconnecter() {
        System.out.println("  [OK] Deconnexion de " + prenom + " " + nom);
    }

    public boolean validerEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean validerMotDePasse(String motDePasse) {
        if (motDePasse == null || motDePasse.length() < 8) return false;
        boolean majuscule = false, chiffre = false;
        for (char c : motDePasse.toCharArray()) {
            if (Character.isUpperCase(c)) majuscule = true;
            if (Character.isDigit(c)) chiffre = true;
        }
        return majuscule && chiffre;
    }

    public abstract void afficherDetails();

    // ── main de démonstration ────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  Utilisateur — Demonstration des methodes communes");
        System.out.println("============================================================");

        // On utilise Administrateur pour instancier (Utilisateur est abstraite)
        Administrateur u = new Administrateur("U01", "Trabelsi", "Leila",
                "leila@email.tn", "LeIla2025");

        System.out.println("\n--- Validation email ---");
        System.out.println("  leila@email.tn  valide : " + u.validerEmail("leila@email.tn"));
        System.out.println("  emailsansarobase        : " + u.validerEmail("emailsansarobase"));
        System.out.println("  @sansdomaine            : " + u.validerEmail("@sansdomaine"));

        System.out.println("\n--- Validation mot de passe ---");
        System.out.println("  LeIla2025  (valide)  : " + u.validerMotDePasse("LeIla2025"));
        System.out.println("  faible     (court)   : " + u.validerMotDePasse("faible"));
        System.out.println("  sansMAJ123 (min OK)  : " + u.validerMotDePasse("sansMAJ123"));
        System.out.println("  SansCHIFFRE(no digit): " + u.validerMotDePasse("SansChiffre"));

        System.out.println("\n--- Connexion ---");
        u.seConnecter("leila@email.tn", "LeIla2025");
        u.seConnecter("leila@email.tn", "mauvais");

        System.out.println("\n--- Deconnexion ---");
        u.seDeconnecter();

        System.out.println("\n--- Desactivation compte ---");
        u.setEstActif(false);
        System.out.println("  Compte actif : " + u.isEstActif());
        u.seConnecter("leila@email.tn", "LeIla2025"); // doit echouer

        System.out.println("\n--- Details ---");
        u.afficherDetails();

        System.out.println("============================================================");
        System.out.println("  Utilisateur — Demonstration terminee");
        System.out.println("============================================================");
    }

    // ── Getters / Setters ────────────────────────────────────────
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
    public void setDateCreation(LocalDateTime d) { this.dateCreation = d; }
    public boolean isEstActif() { return estActif; }
    public void setEstActif(boolean estActif) { this.estActif = estActif; }

    @Override
    public String toString() {
        return prenom + " " + nom + " <" + email + ">";
    }
}
