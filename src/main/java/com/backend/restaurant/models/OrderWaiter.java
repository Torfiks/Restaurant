package com.backend.restaurant.models;


public class OrderWaiter {
    private int id;
    private int numTable;
    private int idDishes;
    private String nameDish;
    private int price;
    private int count;

    public OrderWaiter(){}

    public OrderWaiter(int id, int numTable, int idDishes, int price, int count) {
        this.id = id;
        this.numTable = numTable;
        this.idDishes = idDishes;
        this.price = price;
        this.count = count;
    }


    public int getIdDishes() {
        return idDishes;
    }

    public void setIdDishes(int idDishes) {
        this.idDishes = idDishes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }
}