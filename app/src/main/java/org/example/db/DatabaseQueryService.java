package org.example.db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.model.LongestProject;
import org.example.model.MaxProjectCountClient;
import org.example.model.MaxSalaryWorker;
import org.example.model.ProjectPrice;
import org.example.model.YoungestEldestWorker;

public class DatabaseQueryService {

    public List<LongestProject> findLongestProject() throws Exception {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String sql = Files.readString(Path.of("app/sql/find_longest_project.sql"));
        ResultSet rs = stmt.executeQuery(sql);

        List<LongestProject> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new LongestProject(
                    rs.getString("name"),
                    rs.getInt("month_count")
            ));
        }
        return list;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws Exception {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String sql = Files.readString(Path.of("app/sql/find_max_projects_client.sql"));
        ResultSet rs = stmt.executeQuery(sql);

        List<MaxProjectCountClient> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new MaxProjectCountClient(
                    rs.getString("name"),
                    rs.getInt("project_count")
            ));
        }
        return list;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws Exception {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String sql = Files.readString(Path.of("app/sql/find_max_salary_worker.sql"));
        ResultSet rs = stmt.executeQuery(sql);

        List<MaxSalaryWorker> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new MaxSalaryWorker(
                    rs.getString("name"),
                    rs.getInt("salary")
            ));
        }
        return list;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws Exception {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String sql = Files.readString(Path.of("app/sql/find_youngest_eldest_workers.sql"));
        ResultSet rs = stmt.executeQuery(sql);

        List<YoungestEldestWorker> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new YoungestEldestWorker(
                    rs.getString("type"),
                    rs.getString("name"),
                    rs.getDate("birthday").toLocalDate()
            ));
        }
        return list;
    }

    public List<ProjectPrice> printProjectPrices() throws Exception {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String sql = Files.readString(Path.of("app/sql/print_project_prices.sql"));
        ResultSet rs = stmt.executeQuery(sql);

        List<ProjectPrice> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new ProjectPrice(
                    rs.getString("name"),
                    rs.getInt("price")
            ));
        }
        return list;
    }
}