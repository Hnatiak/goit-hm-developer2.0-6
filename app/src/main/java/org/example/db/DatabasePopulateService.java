package org.example.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class DatabasePopulateService {

    private static String loadSql(String resource) throws Exception {
        try (InputStream is = DatabasePopulateService.class
                .getClassLoader()
                .getResourceAsStream(resource)) {

            if (is == null) {
                throw new RuntimeException("SQL file not found: " + resource);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws Exception {
        String sql = loadSql("sql/populate_db.sql");

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        }

        System.out.println("DB populated");
    }
}