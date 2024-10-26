package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import com.backend.restaurant.helper.MyDialog;
import com.backend.restaurant.models.Login;
import com.backend.restaurant.models.ERole;
import com.backend.restaurant.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


import java.io.IOException;

import static com.backend.restaurant.models.ERole.*;


public class LoginController {

    @FXML
    private TextField tvPassword;

    @FXML
    private TextField tvUsername;

    @FXML
    void startLogin(ActionEvent event) throws IOException {
        String username = tvUsername.getText();
        String password = tvPassword.getText();

        UserService userService = new UserService();

        if (userService.loginUser(username, password)) {

            ERole role = ERole.fromValue(userService.getRole(username, password));

            switch (role){
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
                    MyDialog.show("Ошибка авторизации, попробуйте позже");
            }
            BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
            Main.getBorderPane().setTop(login_panel);

        } else {
            MyDialog.show("Неверный логин или пароль!");
        }
    }
}