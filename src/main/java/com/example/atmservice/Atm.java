package com.example.atmservice;

public class Atm {
    private int id;
    private int regionId;

    public Atm(int id, int regionId) {
        this.id = id;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegion(int newRegionId) {
        this.regionId = newRegionId;
    }
}
