package com.backend.restaurant.controllers;

import com.backend.restaurant.models.Dish;
import com.backend.restaurant.services.DishService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DishesUserController implements Initializable {

    @FXML
    private TableColumn<Dish, Integer> col_dish_price;

    @FXML
    private TableColumn<Dish, Boolean> col_dish_enabled;

    @FXML
    private TableColumn<Dish, Integer> col_dish_id;

    @FXML
    private TableColumn<Dish, String> col_dish_name;

    @FXML
    private TableView<Dish> dishTable;

    ObservableList<Dish> obDishes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DishService dishService = new DishService();

        List<Dish> dishes = dishService.getAllDish();

        for (Dish dish : dishes) {
            obDishes.add(dish);
        }

        col_dish_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_dish_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_dish_enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        col_dish_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        dishTable.setItems(obDishes);
    }

}