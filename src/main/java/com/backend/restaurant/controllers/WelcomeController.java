package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class WelcomeController {
    @FXML
    void back(MouseEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/welcome.fxml"));
        Main.getBorderPane().setCenter(login_panel); // Добавить Login.fxml в BorderPane
    }

    @FXML
    void startLogin(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
        AnchorPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/login.fxml"));
        Main.getBorderPane().getChildren().add(login_panel); // Добавить Login.fxml в BorderPane
    }
    @FXML
    void showAllDishes(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
        TableView dish_table = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/dishes.fxml"));
        Main.getBorderPane().setCenter(dish_table);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_welcome.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }
    @FXML
    void showTelephone(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Контактные данные");
        alert.setHeaderText("Номер телефона: \n+7 (999) 836-52-23");
        alert.showAndWait();
    }

    @FXML
    void showAddress(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Адрес");
        alert.setHeaderText("Митинская ул., 16, Москвав лобби апарт-отеля YE'S");
        alert.showAndWait();
    }
    @FXML
    void showTime(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("График работы");
        alert.setHeaderText("Понедельник\n" +
                "\t08:00 – 03:00\n" +
                "Вторник\n" +
                "\t08:00 – 03:00\n" +
                "Среда\n" +
                "\t08:00 – 03:00\n" +
                "Четверг\n" +
                "\t08:00 – 03:00\n" +
                "Пятница\n" +
                "\t08:00 – 03:00\n" +
                "Суббота\n" +
                "\t10:00 – 03:00\n" +
                "Воскресенье\n" +
                "\t10:00 – 03:00");
        alert.showAndWait();
    }
}
