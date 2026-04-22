package com.diabcare.models;
package com.diabcare.models;

import java.util.ArrayList;
import java.util.List;

public class RendezVousService {

    private List<RendezVous> liste = new ArrayList<>();

    public void demanderRDV(RendezVous rdv) {
        liste.add(rdv);
        System.out.println("RDV ajouté : ID=" + rdv.getId() + ", Date=" + rdv.getDate());
    }

    public void consulterRDV() {
        System.out.println("=== Liste des rendez-vous ===");

        for (RendezVous rdv : liste) {
            System.out.println("ID: " + rdv.getId() + " | Date: " + rdv.getDate());
        }
    }
}