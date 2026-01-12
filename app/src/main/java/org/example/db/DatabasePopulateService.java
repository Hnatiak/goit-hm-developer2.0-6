package org.example.db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

public class DatabasePopulateService {
    public static void main(String[] args) throws Exception {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();

        // Зчитуємо файл populate_db.sql
        String sql = Files.readString(Path.of("app/sql/populate_db.sql"));
        stmt.execute(sql);

        System.out.println("DB populated");
    }
}