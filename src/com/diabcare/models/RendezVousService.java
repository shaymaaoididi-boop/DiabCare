package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class RendezVousService {
    private List<RendezVous> listeRDV = new ArrayList<>();

    public RendezVous demanderRDV(Medecin medecin, String date, String heure) {
        RendezVous rdv = new RendezVous(medecin, date, heure);
        listeRDV.add(rdv);
        return rdv;
    }

    public void afficherRDV() {
        for (RendezVous rdv : listeRDV) {
            System.out.println("----------------------");
            System.out.println(rdv.getRecapitulatif());
        }
    }
}