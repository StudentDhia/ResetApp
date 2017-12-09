package com.walidhelaoui.resetandroidapp.Entity;

/**
 * Created by walid on 09/12/2017.
 */

public class Mission {

    private String libelle;
    private String description;
    private Boolean etat;

    public Mission() {
    }

    public Mission(String libelle, String description, Boolean etat) {
        this.libelle = libelle;
        this.description = description;
        this.etat = etat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}


