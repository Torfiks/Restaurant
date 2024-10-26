package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class ActivityUserController {
    @FXML
    void showAllTables(ActionEvent event) throws IOException {
        FlowPane table_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/tables.fxml"));
        Main.getBorderPane().setCenter(table_panel);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }

    @FXML
    void showAllDishes(ActionEvent event) throws IOException {
        TableView dish_table = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/dishesconsole.fxml"));
        Main.getBorderPane().setCenter(dish_table);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }
}
