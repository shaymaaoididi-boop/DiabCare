package com.diabcare.models;

import com.diabcare.enums.ContexteMesure;
import java.util.ArrayList;

/**
 * Gère l'historique des mesures glycémiques d'un patient.
 * Responsable : kenza(Scrum Master)
 *
 * Fonctionnalité : Enregistrement & Historique des mesures glycémiques
 * 
 * Sprint 2 — Logique complète :
 *   - calculerMoyenne() :  moyenne simple des mesures glycémiques
 *   - calculerTendance() : détection de tendance (amélioration/dégradation)
 *   - getNbMesuresCritiques() : comptage des mesures hors plage
 *   - genererBilanSemaine() : rapport hebdomadaire complet
 *   - calculerEcartType() : statistique de variabilité glycémique
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
            System.out.printf("      ❌ Mesure REFUSÉE — %.1f mmol/L hors plage physiologique "
                    + "(%.1f – %.1f mmol/L)%n",
                    mesure.getValeur(),
                   MesureGlycemique.SEUIL_MIN_PHYSIOLOGIQUE,
                   MesureGlycemique.SEUIL_MAX_PHYSIOLOGIQUE);
            return false;
            }

        mesures.add(mesure);
        System.out.printf("✅ Mesure #%d enregistrée — %.1f mmol/L | %s | %s | %s%n",
        mesure.getId(),
        mesure.getValeur(),
        mesure.getContexte(),
        mesure.getDateHeure(),
        mesure.evaluerGlycemie());

        // ── Avertissement immédiat si critique

        if (mesure.estCritique()) {
            System.out.println("  ⚠️  VALEUR CRITIQUE — Vérification alerte recommandée !");
        }
        return true;
    }
    // ── Moyenne réelle ────────────────────────────────────────
     public double calculerMoyenne() {
        if (mesures.isEmpty()) return 0.0;
        double somme = 0.0;
        for (MesureGlycemique m : mesures) somme += m.getValeur();
        return Math.round((somme / mesures.size()) * 10.0) / 10.0;
    }
     // ──  Moyenne par contexte ─────────────────────────────────
    public double calculerMoyenneParContexte(string contexte) {
        ArrayList<MesureGlycemique> filtrees = filtrerParContexte(contexte);
        if (filtrees.isEmpty()) return 0.0;
        double somme = 0.0;
        for (MesureGlycemique m : filtrees) somme += m.getValeur();
        return Math.round((somme / filtrees.size()) * 10.0) / 10.0;
    }

     // ──  Écart-type (variabilité glycémique) ───────────────────
    public double calculerEcartType() {
        if (mesures.size() < 2) return 0.0;
        double moy = calculerMoyenne();
        double sommeCarres = 0.0;
        for (MesureGlycemique m : mesures)
            sommeCarres += Math.pow(m.getValeur() - moy, 2);
        return Math.round(Math.sqrt(sommeCarres / mesures.size()) * 10.0) / 10.0;
    }
    // ──  Nombre de mesures critiques ───────────────────────────
    public int getNbMesuresCritiques() {
        int count = 0;
        for (MesureGlycemique m : mesures) if (m.estCritique()) count++;
        return count;
    }
    // ──  Tendance sur les N dernières mesures ──────────────────
    public String calculerTendance(int n) {
        if (mesures.size() < 2) return "Données insuffisantes";
        int fin = mesures.size();
        int debut = Math.max(0, fin - n);
        double premiereMoitie = 0, secondeMoitie = 0;
        int mid = (debut + fin) / 2;
        for (int i = debut; i < mid; i++) premiereMoitie += mesures.get(i).getValeur();
        for (int i = mid;   i < fin; i++) secondeMoitie  += mesures.get(i).getValeur();
        premiereMoitie /= Math.max(1, mid - debut);
        secondeMoitie  /= Math.max(1, fin - mid);
        double diff = secondeMoitie - premiereMoitie;
        if (diff > 1.0)  return "📈 En hausse (+" + String.format("%.1f", diff) + " mmol/L)";
        if (diff < -1.0) return "📉 En baisse (" + String.format("%.1f", diff) + " mmol/L)";
        return "➡️  Stable";
    }
    // ── Filtre par contexte ───────────────────────────────────
    public ArrayList<MesureGlycemique> filtrerParContexte(String contexte) {
        ArrayList<MesureGlycemique> res = new ArrayList<>();
        for (MesureGlycemique m : mesures)
            if (m.getContexte().equalsIgnoreCase(contexte)) res.add(m);
        return res;
    }
    // ── Bilan complet ──────────────────────────────────────────
  public void genererBilanSemaine() {
    System.out.println("\n===== BILAN GLYCÉMIQUE =====");
    System.out.println("Patient #" + patientId);

    System.out.println("Nombre de mesures : " + mesures.size());
    System.out.println("Moyenne : " + calculerMoyenne() + " mmol/L");
    System.out.println("Écart-type : " + calculerEcartType() + " mmol/L");
    System.out.println("Mesures critiques : " + getNbMesuresCritiques());
    System.out.println("Tendance : " + calculerTendance(5));

    System.out.println("\n--- Détails ---");
    System.out.println("Moyenne à jeun : "
            + calculerMoyenneParContexte("A_JEUN") );
    System.out.println("Moyenne après repas : "
            + calculerMoyenneParContexte("APRES_REPAS"));
}
  // ── Affichage ──────────────────────────────────────────────
 public void afficherHistorique() {
    System.out.println("\nHistorique patient #" + patientId);

    if (mesures.isEmpty()) {
        System.out.println("Aucune mesure");
        return;
    }

    for (MesureGlycemique m : mesures)
        m.afficherDetails();

    System.out.println("Moyenne : " + calculerMoyenne());
}
   // ── Getters ────────────────────────────────────────────────
    public ArrayList<MesureGlycemique> getMesures()   { return mesures; }
    public int                         getPatientId() { return patientId; }
    public int                         getNbMesures() { return mesures.size(); }

   // ── Setters ────────────────────────────────────────────────
    public void                        setPatientId(int id) { this.patientId = id; }

    
  @Override
  public String toString() {
  return "Historique{patient=" + patientId +
  ", mesures=" + mesures.size() +
  ", moyenne=" + calculerMoyenne() + "}";
  }
}
