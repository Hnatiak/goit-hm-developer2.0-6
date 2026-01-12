package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            // in-memory база H2
            connection = DriverManager.getConnection("jdbc:h2:~/testdb;DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot connect to DB");
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}