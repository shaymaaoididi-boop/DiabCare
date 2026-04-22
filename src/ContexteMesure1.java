package com.diabcare.enums;

/**
 * Contexte de prise de la mesure glycémique.
 * Responsable : kenza
 */
public enum ContexteMesure {
    A_JEUN("À jeun"),
    AVANT_REPAS("Avant repas"),
    APRES_REPAS("Après repas"),
    AU_COUCHER("Au coucher");

    private final String libelle;

    ContexteMesure(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() { return libelle; }

    @Override
    public String toString() {
        return libelle;
    }
}
