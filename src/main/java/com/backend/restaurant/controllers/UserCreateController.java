package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import com.backend.restaurant.helper.MyDialog;
import com.backend.restaurant.models.ERole;
import com.backend.restaurant.models.User;
import com.backend.restaurant.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.backend.restaurant.models.ERole.ROLE_MANAGER;
import static com.backend.restaurant.models.ERole.ROLE_USER;
import static com.backend.restaurant.models.ERole.ROLE_WAITER;

public class UserCreateController implements Initializable {

    @FXML
    private ChoiceBox<String> admin_cu_role;

    @FXML
    private ChoiceBox<String> admin_cu_enabled;

    @FXML
    private TextField admin_cu_username;

    @FXML
    private TextField admin_cu_password;

    User user = Main.user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        admin_cu_role.getItems().addAll(ROLE_USER.toString(), ROLE_MANAGER.toString(), ROLE_WAITER.toString());
        admin_cu_role.getSelectionModel().select(0);
        admin_cu_enabled.getItems().addAll("0", "1");
        admin_cu_enabled.getSelectionModel().select(1);

        if (user.getId() != 0) {
            admin_cu_username.setText(user.getUsername());
            admin_cu_password.setText(user.getPassword());
            if (user.isEnabled()) {
                admin_cu_enabled.getSelectionModel().select(1);
            } else {
                admin_cu_enabled.getSelectionModel().select(0);
            }
            if (user.getRole().equals(ROLE_MANAGER.toString())) {
                admin_cu_role.getSelectionModel().select(ROLE_MANAGER.toString());
            } else if(user.getRole().equals(ROLE_WAITER.toString())){
                admin_cu_role.getSelectionModel().select(ROLE_WAITER.toString());
            }else{
                admin_cu_role.getSelectionModel().select(ROLE_USER.toString());
            }

        }

    }

    @FXML
    void startCreateUser(ActionEvent event) {

        user.setUsername(admin_cu_username.getText());
        user.setPassword(admin_cu_password.getText());

        user.setEnabled(!admin_cu_enabled.getSelectionModel().getSelectedItem().contentEquals("0"));

        user.setRole((admin_cu_role.getSelectionModel().getSelectedItem()));

        UserService userService = new UserService();

        if (user.getId() != 0) {
            if (userService.updateUser(user)) {
                try {
                    VBox admin_user_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_users.fxml"));
                    Main.getBorderPane().setCenter(admin_user_panel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                MyDialog.show("Something Wrong!");
            }
        } else {
            if (userService.saveUser(user)) {
                try {
                    VBox admin_user_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/admin_users.fxml"));
                    Main.getBorderPane().setCenter(admin_user_panel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                MyDialog.show("Something Wrong!");
            }
        }


    }
}