package com.backend.restaurant;

import com.backend.restaurant.models.Dish;
import com.backend.restaurant.models.Table;
import com.backend.restaurant.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.backend.restaurant.models.ERole.ROLE_USER;

public class Main extends Application {
    public static Stage stage;
    public static BorderPane borderPane;

    public static User user = new User(0, ROLE_USER.toString(), false, "", "");
    public static Dish dish = new Dish(0, 0, "", false);
    public static Table table = new Table(0, 0, 0, false, "");

    @Override
    public void start(Stage stage) throws Exception {
        borderPane = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage.setTitle("My Restaurant");
        stage.setScene(new Scene(borderPane));
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static BorderPane getBorderPane() {
        return borderPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
