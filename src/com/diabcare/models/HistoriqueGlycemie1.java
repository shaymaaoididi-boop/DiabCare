package com.diabcare.models;

import com.diabcare.enums.ContexteMesure;
import java.util.ArrayList;

/**
 * Gère l'historique des mesures glycémiques d'un patient.
 * Responsable : kenza(Scrum Master)
 *
 * Fonctionnalité : Enregistrement & Historique des mesures glycémiques
 */
public class HistoriqueGlycemie {

    // ── Champs privés ────────────────────────────────────────────────────────
    private ArrayList<MesureGlycemique> mesures;
    private int                         patientId;

    // ── Constructeur vide ────────────────────────────────────────────────────
    public HistoriqueGlycemie() {
        this.mesures   = new ArrayList<>();
        this.patientId = 0;
    }

    // ── Constructeur complet ─────────────────────────────────────────────────
    public HistoriqueGlycemie(int patientId) {
        this.mesures   = new ArrayList<>();
        this.patientId = patientId;
    }

    // ── Méthodes métier ──────────────────────────────────────────────────────

    /**
     * Ajoute une mesure après validation de sa plage physiologique.
     * @param mesure la mesure à enregistrer
     * @return true si la mesure a été ajoutée
     */
    public boolean ajouterMesure(MesureGlycemique mesure) {
        if (!mesure.estValide()) {
            System.out.println("Mesure refusée (hors plage physiologique) : "
                    + mesure.getValeur() + " mmol/L");
            return false;
        }
        mesures.add(mesure);
        System.out.println(" Mesure enregistrée : "
                + String.format("%.1f", mesure.getValeur()) + " mmol/L"
                + " — " + mesure.getContexte()
                + " (" + mesure.getDateHeure() + ")");
        return true;
    }

    /**
     * Calcule la moyenne glycémique sur toutes les mesures de l'historique.
     * @return moyenne en mmol/L, ou 0 si aucune mesure
     */
    public double calculerMoyenne() {
        if (mesures.isEmpty()) return 0.0;
        double somme = 0.0;
        for (MesureGlycemique m : mesures) {
            somme += m.getValeur();
        }
        double moyenne = somme / mesures.size();
        return Math.round(moyenne * 10.0) / 10.0;
    }

    /**
     * Filtre les mesures selon un contexte donné.
     * @param contexte filtre à appliquer
     * @return liste des mesures correspondantes
     */
    public ArrayList<MesureGlycemique> filtrerParContexte(ContexteMesure contexte) {
        ArrayList<MesureGlycemique> resultat = new ArrayList<>();
        for (MesureGlycemique m : mesures) {
            if (m.getContexte() == contexte) {
                resultat.add(m);
            }
        }
        return resultat;
    }

    /**
     * Retourne les N dernières mesures de l'historique.
     * @param n nombre de mesures souhaitées
     * @return sous-liste (les plus récentes sont en fin de liste)
     */
    public ArrayList<MesureGlycemique> getDernieresMesures(int n) {
        int debut = Math.max(0, mesures.size() - n);
        return new ArrayList<>(mesures.subList(debut, mesures.size()));
    }

    /**
     * Affiche tout l'historique dans la console.
     */
    public void afficherHistorique() {
        System.out.println("  📋 Historique glycémique (patient #" + patientId
                + ") — " + mesures.size() + " mesure(s) :");
        if (mesures.isEmpty()) {
            System.out.println("     (aucune mesure enregistrée)");
            return;
        }
        for (MesureGlycemique m : mesures) {
            m.afficherDetails();
        }
        System.out.println("  ─────────────────────────────────────────────");
        System.out.println("  Moyenne : " + calculerMoyenne() + " mmol/L");
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public ArrayList<MesureGlycemique> getMesures()   { return mesures; }
    public int                         getPatientId() { return patientId; }
    public int                         getNbMesures() { return mesures.size(); }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setPatientId(int patientId) { this.patientId = patientId; }

    @Override
    public String toString() {
        return "Historique{patientId=" + patientId
                + ", nbMesures=" + mesures.size()
                + ", moyenne=" + calculerMoyenne() + " mmol/L}";
    }
}
