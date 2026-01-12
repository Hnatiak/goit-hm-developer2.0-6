package org.example.db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {

    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection();
             Statement stmt = connection.createStatement()) {

            // Очищення таблиць
            stmt.execute("DROP TABLE IF EXISTS project_worker");
            stmt.execute("DROP TABLE IF EXISTS project");
            stmt.execute("DROP TABLE IF EXISTS client");
            stmt.execute("DROP TABLE IF EXISTS worker");

            System.out.println("DB cleared");

            // Створення таблиць з SQL файлу
            String sql = Files.readString(Path.of("sql/init_db.sql"));
            stmt.execute(sql);

            System.out.println("DB initialized");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}