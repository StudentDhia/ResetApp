package com.walidhelaoui.resetandroidapp.Entity;

/**
 * Created by walid on 24/01/2018.
 */

public class Trophy {

    private int drinkeRest;
    private int smokeRest;

    public Trophy(int drinkeRest, int smokeRest) {
        this.drinkeRest = drinkeRest;
        this.smokeRest = smokeRest;
    }

    public Trophy() {
    }

    public int getDrinkeRest() {
        return drinkeRest;
    }

    public void setDrinkeRest(int drinkeRest) {
        this.drinkeRest = drinkeRest;
    }

    public int getSmokeRest() {
        return smokeRest;
    }

    public void setSmokeRest(int smokeRest) {
        this.smokeRest = smokeRest;
    }
}
