package com.backend.restaurant.controllers;


import com.backend.restaurant.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LabelWelcomeController implements Initializable {

    @FXML
    void showTable(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear();
        FlowPane table_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/tables.fxml"));
        Main.getBorderPane().setCenter(table_panel);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_welcome.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }
    @FXML
    void showMenu(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear();
        TableView table_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/dishes.fxml"));
        Main.getBorderPane().setCenter(table_panel);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_welcome.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }

    @FXML
    void showExit(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear();
        BorderPane home_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/welcome.fxml"));
        Main.getBorderPane().setCenter(home_panel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

}