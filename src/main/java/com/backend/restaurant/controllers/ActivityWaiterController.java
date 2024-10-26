package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import com.backend.restaurant.helper.MyDialog;
import com.backend.restaurant.models.*;
import com.backend.restaurant.services.DishService;
import com.backend.restaurant.services.OrderService;
import com.backend.restaurant.services.TableService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.backend.restaurant.models.ERole.ROLE_WAITER;

public class ActivityWaiterController implements Initializable {

    @FXML
    private ListView<String> tableListView;


    @FXML
    private ListView<String> orderListView;
    private final List<String> selectedTableOrders = new ArrayList<>();
    private final TableService tableService = new TableService();
    private final DishService dishService = new DishService();

    @FXML
    private FlowPane tables_flow_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        List<Table> tables = tableService.getAllTable();

        for (Table table : tables) {
            Button button = new Button("Столик номер: "+ String.valueOf(table.getId()));
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.getStyleClass().add("table_button");
            button.setOnAction(e -> System.out.println("Столик номер:\n"+table.getId()));

            Tooltip tooltip = new Tooltip("Мест: " + table.getChairs() + "\nСвободен: " + (table.isEnabled() ? "Да" : "Нет"));
            button.setTooltip(tooltip);


            button.setOnAction(e -> {
                try {
                    tabl.getInstance().setNumTable(table.getId());
                    panel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            });
            tables_flow_pane.getChildren().add(button);
        }
    }

    @FXML
    void panel() throws IOException {
        Main.getBorderPane().getChildren().clear();
        ERole login = ERole.fromValue(Login.getInstance().getStatus());

        switch (login) {
            case ROLE_WAITER:
                BorderPane activity_waiter_orders = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_waiter_orders.fxml"));
                Main.getBorderPane().setCenter(activity_waiter_orders);
                break;
            case ROLE_MANAGER:
                BorderPane activity_waiter_admin_orders = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_waiter_admin_orders.fxml"));
                Main.getBorderPane().setCenter(activity_waiter_admin_orders);
                break;
            default:
                MyDialog.show("Ошибка обращения");
        }
        BorderPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/label_home.fxml"));
        Main.getBorderPane().setTop(login_panel); // Добавить Login.fxml в BorderPane
    }

    @FXML
    public void initialize() {
        loadTables();
    }

    private void loadTables() {
        List<Table> tables = tableService.getAllActiveTable();
        for(Table table : tables) {
            tableListView.getItems().add("Столы " + table.getId() + " - " + table.getChairs() + " стулья");
        }
    }

    @FXML
    void tableSelected(ActionEvent event) {
        String selectedTable = tableListView.getSelectionModel().getSelectedItem();
        if (selectedTable != null) {
            // Load dishes for the selected table
            loadDishesForTable(selectedTable);
        }
    }

    public void loadDishesForTable(String tableId) {
        // Assuming each table has a list of dishes, you need to fetch this data from your database
        // For now, let's just clear and update the order list
        orderListView.getItems().clear();
        selectedTableOrders.clear();
        // Example: Fetch dishes for the table
        // You need to implement this based on your database schema
        // List<Dish> dishes = dishService.getDishesForTable(Integer.parseInt(tableId));
        // for (Dish dish : dishes) {
        //     orderListView.getItems().add(dish.getName() + " - $" + dish.getPrice());
        // }
    }

    @FXML
    void addDishToOrder(ActionEvent event) {
        String selectedDish = "Selected Dish"; // Здесь нужно получить выбранное блюдо
        selectedTableOrders.add(selectedDish);
        orderListView.getItems().setAll(selectedTableOrders);
    }

    @FXML
    void calculateTotalSum(ActionEvent event) {
        int totalSum = 0;
        for(String dish : selectedTableOrders) {
            totalSum += getPriceOfDish(dish); // getPriceOfDish - метод, который возвращает цену блюда
        }
        orderListView.getItems().add("Total Sum: " + totalSum);
    }

    private int getPriceOfDish(String dish) {
        // Реализация метода
        return 0;
    }
}
