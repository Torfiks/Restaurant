package com.backend.restaurant.models;

public class Sale {
    private int id;
    private int dish_id;
    private int present;
    private int price;

    public Sale(){}

    public Sale(int id, int dish_id, int present, int price) {
        this.id = id;
        this.dish_id = dish_id;
        this.present = present;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
