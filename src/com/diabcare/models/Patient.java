package com.diabcare.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représente un patient diabétique inscrit sur DiabCare.
 * US-01, US-02, US-03, US-04
 * Responsable : Ahmed (feature/auth-profil)
 *
 * PROMPT IA utilisé :
 * "Génère une classe Java Patient avec validation email/mot de passe,
 *  historique des mesures glycémiques, calcul de moyenne et filtrage
 *  par contexte. Package com.diabcare.models."
 */
public class Patient extends Utilisateur {

    private LocalDate dateNaissance;
    private String typesDiabete;
    private double poidsKg;
    private String medicamentsEnCours;
    private Medecin medecinReferent;
    private List<MesureGlycemique> historiqueMesures;
    private List<String> historiqueModifications;
    private static List<String> emailsInscrits = new ArrayList<>();

    public Patient() {
        super();
        this.historiqueMesures = new ArrayList<>();
        this.historiqueModifications = new ArrayList<>();
    }

    public Patient(String id, String nom, String prenom, String email,
                   String motDePasse, LocalDate dateNaissance,
                   String typesDiabete, double poidsKg) {
        super(id, nom, prenom, email, motDePasse);
        this.dateNaissance = dateNaissance;
        this.typesDiabete = typesDiabete;
        this.poidsKg = poidsKg;
        this.historiqueMesures = new ArrayList<>();
        this.historiqueModifications = new ArrayList<>();
    }

    /** Inscription avec validation complète (US-01). */
    public boolean sInscrire() {
        if (!validerEmail(getEmail())) {
            System.out.println("  [ERREUR] Email invalide : " + getEmail());
            return false;
        }
        if (!validerMotDePasse(getMotDePasse())) {
            System.out.println("  [ERREUR] Mot de passe trop faible");
            return false;
        }
        if (emailsInscrits.contains(getEmail())) {
            System.out.println("  [ERREUR] Email deja utilise : " + getEmail());
            return false;
        }
        emailsInscrits.add(getEmail());
        System.out.println("  [OK] Compte cree : " + getPrenom() + " " + getNom()
                + " (" + typesDiabete + ")");
        return true;
    }

    /** Mise à jour du profil médical avec historique (US-02). */
    public void mettreAJourProfil(double nouveauPoids, String nouveauxMedicaments,
                                   Medecin nouveauMedecin) {
        String modif = "[" + LocalDateTime.now().toLocalDate() + "] "
                + "Poids: " + this.poidsKg + " -> " + nouveauPoids + " kg";
        this.poidsKg = nouveauPoids;
        this.medicamentsEnCours = nouveauxMedicaments;
        if (nouveauMedecin != null) this.medecinReferent = nouveauMedecin;
        historiqueModifications.add(modif);
        System.out.println("  [OK] Profil mis a jour : " + modif);
    }

    /** Enregistrement d'une mesure avec validation (US-03). */
    public boolean enregistrerMesure(MesureGlycemique mesure) {
        if (!mesure.estDansPlagePhysiologique()) {
            System.out.println("  [ERREUR] Valeur hors plage (" + mesure.getValeurMmolL() + " mmol/L)");
            return false;
        }
        historiqueMesures.add(mesure);
        String flag = mesure.estHypoglycemie() ? " *** HYPOGLYCEMIE ***"
                    : mesure.estHyperglycemie() ? " *** HYPERGLYCEMIE ***" : "";
        System.out.println("  [OK] " + mesure.getValeurMmolL() + " mmol/L ["
                + mesure.getContexte() + "]" + flag);
        return true;
    }

    /** Historique N jours trié décroissant (US-04). */
    public List<MesureGlycemique> consulterHistorique(int nbJours) {
        LocalDateTime limite = LocalDateTime.now().minusDays(nbJours);
        return historiqueMesures.stream()
                .filter(m -> m.getDateHeure().isAfter(limite))
                .sorted(Comparator.comparing(MesureGlycemique::getDateHeure).reversed())
                .collect(Collectors.toList());
    }

    /** Filtrage par contexte (US-04). */
    public List<MesureGlycemique> filtrerParContexte(String contexte) {
        return historiqueMesures.stream()
                .filter(m -> m.getContexte().equalsIgnoreCase(contexte))
                .sorted(Comparator.comparing(MesureGlycemique::getDateHeure).reversed())
                .collect(Collectors.toList());
    }

    /** Moyenne glycémique sur N jours. */
    public double calculerMoyenne(int nbJours) {
        List<MesureGlycemique> mesures = consulterHistorique(nbJours);
        if (mesures.isEmpty()) return 0.0;
        double somme = mesures.stream().mapToDouble(MesureGlycemique::getValeurMmolL).sum();
        return Math.round((somme / mesures.size()) * 100.0) / 100.0;
    }

    /** Statut glycémique global. */
    public String getStatutGlycemique() {
        double moy = calculerMoyenne(7);
        if (moy == 0) return "Aucune donnee";
        if (moy < 3.9) return "HYPOGLYCEMIE";
        if (moy > 13.9) return "HYPERGLYCEMIE";
        if (moy >= 4.0 && moy <= 7.0) return "OPTIMAL";
        return "A SURVEILLER";
    }

    @Override
    public void afficherDetails() {
        System.out.println("  Patient       : " + getPrenom() + " " + getNom());
        System.out.println("  Email         : " + getEmail());
        System.out.println("  Diabete       : " + typesDiabete);
        System.out.println("  Poids         : " + poidsKg + " kg");
        System.out.println("  Medicaments   : " + (medicamentsEnCours != null ? medicamentsEnCours : "Non renseigne"));
        System.out.println("  Medecin       : " + (medecinReferent != null
                ? "Dr. " + medecinReferent.getPrenom() + " " + medecinReferent.getNom() : "Non assigne"));
        System.out.println("  Mesures (30j) : " + consulterHistorique(30).size());
        System.out.println("  Moyenne (7j)  : " + calculerMoyenne(7) + " mmol/L");
        System.out.println("  Statut        : " + getStatutGlycemique());
    }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }  
    public Medecin getMedecin() { return medecinReferent; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate d) { this.dateNaissance = d; }
    public String getTypesDiabete() { return typesDiabete; }
    public void setTypesDiabete(String t) { this.typesDiabete = t; }
    public double getPoidsKg() { return poidsKg; }
    public void setPoidsKg(double p) { this.poidsKg = p; }
    public String getMedicamentsEnCours() { return medicamentsEnCours; }
    public void setMedicamentsEnCours(String m) { this.medicamentsEnCours = m; }
    public Medecin getMedecinReferent() { return medecinReferent; }
    public void setMedecinReferent(Medecin m) { this.medecinReferent = m; }
    public List<MesureGlycemique> getHistoriqueMesures() { return historiqueMesures; }
    public List<String> getHistoriqueModifications() { return historiqueModifications; }
    public static List<String> getEmailsInscrits() { return emailsInscrits; }
}