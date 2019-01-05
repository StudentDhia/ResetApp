package com.esprit.reset.Entity;

/**
 * Created by walid on 16/11/2017.
 */

public class Compte {

    String username;
    String email;
    Boolean enable=true;
    Boolean smoker=true;
    Boolean drinker=true;
    float smoke_score;
    float drink_score;

    public Compte(){
        this.username = "";
        this.email = "";
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public Boolean getDrinker() {
        return drinker;
    }

    public void setDrinker(Boolean drinker) {
        this.drinker = drinker;
    }

    public float getSmoke_score() {
        return smoke_score;
    }

    public void setSmoke_score(float smoke_score) {
        this.smoke_score = smoke_score;
    }

    public float getDrink_score() {
        return drink_score;
    }

    public void setDrink_score(float drink_score) {
        this.drink_score = drink_score;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                ", smoker=" + smoker +
                ", drinker=" + drinker +
                ", smoke_score=" + smoke_score +
                ", drink_score=" + drink_score +
                '}';
    }
}
