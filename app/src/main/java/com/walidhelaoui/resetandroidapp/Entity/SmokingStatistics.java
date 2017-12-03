package com.walidhelaoui.resetandroidapp.Entity;

/**
 * Created by walid on 27/11/2017.
 */

public class SmokingStatistics {

    int number;
    String date;
    float price;

    public SmokingStatistics() {

    }

    public SmokingStatistics(int number, String date, float price) {
        this.number = number;
        this.date = date;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SmokingStatistics{" +
                "number=" + number +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
