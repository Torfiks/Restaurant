package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import com.backend.restaurant.helper.MyDialog;
import com.backend.restaurant.models.ERole;
import com.backend.restaurant.models.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.backend.restaurant.models.ERole.*;

public class LabelHomeController implements Initializable {

    @FXML
    void showExit(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear();
        BorderPane home_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/welcome.fxml"));
        Main.getBorderPane().setCenter(home_panel);
    }

    @FXML
    void showMenu(ActionEvent event) throws IOException {
        Main.getBorderPane().getChildren().clear();
        ERole login = ERole.fromValue(Login.getInstance().getStatus());

        switch (login){
            case ROLE_MANAGER:
                Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
                GridPane activity_manager = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_manager.fxml"));
                Main.getBorderPane().setCenter(activity_manager);
                Login.getInstance().setStatus(ROLE_MANAGER.toString());
                break;
            case ROLE_WAITER:
                Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
                FlowPane activity_waiter = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_waiter.fxml"));
                Main.getBorderPane().setCenter(activity_waiter);
                Login.getInstance().setStatus(ROLE_WAITER.toString());
                break;
            case ROLE_USER:
                Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
                GridPane activity_user = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_user.fxml"));
                Main.getBorderPane().setCenter(activity_user);
                Login.getInstance().setStatus(ROLE_USER.toString());
                break;
            default:
                MyDialog.show("Ошибка обращения");
        }
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
        Main.getBorderPane().setTop(login_panel);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
