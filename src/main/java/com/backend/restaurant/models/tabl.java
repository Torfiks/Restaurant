package com.backend.restaurant.models;

public class tabl {

    public int numTable;

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public int getNumTable() {
        return numTable;
    }

    public tabl(){}

    private static tabl instance;

    public static tabl getInstance() {
        if (instance == null) {
            instance = new tabl();
        }
        return instance;
    }
}
