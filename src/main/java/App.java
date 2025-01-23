import database_entities.combined.*;

import java.util.List;

public class App {
    public static void main(String[] args) {
        new DatabaseInitService().init();
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.populate();
        databasePopulateService.collectionsInit();
        databasePopulateService.clearAllTables();
        databasePopulateService.populateFromCollections();




        DatabaseQueryService queryService = new DatabaseQueryService();

        long startTime = System.currentTimeMillis();

        // Max Project Count Clients
        List<MaxProjectCountClient> clientsWithMaxProjectCounts = queryService.findMaxProjectCountClients();
        System.out.println("Clients with Max Project Counts:");
        for (MaxProjectCountClient client : clientsWithMaxProjectCounts) {
            System.out.println(client);
        }

        // Longest Projects
        List<LongestProject> longestProjects = queryService.findLongestProjects();
        System.out.println("Longest Projects:");
        for (LongestProject project : longestProjects) {
            System.out.println(project);
        }

        // Max Salary Workers
        List<MaxSalaryWorker> maxSalaryWorkers = queryService.findMaxSalaryWorkers();
        System.out.println("Workers with Max Salaries:");
        for (MaxSalaryWorker worker : maxSalaryWorkers) {
            System.out.println(worker);
        }

        // Youngest and Eldest Workers
        List<WorkerByAge> youngestAndEldestWorkers = queryService.findYoungestAndEldestWorkers();
        System.out.println("Youngest and Eldest Workers:");
        for (WorkerByAge worker : youngestAndEldestWorkers) {
            System.out.println(worker);
        }

        // Project Prices
        List<ProjectPrice> projectPrices = queryService.findProjectPrices();
        System.out.println("Project Prices:");
        for (ProjectPrice projectPrice : projectPrices) {
            System.out.println(projectPrice);
        }




        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + "ms");
    }
}
