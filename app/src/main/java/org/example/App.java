package org.example;

import java.util.List;

import org.example.db.DatabaseInitService;
import org.example.db.DatabasePopulateService;
import org.example.db.DatabaseQueryService;
import org.example.model.LongestProject;
import org.example.model.MaxProjectCountClient;
import org.example.model.MaxSalaryWorker;
import org.example.model.ProjectPrice;
import org.example.model.YoungestEldestWorker;

public class App {
    public static void main(String[] args) throws Exception {

        // 1️⃣ Ініціалізація БД (створює таблиці)
        DatabaseInitService.main(args);

        // 2️⃣ Наповнення БД
        DatabasePopulateService.main(args);

        // 3️⃣ Виконання SELECT запитів
        DatabaseQueryService service = new DatabaseQueryService();

        List<MaxProjectCountClient> maxProjects = service.findMaxProjectsClient();
        System.out.println("Max Projects Client: " + maxProjects);

        List<LongestProject> longestProjects = service.findLongestProject();
        System.out.println("Longest Projects: " + longestProjects);

        List<MaxSalaryWorker> maxSalaryWorkers = service.findMaxSalaryWorker();
        System.out.println("Max Salary Workers: " + maxSalaryWorkers);

        List<YoungestEldestWorker> youngestEldest = service.findYoungestEldestWorkers();
        System.out.println("Youngest/Eldest Workers: " + youngestEldest);

        List<ProjectPrice> projectPrices = service.printProjectPrices();
        System.out.println("Project Prices: " + projectPrices);
    }
}