package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class RendezVousService {

    private List<RendezVous> liste = new ArrayList<>();

    // création RDV
    public RendezVous demanderRDV(Patient patient, Medecin medecin, String date, String heure) {

        if (!medecin.estDisponible()) {
            System.out.println("Médecin non disponible !");
            return null;
        }

        RendezVous rdv = new RendezVous(patient, medecin, date, heure);
        liste.add(rdv);

        return rdv;
    }

    // recherche médecin par spécialité
    public List<Medecin> rechercher(List<Medecin> medecins, String specialite) {
        List<Medecin> resultats = new ArrayList<>();

        for (Medecin m : medecins) {
            if (m.getSpecialite().equalsIgnoreCase(specialite)) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    public void afficherRDV() {
        for (RendezVous r : liste) {
            System.out.println("------------------");
            System.out.println(r.getRecapitulatif());
        }
    }

    public List<RendezVous> getListe() {
        return liste;
    }
}