package com.backend.restaurant.models;


public class Table {
    private int id, charge, chairs;
    private String nameUser;
    private boolean enabled;

    public Table(int id, int charge, int chairs, boolean enabled, String nameUser) {
        this.id = id;
        this.charge = charge;
        this.chairs = chairs;
        this.enabled = enabled;
        this.nameUser = nameUser;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    public String getNameUser() {
        return nameUser;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", charge=" + charge +
                ", chairs=" + chairs +
                ", enabled=" + enabled +
                '}';
    }
}
