package org.example.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {

    private static String loadSql(String resource) throws Exception {
        try (InputStream is = DatabaseInitService.class
                .getClassLoader()
                .getResourceAsStream(resource)) {

            if (is == null) {
                throw new RuntimeException("SQL file not found: " + resource);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("DB cleared");

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS project_worker");
            stmt.execute("DROP TABLE IF EXISTS project");
            stmt.execute("DROP TABLE IF EXISTS client");
            stmt.execute("DROP TABLE IF EXISTS worker");

            String sql = loadSql("sql/init_db.sql");
            stmt.execute(sql);
        }

        System.out.println("DB initialized");
    }
}
