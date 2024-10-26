package com.backend.restaurant.services;

import com.backend.restaurant.helper.DBHelper;
import com.backend.restaurant.models.OrderDetail;
import com.backend.restaurant.models.Sale;
import com.backend.restaurant.models.Table;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleService {

    public boolean addSale(Sale sale) {
        boolean condition = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        String query = "INSERT INTO sale (dish_id, present, price) VALUES (?, ?, ?)";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sale.getDish_id());
            ps.setInt(2, sale.getPresent());
            ps.setInt(3, sale.getPrice());


            int result = ps.executeUpdate();
            condition = true;

            if (result == 1) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    sale.setId(rs.getInt(7)); // Установить сгенерированный идентификатор заказа
                    condition = true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);


        return condition;
    }

    public static List<Sale> getListSale() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sale";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);

            set = ps.executeQuery();

            while (set.next()) {
                sales.add(new Sale(
                        set.getInt("id"),
                        set.getInt("dish_id"),
                        set.getInt("present"),
                        set.getInt("price")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, set);


        return sales;
    }

    public List<OrderDetail> getOrderBetweenDate(LocalDateTime start, LocalDateTime end) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT \n" +
                "\to.table_id,\n" +
                "    o.count,\n" +
                "    o.price,\n" +
                "    d.name \n" +
                "FROM \n" +
                "\torders as o \n" +
                "LEFT JOIN \n" +
                "\tdishes as d \n" +
                "ON \n" +
                "\to.dish_id=d.id\n" +
                "WHERE \n" +
                "\to.created_at \n" +
                "BETWEEN\n" +
                "\t ? AND ?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);

            ps.setTimestamp(1, Timestamp.valueOf(start));
            ps.setTimestamp(2, Timestamp.valueOf(end));


            set = ps.executeQuery();

            while (set.next()) {
                orderDetails.add(new OrderDetail(
                        set.getInt("table_id"),
                        set.getInt("price"),
                        set.getInt("count"),
                        set.getInt("price") * set.getInt("count"),
                        set.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return orderDetails;
    }
}
