package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import com.backend.restaurant.helper.MyDialog;
import com.backend.restaurant.models.Dish;
import com.backend.restaurant.models.Order;
import com.backend.restaurant.models.Sale;
import com.backend.restaurant.models.Table;
import com.backend.restaurant.services.DishService;
import com.backend.restaurant.services.OrderService;
import com.backend.restaurant.services.SaleService;
import com.backend.restaurant.services.TableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SaleController implements Initializable {

    @FXML
    private TextField order_sale_id;

    @FXML
    private ListView<String> order_table_listview;

    @FXML
    private TextField order_dish_id;

    @FXML
    private ListView<String> order_dish_listview;

    ObservableList<String> obDishes = FXCollections.observableArrayList();
    ObservableList<String> obTables = FXCollections.observableArrayList();

    private final Sale sale = new Sale(){};
    private int price;

    @FXML
    void startOrder(ActionEvent event) {

        SaleService saleService = new SaleService();

        int SaleId = Integer.parseInt(order_sale_id.getText()) * 10;
        int dishId = Integer.parseInt(order_dish_id.getText());

        sale.setDish_id(dishId);
        sale.setPresent(SaleId);
        sale.setPrice((int) (price/(SaleId*0.1)));

        if (saleService.addSale(sale)) {
            MyDialog.show("Скидка добавлена");
        } else {
            MyDialog.show("Ошибка!");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getAllTable();



        DishService dishService = new DishService();
        List<Dish> dishes = dishService.getAllDish();


        for (int i = 0; i <= 100;i+=10) {
            obTables.add(String.valueOf(i));
        }
        for (Dish dish : dishes) {
            obDishes.add(dish.getName());
        }

        order_table_listview.getItems().addAll(obTables);
        order_dish_listview.getItems().addAll(obDishes);

        order_table_listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int ind = order_table_listview.getSelectionModel().getSelectedIndex();
            order_sale_id.setText(String.valueOf(ind));
        });

        order_dish_listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int ind = order_dish_listview.getSelectionModel().getSelectedIndex();
            Dish dish = dishes.get(ind);
            order_dish_id.setText(String.valueOf(dish.getId()));
            sale.setPrice(dish.getPrice());
            this.price = dish.getPrice();
        });
    }

    @FXML
    void showOrders(ActionEvent event) throws IOException {
        SplitPane active_order_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/active_orders.fxml"));
        Main.getBorderPane().setCenter(active_order_panel);
    }
}
