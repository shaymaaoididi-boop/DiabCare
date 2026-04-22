package com.diabcare.models;

public class RendezVousService {

    public void demanderRDV(RendezVous rdv) {
        System.out.println("RDV demandé pour : " + rdv.getDate());
    }

    public void consulterRDV() {
        System.out.println("Liste des rendez-vous affichée");
    }
}
