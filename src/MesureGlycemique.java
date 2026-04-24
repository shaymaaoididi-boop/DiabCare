package com.diabcare.models;

/**
 * Encapsule une mesure glycémique saisie par un patient.
 * Responsable : kenza (Scrum Master)
 *
 * Fonctionnalité : Enregistrement & Historique des mesures glycémiques
 *
 *  * Sprint 2 — Logique complète :
 *   - convertirUnite() : conversion bidirectionnelle réelle
 *   - evaluerGlycemie() : évaluation qualitative selon contexte
 *   - estCritique() : détection rapide d'une valeur critique
 *   - getStatutCouleur() : indicateur visuel pour le dashboard
 *
 * Seuils physiologiques (mmol/L) :
 *   - Plage valide     :  1.0 – 35.0
 *   - Normale à jeun   :  4.0 –  5.9
 *   - Hypoglycémie     :  < 3.9
 *   - Hyperglycémie    :  > 10.0
 */
public class MesureGlycemique {

    // Constantes seuils
    public static final double SEUIL_MIN_PHYSIOLOGIQUE  = 1.0;
    public static final double SEUIL_MAX_PHYSIOLOGIQUE  = 35.0;
    public static final double SEUIL_HYPO               = 3.9;
    public static final double SEUIL_NORMAL_HAUT        = 7.8;  // à jeun
    public static final double SEUIL_HYPER_LEGERE       = 10.0;
    public static final double SEUIL_HYPER_SEVERE       = 13.9;

    public static final String A_JEUN = "A_JEUN";
    public static final String AVANT_REPAS = "AVANT_REPAS";
    public static final String APRES_REPAS = "APRES_REPAS";
    public static final String AU_COUCHER = "AU_COUCHER";

    // ── Champs privés ────────────────────────────────────────────────────────
    private int            id;
    private double         valeur;        // valeur en mmol/L (stockage interne)
    private String         unite;         // "mmol/L" ou "mg/dL"
    private String         contexte;
    private String         dateHeure;     // format "AAAA-MM-JJ HH:MM"
    private int            patientId;
    private String         notePatient; 

    // ── Constructeur vide ────────────────────────────────────────────────────
    public MesureGlycemique() {
        this.id        = 0;
        this.valeur    = 0.0;
        this.unite     = "mmol/L";
        this.contexte  = A_JEUN;
        this.dateHeure = "";
        this.patientId = 0;
        this.notePatient = "";
    }

    // ── Constructeur complet ─────────────────────────────────────────────────
   public MesureGlycemique(int id, double valeur, String unite,
                        String contexte, String dateHeure, int patientId) {
    this.id        = id;
    this.unite     = unite;
    this.valeur    = unite.equalsIgnoreCase("mg/dL") ? valeur / 18.0 : valeur;
    this.contexte  = contexte;
    this.dateHeure = dateHeure;
    this.patientId = patientId;
    this.notePatient = "";
}

    // ── Méthodes métier ──────────────────────────────────────────────────────

    /**
     * Vérifie que la valeur est dans la plage physiologique acceptable.
     * @return true si la mesure est valide
     */
    public boolean estValide() {
        return valeur >= SEUIL_MIN_PHYSIOLOGIQUE && valeur <= SEUIL_MAX_PHYSIOLOGIQUE;
    }

    /**
     * Convertit la valeur stockée (mmol/L) vers l'unité souhaitée.
     * @param uniteTarget "mmol/L" ou "mg/dL"
     * @return valeur convertie
     */
    public double convertirUnite(String uniteTarget) {
        if (uniteTarget.equalsIgnoreCase("mg/dL")) {
            return Math.round(valeur * 18.0 * 10.0) / 10.0;
        }
        return Math.round(valeur * 10.0) / 10.0;
    }
     /**

  * Évalue la glycémie et retourne un diagnostic.
  * @return texte décrivant l’état glycémique
    */
    public String evaluerGlycemie() {
    if (valeur < SEUIL_HYPO)
    return "🔵 HYPOGLYCÉMIE";
    if (valeur > SEUIL_HYPER_SEVERE)
    return "🔴 HYPERGLYCÉMIE SÉVÈRE";
    if (valeur > SEUIL_HYPER_LEGERE)
    return "🟠 HYPERGLYCÉMIE LÉGÈRE";
    if (contexte.equalsIgnoreCase(A_JEUN) && valeur <= 5.9)
    return "🟢 NORMAL À JEUN";
    if (contexte.equalsIgnoreCase(APRES_REPAS) && valeur <= SEUIL_NORMAL_HAUT)
    return "🟢 NORMAL POST-PRANDIAL";
    return "🟡 LÉGÈREMENT ÉLEVÉ";
    }
     /**

  * Vérifie si la mesure est critique.
  * @return true si hypo ou hyper sévère
    */
    public boolean estCritique() {
    return valeur < SEUIL_HYPO || valeur > SEUIL_HYPER_SEVERE;
    }
 /**

  * Retourne une couleur pour le dashboard.
  * @return VERT, JAUNE, ORANGE ou ROUGE
    */
    public String getStatutCouleur() {
    if (valeur < SEUIL_HYPO || valeur > SEUIL_HYPER_SEVERE) return "ROUGE";
    if (valeur > SEUIL_HYPER_LEGERE) return "ORANGE";
    if (valeur > SEUIL_NORMAL_HAUT)  return "JAUNE";
    return "VERT";
    }

    /**
     * Affiche les détails de la mesure dans la console.
     */
     public void afficherDetails() {
        System.out.printf("      #%-3d | %5.1f mmol/L (%5.0f mg/dL) | %-14s | %s | %s%n",
                id, valeur, convertirUnite("mg/dL"),
                contexte, dateHeure, evaluerGlycemie());
        if (!notePatient.isEmpty())
            System.out.println("           Note : " + notePatient);
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public int            getId()        { return id; }
    public double         getValeur()    { return valeur; }
    public String         getUnite()     { return unite; }
    public String         getContexte()  { return contexte; }
    public String         getDateHeure() { return dateHeure; }
    public int            getPatientId() { return patientId; }
    public String         getNotePatient() { return notePatient; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setId(int id)                      { this.id = id; }
    public void setValeur(double valeur)           { this.valeur = valeur; }
    public void setUnite(String unite)             { this.unite = unite; }
    public void setContexte(String contexte)       { this.contexte = contexte; }
    public void setDateHeure(String dateHeure)     { this.dateHeure = dateHeure; }
    public void setPatientId(int patientId)        { this.patientId = patientId; }
    public void setNotePatient(String notePatient) { this.notePatient = notePatient; }


   @Override
    public String toString() {
        return String.format("Mesure{#%d, %.1f mmol/L, %s, %s, %s}",
                id, valeur, contexte, evaluerGlycemie(), dateHeure);
    }
}
