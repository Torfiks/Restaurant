package com.backend.restaurant.controllers;

import com.backend.restaurant.models.Sale;
import com.backend.restaurant.models.SaleHsitory;
import com.backend.restaurant.services.SaleHistoryService;
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

public class SaleHistoryController implements Initializable {

    @FXML
    private TableView<Sale> sale_history_table;

    @FXML
    private TableColumn<Sale, String> sale_history_table_col_date;

    @FXML
    private TableColumn<Sale, Integer> sale_history_table_col_total_sale;

    ObservableList<Sale> obHistories = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sale_history_table_col_date.setCellValueFactory(new PropertyValueFactory<>("dish_id"));
        sale_history_table_col_total_sale.setCellValueFactory(new PropertyValueFactory<>("present"));

        SaleHistoryService service = new SaleHistoryService();
        List<Sale> histories = service.getAllHistory();

        obHistories.addAll(histories);
        sale_history_table.setItems(obHistories);
    }
}