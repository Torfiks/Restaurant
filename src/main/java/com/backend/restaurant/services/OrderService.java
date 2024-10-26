package com.backend.restaurant.services;

import com.backend.restaurant.helper.DBHelper;
import com.backend.restaurant.models.Dish;
import com.backend.restaurant.models.Order;
import com.backend.restaurant.models.OrderDetail;
import com.backend.restaurant.models.OrderWaiter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;




public class OrderService {

    public static int totalPrice(int id){

        String query = "SELECT " +
                "o.price, " +
                "o.count " +
                "FROM " +
                "orders as o " +
                "WHERE " +
                "o.table_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;
        int count = 0;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            set = ps.executeQuery();

            while (set.next()) {
                count += set.getInt("price") * set.getInt("count"); ;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return count;
    }

    public void updateOrderWaiter(OrderWaiter orderWaiter) {

        boolean condition = false;

        String query = "UPDATE orders SET count=? where dish_id=? AND table_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, orderWaiter.getCount());
            ps.setInt(2, orderWaiter.getIdDishes());
            ps.setInt(3, orderWaiter.getNumTable());

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);

    }

    public void addOrderWaiter(OrderWaiter orderWaiter) {
        boolean condition = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        String query = "INSERT INTO orders (table_id, dish_id, price, count) VALUES (?,?,?,?)";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, orderWaiter.getNumTable());
            ps.setInt(2, orderWaiter.getIdDishes());
            ps.setInt(3, orderWaiter.getPrice());
            ps.setInt(4, orderWaiter.getCount());

            int result = ps.executeUpdate();
            condition = true;

            if (result == 1) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    orderWaiter.setId(rs.getInt(1)); // Установить сгенерированный идентификатор заказа
                    condition = true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);


    }

    public void deleteCount(int newCount, OrderWaiter orderWaiter){
        boolean condition = false;

        String query = "UPDATE orders SET count=? where dish_id=? AND table_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, newCount);
            ps.setInt(2, orderWaiter.getIdDishes());
            ps.setInt(3, orderWaiter.getNumTable());

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);
    }

    public void deleteDish(int dish_id,int table_id){
        boolean condition = false;

        String query = "DELETE FROM orders WHERE dish_id=? AND table_id=?";
        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement(query);
            ps.setInt(1, dish_id);
            ps.setInt(2, table_id);

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, null);

    }

    public static List<OrderWaiter> getOrderDetailByTableIdWaiter(int id) {

        List<OrderWaiter> orderWaiterList = new ArrayList<>();

        String query = "SELECT " +
                "d.name as name, " +
                "o.price, " +
                "o.count, " +
                "o.dish_id,"+
                "o.table_id " +
                "FROM " +
                "orders as o " +
                "RIGHT JOIN " +
                "dishes as d " +
                "ON " +
                "o.dish_id=d.id " +
                "WHERE " +
                "o.table_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            set = ps.executeQuery();

            while (set.next()) {
                orderWaiterList.add(new OrderWaiter(
                        id,
                        set.getInt("table_id"),
                        set.getInt("dish_id"),
                        set.getInt("price"),
                        set.getInt("count")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return orderWaiterList;
    }

    public static List<OrderWaiter> getOllOrderDetailByTableIdWaiter(int id) {

        List<OrderWaiter> orderWaiterList = new ArrayList<>();

        String query = "SELECT " +
                "o.price, " +
                "o.count, " +
                "o.dish_id,"+
                "o.table_id " +
                "FROM " +
                "orders as o " +
                "WHERE " +
                "o.dish_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            set = ps.executeQuery();

            while (set.next()) {
                orderWaiterList.add(new OrderWaiter(
                        id,
                        set.getInt("table_id"),
                        set.getInt("dish_id"),
                        set.getInt("price"),
                        set.getInt("count")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return orderWaiterList;
    }

    public List<OrderDetail> getOrderDetailByTableId(int id) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT \n" +
                "\td.name as name,\n" +
                "    o.price,\n" +
                "    o.count\n" +
                "FROM \n" +
                "\torders as o \n" +
                "RIGHT JOIN \n" +
                "\tdishes as d \n" +
                "ON \n" +
                "\to.dish_id=d.id \n" +
                "WHERE \n" +
                "\to.table_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, id);

            set = ps.executeQuery();
            while (set.next()) {
                orderDetails.add(new OrderDetail(
                        id,
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