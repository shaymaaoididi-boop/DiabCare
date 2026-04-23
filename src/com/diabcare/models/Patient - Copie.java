package com.diabcare.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe Patient — Feature : Authentification & Profil
 * Responsable : Ahmed (feature/auth-profil)
 * Sprint 2 — Logique metier complete
 *
 * US-01 : Inscription securisee avec validation complete
 * US-02 : Modification du profil medical avec historique
 *
 * PROMPT IA utilise :
 * "Genere une classe Java Patient avec inscription securisee,
 *  validation email/mot de passe, verification unicite email,
 *  modification de profil avec historique horodate.
 *  Package com.diabcare.models."
 *
 * CORRECTIONS APPORTEES :
 * - Ajout emailsInscrits.contains() pour unicite email
 * - Messages d erreur explicites par System.out.println
 * - Historique modifications horodate avec ancienne/nouvelle valeur
 * - getStatutGlycemique() avec 4 niveaux cliniques
 */
public class Patient extends Utilisateur {

    // ── Champs prives ─────────────────────────────────────────────
    private LocalDate dateNaissance;
    private String typesDiabete;
    private double poidsKg;
    private String medicamentsEnCours;
    private Medecin medecinReferent;
    private List<MesureGlycemique> historiqueMesures;
    private List<String> historiqueModifications;

    // Liste statique simulant la base de donnees des emails
    private static List<String> emailsInscrits = new ArrayList<>();

    // ── Constructeur vide ─────────────────────────────────────────
    public Patient() {
        super();
        this.historiqueMesures    = new ArrayList<>();
        this.historiqueModifications = new ArrayList<>();
    }

    // ── Constructeur complet ──────────────────────────────────────
    public Patient(String id, String nom, String prenom, String email,
                   String motDePasse, LocalDate dateNaissance,
                   String typesDiabete, double poidsKg) {
        super(id, nom, prenom, email, motDePasse);
        this.dateNaissance    = dateNaissance;
        this.typesDiabete     = typesDiabete;
        this.poidsKg          = poidsKg;
        this.historiqueMesures       = new ArrayList<>();
        this.historiqueModifications = new ArrayList<>();
    }

    // ════════════════════════════════════════════════════════════════
    // US-01 — Inscription securisee
    // ════════════════════════════════════════════════════════════════

    /**
     * Inscrit le patient apres validation complete.
     * Verifie : format email, complexite MDP, unicite email.
     * @return true si inscription reussie, false sinon
     */
    public boolean sInscrire() {
        // Validation email
        if (!validerEmail(getEmail())) {
            System.out.println("  [ERREUR] Email invalide : " + getEmail()
                    + " (doit contenir @ et .)");
            return false;
        }
        // Validation mot de passe
        if (!validerMotDePasse(getMotDePasse())) {
            System.out.println("  [ERREUR] Mot de passe trop faible : min 8 caracteres,"
                    + " 1 majuscule, 1 chiffre");
            return false;
        }
        // Unicite email — critere 2 US-01
        if (emailsInscrits.contains(getEmail())) {
            System.out.println("  [ERREUR] Email deja utilise : " + getEmail());
            return false;
        }
        // Tout est valide — inscription
        emailsInscrits.add(getEmail());
        System.out.println("  [OK] Compte cree avec succes !");
        System.out.println("       Bienvenue " + getPrenom() + " " + getNom()
                + " | Diabete : " + typesDiabete
                + " | Poids : " + poidsKg + " kg");
        return true;
    }

    /**
     * Verifie si un email est deja enregistre dans le systeme.
     * @return true si email deja utilise
     */
    public static boolean emailDejaUtilise(String email) {
        return emailsInscrits.contains(email);
    }

    // ════════════════════════════════════════════════════════════════
    // US-02 — Modification du profil medical
    // ════════════════════════════════════════════════════════════════

    /**
     * Met a jour le profil medical du patient.
     * Conserve un historique horodate de chaque modification.
     * @param nouveauPoids       nouveau poids en kg
     * @param nouveauxMedicaments liste des medicaments en cours
     * @param nouveauMedecin     nouveau medecin referent (null = pas de changement)
     */
    public void mettreAJourProfil(double nouveauPoids,
                                   String nouveauxMedicaments,
                                   Medecin nouveauMedecin) {
        StringBuilder modif = new StringBuilder();
        modif.append("[").append(LocalDateTime.now()).append("] ");
        boolean changementDetecte = false;

        // Modification poids
        if (Double.compare(nouveauPoids, this.poidsKg) != 0) {
            modif.append("Poids : ").append(this.poidsKg)
                 .append(" -> ").append(nouveauPoids).append(" kg | ");
            this.poidsKg = nouveauPoids;
            changementDetecte = true;
        }

        // Modification medicaments
        if (nouveauxMedicaments != null
                && !nouveauxMedicaments.equals(this.medicamentsEnCours)) {
            modif.append("Meds : [")
                 .append(this.medicamentsEnCours != null
                         ? this.medicamentsEnCours : "aucun")
                 .append("] -> [").append(nouveauxMedicaments).append("] | ");
            this.medicamentsEnCours = nouveauxMedicaments;
            changementDetecte = true;
        }

        // Modification medecin referent
        if (nouveauMedecin != null && nouveauMedecin != this.medecinReferent) {
            modif.append("Medecin : ")
                 .append(this.medecinReferent != null
                         ? "Dr. " + this.medecinReferent.getNom() : "aucun")
                 .append(" -> Dr. ")
                 .append(nouveauMedecin.getNom());
            this.medecinReferent = nouveauMedecin;
            changementDetecte = true;
        }

        if (!changementDetecte) {
            System.out.println("  [INFO] Aucun changement detecte.");
            return;
        }

        if (modif.toString().endsWith(" | ")) {
            modif.setLength(modif.length() - 3);
        }

        historiqueModifications.add(modif.toString());
        System.out.println("  [OK] Profil mis a jour : " + modif);
    }

    /**
     * Affiche tout l historique des modifications du profil (US-02 critere 6).
     */
    public void afficherHistoriqueModifications() {
        if (historiqueModifications.isEmpty()) {
            System.out.println("  Aucune modification enregistree.");
            return;
        }
        System.out.println("  Historique des modifications ("
                + historiqueModifications.size() + ") :");
        for (String m : historiqueModifications) {
            System.out.println("    >> " + m);
        }
    }

    // ════════════════════════════════════════════════════════════════
    // Methodes d appui (utilisees par autres features)
    // ════════════════════════════════════════════════════════════════

    /** Enregistre une mesure apres validation plage physiologique. */
    public boolean enregistrerMesure(MesureGlycemique mesure) {
        if (!mesure.estDansPlagePhysiologique()) {
            System.out.println("  [ERREUR] Valeur hors plage ("
                    + mesure.getValeurMmolL() + " mmol/L) — mesure refusee");
            return false;
        }
        historiqueMesures.add(mesure);
        String flag = mesure.estHypoglycemie()  ? " *** HYPOGLYCEMIE ***"
                    : mesure.estHyperglycemie() ? " *** HYPERGLYCEMIE ***" : "";
        System.out.println("  [OK] " + mesure.getValeurMmolL()
                + " mmol/L [" + mesure.getContexte() + "]" + flag);
        return true;
    }

    /** Retourne les mesures des N derniers jours, triees decroissant. */
    public List<MesureGlycemique> consulterHistorique(int nbJours) {
        LocalDateTime limite = LocalDateTime.now().minusDays(nbJours);
        return historiqueMesures.stream()
                .filter(m -> m.getDateHeure().isAfter(limite))
                .sorted(Comparator.comparing(MesureGlycemique::getDateHeure).reversed())
                .collect(Collectors.toList());
    }

    /** Filtre les mesures par contexte (a jeun, apres repas...). */
    public List<MesureGlycemique> filtrerParContexte(String contexte) {
        return historiqueMesures.stream()
                .filter(m -> m.getContexte().equalsIgnoreCase(contexte))
                .sorted(Comparator.comparing(MesureGlycemique::getDateHeure).reversed())
                .collect(Collectors.toList());
    }

    /** Calcule la moyenne glycemique sur N jours. */
    public double calculerMoyenne(int nbJours) {
        List<MesureGlycemique> mesures = consulterHistorique(nbJours);
        if (mesures.isEmpty()) return 0.0;
        double somme = mesures.stream()
                .mapToDouble(MesureGlycemique::getValeurMmolL).sum();
        return Math.round((somme / mesures.size()) * 100.0) / 100.0;
    }

    /** Statut glycemique global sur 7 jours. */
    public String getStatutGlycemique() {
        double moy = calculerMoyenne(7);
        if (moy == 0)    return "Aucune donnee";
        if (moy < 3.9)   return "HYPOGLYCEMIE";
        if (moy > 13.9)  return "HYPERGLYCEMIE";
        if (moy <= 7.0)  return "OPTIMAL";
        return "A SURVEILLER";
    }

    @Override
    public void afficherDetails() {
        System.out.println("  +--------------------------------------------------+");
        System.out.println("  | PROFIL PATIENT                                   |");
        System.out.println("  +--------------------------------------------------+");
        System.out.println("  | Nom          : " + getPrenom() + " " + getNom());
        System.out.println("  | Email        : " + getEmail());
        System.out.println("  | Naissance    : " + dateNaissance);
        System.out.println("  | Diabete      : " + typesDiabete);
        System.out.println("  | Poids        : " + poidsKg + " kg");
        System.out.println("  | Medicaments  : "
                + (medicamentsEnCours != null ? medicamentsEnCours : "Non renseigne"));
        System.out.println("  | Medecin      : "
                + (medecinReferent != null
                   ? "Dr. " + medecinReferent.getPrenom() + " " + medecinReferent.getNom()
                   : "Non assigne"));
        System.out.println("  | Mesures (30j): " + consulterHistorique(30).size());
        System.out.println("  | Moyenne (7j) : " + calculerMoyenne(7) + " mmol/L");
        System.out.println("  | Statut       : " + getStatutGlycemique());
        System.out.println("  | Compte actif : " + isEstActif());
        System.out.println("  +--------------------------------------------------+");
    }

    // ── Getters / Setters ─────────────────────────────────────────
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
    public static void reinitialiserEmails() { emailsInscrits.clear(); }
}