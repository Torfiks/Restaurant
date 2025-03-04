package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminSidebarController {

    @FXML
    void showAdminUserPanel(ActionEvent event) throws IOException {
        VBox admin_user_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_users.fxml"));
        Main.getBorderPane().setCenter(admin_user_panel);
    }

    @FXML
    void showAdminTablePanel(ActionEvent event) throws IOException {
        VBox admin_table_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_table.fxml"));
        Main.getBorderPane().setCenter(admin_table_panel);
    }

    @FXML
    void showAdminDishPanel(ActionEvent event) throws IOException {
        VBox admin_dishes_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_dishes.fxml"));
        Main.getBorderPane().setCenter(admin_dishes_panel);
    }

    @FXML
    void showAdminTodaySale(ActionEvent event) throws IOException {
        VBox admin_dishes_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/today_sale.fxml"));
        Main.getBorderPane().setCenter(admin_dishes_panel);
    }

    @FXML
    void showAdminSaleHistoryPanel(ActionEvent event) throws IOException {
        TableView admin_sale_history_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/sale_history.fxml"));
        Main.getBorderPane().setCenter(admin_sale_history_panel);
    }

}