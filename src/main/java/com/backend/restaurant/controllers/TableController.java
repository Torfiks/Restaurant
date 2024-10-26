package com.backend.restaurant.controllers;

import com.backend.restaurant.models.Table;
import com.backend.restaurant.services.TableService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private FlowPane tables_flow_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableService tableService = new TableService();
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
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Бронирование стола");
                dialog.setHeaderText("Стол имеет мест:" + table.getChairs() +
                        "\nНа данный момент стол " + (table.isEnabled() ? "свободен" : "забронирован")+
                        "\nВведите ваше имя для бронирования стола " + table.getId() + ":");
                dialog.setContentText("Имя:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> {
                    // Здесь вызовите метод для записи информации в базу данных
                    boolean reservationSuccess = tableService.reserveTable(table.getId(), name);

                    if (reservationSuccess) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Успешное бронирование");
                        alert.setHeaderText("Стол успешно забронирован!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка бронирования");
                        alert.setHeaderText("Что-то пошло не так при бронировании стола.");
                        alert.showAndWait();
                    }
                });
            });
            tables_flow_pane.getChildren().add(button);
        }
    }
}