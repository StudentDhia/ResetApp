package com.esprit.reset.Entity;

/**
 * Created by walid on 24/03/2018.
 */

public class Settings {

    private int smoking_price=-1;
    private int drinking_price=-1;

    public int getSmoking_price() {
        return smoking_price;
    }

    public void setSmoking_price(int smoking_price) {
        this.smoking_price = smoking_price;
    }

    public int getDrinking_price() {
        return drinking_price;
    }

    public void setDrinking_price(int drinking_price) {
        this.drinking_price = drinking_price;
    }
}
