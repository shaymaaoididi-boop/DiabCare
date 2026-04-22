package com.diabcare.models;

import com.diabcare.enums.ContexteMesure;

/**
 * Encapsule une mesure glycémique saisie par un patient.
 * Responsable : kenza (Scrum Master)
 *
 * Fonctionnalité : Enregistrement & Historique des mesures glycémiques
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
    public static final double SEUIL_HYPER_LEGERE       = 10.0;
    public static final double SEUIL_HYPER_SEVERE       = 13.9;

    // ── Champs privés ────────────────────────────────────────────────────────
    private int            id;
    private double         valeur;        // valeur en mmol/L (stockage interne)
    private String         unite;         // "mmol/L" ou "mg/dL"
    private ContexteMesure contexte;
    private String         dateHeure;     // format "AAAA-MM-JJ HH:MM"
    private int            patientId;

    // ── Constructeur vide ────────────────────────────────────────────────────
    public MesureGlycemique() {
        this.id        = 0;
        this.valeur    = 0.0;
        this.unite     = "mmol/L";
        this.contexte  = ContexteMesure.A_JEUN;
        this.dateHeure = "";
        this.patientId = 0;
    }

    // ── Constructeur complet ─────────────────────────────────────────────────
    public MesureGlycemique(int id, double valeur, String unite,
                            ContexteMesure contexte, String dateHeure, int patientId) {
        this.id        = id;
        this.unite     = unite;
        // Normalisation : on stocke toujours en mmol/L
        this.valeur    = unite.equalsIgnoreCase("mg/dL") ? valeur / 18.0 : valeur;
        this.contexte  = contexte;
        this.dateHeure = dateHeure;
        this.patientId = patientId;
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
     * Affiche les détails de la mesure dans la console.
     */
    public void afficherDetails() {
        System.out.println("  Mesure #" + id
                + " | " + String.format("%.1f", valeur) + " mmol/L"
                + " (" + String.format("%.0f", convertirUnite("mg/dL")) + " mg/dL)"
                + " | " + contexte
                + " | " + dateHeure);
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public int            getId()        { return id; }
    public double         getValeur()    { return valeur; }
    public String         getUnite()     { return unite; }
    public ContexteMesure getContexte()  { return contexte; }
    public String         getDateHeure() { return dateHeure; }
    public int            getPatientId() { return patientId; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setId(int id)                      { this.id = id; }
    public void setValeur(double valeur)           { this.valeur = valeur; }
    public void setUnite(String unite)             { this.unite = unite; }
    public void setContexte(ContexteMesure c)      { this.contexte = c; }
    public void setDateHeure(String dateHeure)     { this.dateHeure = dateHeure; }
    public void setPatientId(int patientId)        { this.patientId = patientId; }

    @Override
    public String toString() {
        return "Mesure{id=" + id
                + ", " + String.format("%.1f", valeur) + " mmol/L"
                + ", " + contexte + ", " + dateHeure + "}";
    }
}
