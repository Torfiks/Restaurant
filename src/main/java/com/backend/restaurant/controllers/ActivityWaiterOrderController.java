package com.backend.restaurant.controllers;

import com.backend.restaurant.Main;
import com.backend.restaurant.models.*;
import com.backend.restaurant.services.DishService;
import com.backend.restaurant.services.OrderService;
import com.backend.restaurant.services.SaleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.backend.restaurant.services.OrderService.getOllOrderDetailByTableIdWaiter;
import static com.backend.restaurant.services.OrderService.getOrderDetailByTableIdWaiter;


public class ActivityWaiterOrderController implements Initializable {

    @FXML
    private TextField order_count_dish;

    @FXML
    private Text total_sum;


    @FXML
    private ListView<String> order_dish_listview;

    @FXML
    private ListView<String> order_listview;

    private final List<OrderWaiter> orderWaiterList = new ArrayList<>();

    ObservableList<String> obOrder = FXCollections.observableArrayList();
    ObservableList<String> obDishes = FXCollections.observableArrayList();

    OrderService orderService = new OrderService();


    private final int tableNumber = tabl.getInstance().getNumTable() ; // Номер стола
    private int over_price;
    private int sale_price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        total_sum.setText(String.valueOf(OrderService.totalPrice(tableNumber)));
        price();
        System.out.println(String.valueOf(OrderService.totalPrice(tableNumber)));
        System.out.println(over_price);
        System.out.println(sale_price);
        total_sum.setText(String.format("Со скидкой %s без скидки %s", sale_price, over_price));
        order_count_dish.setText(String.valueOf(1));

        DishService dishService = new DishService();
        List<Dish> dishes = dishService.getAllDish();

        for (Dish dish : dishes) {
            obDishes.add(dish.getName());
        }
        order_dish_listview.getItems().addAll(obDishes);

        for (OrderWaiter orderWaiter : orderWaiterList) {
            obOrder.add(orderWaiter.getCount() + " x " + orderWaiter.getNameDish());
        }


        if(orderWaiterList.isEmpty()){

            List<OrderWaiter> orderWaiterL = getOrderDetailByTableIdWaiter(tabl.getInstance().getNumTable());
            if(!orderWaiterL.isEmpty()){
                for (OrderWaiter orderWaiters : orderWaiterL ) {
                    Dish selectedDish = DishService.getDishById(orderWaiters.getIdDishes());

                    OrderWaiter orderWaiter = new OrderWaiter();
                    orderWaiter.setIdDishes(orderWaiters.getIdDishes());
                    orderWaiter.setNumTable(tableNumber);
                    orderWaiter.setNameDish(selectedDish.getName());
                    orderWaiter.setPrice(orderWaiters.getPrice());
                    orderWaiter.setCount(orderWaiters.getCount());
                    orderWaiterList.add(orderWaiter);

                    obOrder.add(orderWaiter.getCount() + " x " + selectedDish.getName());
                }
            }
        }

        order_listview.getItems().addAll(obOrder);
    }

    @FXML
    void addDish(ActionEvent actionEvent) {
        int selectedIndex = order_dish_listview.getSelectionModel().getSelectedIndex();

        if (selectedIndex == 1){
            return;
        }

        if (selectedIndex >= 0) {
            Dish selectedDish = new DishService().getAllDish().get(selectedIndex);
            OrderWaiter orderWaiter = orderWaiterList.stream()
                    .filter(o -> o.getIdDishes() == selectedDish.getId())
                    .findFirst()
                    .orElse(null);

            if (orderWaiter != null) {
                orderWaiter.setCount(orderWaiter.getCount() + Integer.parseInt(order_count_dish.getText()));
            } else {
                orderWaiter = new OrderWaiter();
                orderWaiter.setIdDishes(selectedDish.getId());
                orderWaiter.setNumTable(tableNumber);
                orderWaiter.setNameDish(selectedDish.getName());
                orderWaiter.setPrice(selectedDish.getPrice());
                orderWaiter.setCount(Integer.parseInt(order_count_dish.getText()));
                orderWaiterList.add(orderWaiter);
            }

            obOrder.clear();

            for (OrderWaiter orderWaiterd : orderWaiterList) {
                Dish selectedDishd = DishService.getDishById(orderWaiterd.getIdDishes());
                obOrder.add(orderWaiterd.getCount() + " x " + selectedDishd.getName());
            }

            order_listview.getItems().clear();
            order_listview.getItems().addAll(obOrder);
            total_sum.setText(String.valueOf(price()));
            saveOrderToDB();
        }

    }

    @FXML
    void back(ActionEvent actionEvent) throws IOException {
        Main.getBorderPane().getChildren().clear(); // Очистить содержимое BorderPane
        FlowPane login_panel = FXMLLoader.load(getClass().getResource("/com/backend/restaurant/activity_waiter.fxml"));
        Main.getBorderPane().setCenter(login_panel); // Добавить Login.fxml в BorderPane
    }

    @FXML
    void deleteCount(ActionEvent actionEvent) {
        int selectedIndex = order_listview.getSelectionModel().getSelectedIndex();
        if (selectedIndex == 1){
            return;
        }
        String string =  order_listview.getItems().get(selectedIndex);
        string = string.split("x")[1].trim();
        String finalString = string;
        OrderWaiter orderWaiter = orderWaiterList.stream()
                .filter(o -> Objects.equals(o.getNameDish(), finalString))
                .findFirst()
                .orElse(null);

        if (orderWaiter != null) {
            if((orderWaiter.getCount() - 1) == 0){
                orderService.deleteDish(orderWaiter.getIdDishes(), orderWaiter.getNumTable());
            }
            orderWaiter.setCount(orderWaiter.getCount() - 1);
            orderService.deleteCount(orderWaiter.getCount() - 1, orderWaiter);
        }

        obOrder.clear();

        for (OrderWaiter orderWaiterd : orderWaiterList) {
            Dish selectedDishd = DishService.getDishById(orderWaiterd.getIdDishes());
            obOrder.add(orderWaiterd.getCount() + " x " + selectedDishd.getName());
        }

        order_listview.getItems().clear();
        order_listview.getItems().addAll(obOrder);
        total_sum.setText(String.valueOf(price()));
    }

    @FXML
    void deleteDishs(ActionEvent actionEvent) {
        int selectedIndex = order_listview.getSelectionModel().getSelectedIndex();
        if (selectedIndex == 1){
            return;
        }
        String string =  order_listview.getItems().get(selectedIndex);
        string = string.split("x")[1].trim();
        String finalString = string;
        OrderWaiter orderWaiter = orderWaiterList.stream()
                .filter(o -> Objects.equals(o.getNameDish(), finalString))
                .findFirst()
                .orElse(null);

        if (orderWaiter != null) {
            orderService.deleteDish(orderWaiter.getIdDishes(), orderWaiter.getNumTable());
            orderWaiter.setCount(orderWaiter.getCount() - 1);
            orderWaiterList.remove(selectedIndex);

        }

        obOrder.clear();

        for (OrderWaiter orderWaiterd : orderWaiterList) {
            Dish selectedDishd = DishService.getDishById(orderWaiterd.getIdDishes());
            obOrder.add(orderWaiterd.getCount() + " x " + selectedDishd.getName());
        }

        order_listview.getItems().clear();
        order_listview.getItems().addAll(obOrder);
        total_sum.setText(String.valueOf(price()));
    }

    private String price() {

        int sale_prices = 0;
        int over_prices = 0;
        List<Sale> sales = SaleService.getListSale();
        boolean flag = false;
        for (OrderWaiter orderWaiter : orderWaiterList) {

            for(Sale sale: sales) {
                if(orderWaiter.getIdDishes() == sale.getDish_id()){
                    sale_prices += orderWaiter.getCount() * sale.getPrice();
                    flag = true;
                    break;
                }
            }

            if(flag){
                over_prices += orderWaiter.getCount() *orderWaiter.getPrice();
                sale_prices += orderWaiter.getCount() *orderWaiter.getPrice();
                flag = false;
            }

        }
        sale_price=sale_prices;
        over_price=over_prices;
        return String.format("Со скидкой %s без скидки %s", sale_prices, over_prices);
    }

    private void saveOrderToDB() {
        OrderService orderService = new OrderService();
        for (OrderWaiter orderWaiter : orderWaiterList) {
            if (!getOrderDetailByTableIdWaiter(tableNumber).isEmpty() && !getOllOrderDetailByTableIdWaiter(orderWaiter.getIdDishes()).isEmpty()) {
                orderService.updateOrderWaiter(orderWaiter);
//                if (orderService.updateOrderWaiter(orderWaiter)) {
//                    System.out.println("Количество блюд успешно обновлено.");
//                } else {
//                    System.out.println("Не удалось обновить количество блюд.");
//                }
            } else {
                orderService.addOrderWaiter(orderWaiter);
//                if (orderService.addOrderWaiter(orderWaiter)) {
//                    System.out.println("Заказ успешно добавлен в базу данных.");
//                } else {
//                    System.out.println("Не удалось добавить заказ в базу данных.");
//                }
            }
        }
    }
}