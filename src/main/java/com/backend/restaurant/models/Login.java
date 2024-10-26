package com.backend.restaurant.models;

public class Login {
    public String status;

    public Login(){}

    public void setStatus(String status) {this.status = status;}

    public String getStatus() {return this.status;}

    // Добавляем статический метод для получения единственного экземпляра класса Login
    private static Login instance;

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }
}
