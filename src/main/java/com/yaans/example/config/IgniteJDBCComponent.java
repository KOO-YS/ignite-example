package com.yaans.example.config;

import com.yaans.example.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

@Slf4j
public class IgniteJDBCComponent {

    private IgniteCache<String, Product> cache;

    private Connection conn;

    public IgniteJDBCComponent(IgniteComponent server) throws ClassNotFoundException, SQLException {
        cache = server.getIgnite().cache("EXAMPLE_JDBC");

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");
        initTables();
    }

    public void initTables() throws SQLException {
        String DDL = "CREATE TABLE IF NOT EXISTS " +
            "Product (" +
                "id Integer PRIMARY KEY, " +
                "name VARCHAR, " +
                "isOnSale tinyint(1)" +
            ") " +
            "WITH \"template=replicated\"";


        try (Statement stmt = conn.createStatement()) {
            log.info("start Cache query DDL : {}", DDL);
            stmt.executeUpdate(DDL);
        }

    }

    public int put(Product p) {
        String insertDML = "INSERT INTO Product (id, name, isOnSale) VALUES (?, ?, ?)";

        log.info("start Cache query insert data : {}", p);

        int id = new Random().nextInt(2000);
        try (PreparedStatement stmt = conn.prepareStatement(insertDML)) {
            stmt.setInt(1, id);
            stmt.setString(2, p.getName());
            stmt.setBoolean(3, p.isOnSale());

            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public void get(int id) {
        String selectDML = "SELECT p.id, p.name, p.isOnSale FROM PRODUCT p WHERE p.id=?";
//        String selectDML = "SELECT * FROM PRODUCT WHERE id = ?";

        log.info("start Cache query select data : {}", id);

        try (PreparedStatement stmt = conn.prepareStatement(selectDML)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                log.info("selected data : \n{}, {}, {}", rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

