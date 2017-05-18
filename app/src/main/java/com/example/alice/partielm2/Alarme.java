package com.example.alice.partielm2;

/**
 * Created by alice on 18/05/2017.
 */

import com.orm.SugarRecord;

import java.util.GregorianCalendar;

public class Alarme extends SugarRecord {

    private GregorianCalendar heure;
    private String description;
    private boolean active;

    public Alarme(GregorianCalendar heure, String description, boolean active) {
        this.heure = heure;
        this.description = description;
        this.active = active;
    }

    public Alarme(GregorianCalendar heure, String description) {
        this.heure = heure;
        this.description = description;
        this.active = false;
    }

    public GregorianCalendar getHeure() {
        return heure;
    }

    public void setHeure(GregorianCalendar heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
