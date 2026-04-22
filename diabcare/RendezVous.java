package com.diabcare.models;

public class RendezVous {

    private int id;
    private String date;

    // constructeur
    public RendezVous(int id, String date) {
        this.id = id;
        this.date = date;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}