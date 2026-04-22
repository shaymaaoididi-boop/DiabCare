pprivate double derniereMesure;

public String getNom() {
    return nom;
}

public double getDerniereMesure() {
    return derniereMesure;
}

public void setDerniereMesure(double valeur) {
    this.derniereMesure = valeur;
}

public double calculerMoyenne() {
    return derniereMesure; // simplifié Sprint 1
}