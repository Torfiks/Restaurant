package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ActivityManagerController {
    @FXML
    void showSale(ActionEvent event) throws IOException {
        HBox sale_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/sale.fxml"));
        Main.getBorderPane().setCenter(sale_panel);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }

    @FXML
    void showAdminHome(ActionEvent event) throws IOException {
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));

        VBox admin_sidebar_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_side_bar.fxml"));
        VBox admin_user_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_users.fxml"));

        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
        Main.getBorderPane().setRight(admin_sidebar_panel);
        Main.getBorderPane().setCenter(admin_user_panel);

    }
    @FXML
    void showAllTables(ActionEvent actionEvent) throws IOException  {
        FlowPane table_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_waiter.fxml"));
        Main.getBorderPane().setCenter(table_panel);
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }
}
