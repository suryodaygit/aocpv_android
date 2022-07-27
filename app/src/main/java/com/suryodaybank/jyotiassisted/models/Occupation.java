package com.suryodaybank.jyotiassisted.models;

public class Occupation {
    String id;
    String occupation;

    public Occupation(String id, String occupation) {
        this.id = id;
        this.occupation = occupation;
    }

    public String getId() {
        return id;
    }

    public String getOccupation() {
        return occupation;
    }
}
