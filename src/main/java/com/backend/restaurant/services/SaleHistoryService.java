package com.backend.restaurant.services;


import com.backend.restaurant.helper.DBHelper;
import com.backend.restaurant.models.Sale;
import com.backend.restaurant.models.SaleHsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleHistoryService {

    public List<Sale> getAllHistory() {
        List<Sale> histories = new ArrayList<>();

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        String query = "SELECT * FROM sale";


        try {
            ps = con.prepareStatement(query);
            set = ps.executeQuery();

            while (set.next()) {
                histories.add(new Sale(
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

        return histories;
    }
}
