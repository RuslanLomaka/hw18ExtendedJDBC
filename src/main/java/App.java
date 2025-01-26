import database_entities.combined.*;
import database_entities.simple.Client;
import java.util.List;

public class App {
    public static void main(String[] args) {
        new DatabaseInitService().init();
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.collectionsInit();//populate the collections from DB
        databasePopulateService.clearAllTables();//clear DB's content
        databasePopulateService.populateFromCollections();//populate DB using prepared statements and batch processing

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
        // Testing ClientService methods
        ClientService clientService = new ClientService();

        System.out.println("\n--- Testing ClientService Methods ---");

        // 1. Create a client
        System.out.println("Creating a new client...");
        long newClientId = clientService.create("Test Client");
        System.out.println("Created client with ID: " + newClientId);

        //1.1 Create a client with a name that is too long
        System.out.println("Creating a client with a name that is too long");
        StringBuilder longName = new StringBuilder();
        longName.append("a".repeat(1001));
        try {
            clientService.create(longName.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // 2. Get the created client by ID
        System.out.println("\nFetching client by ID...");
        String client = clientService.getById(newClientId);
        System.out.println("Fetched client: " + client);

        // 3. Update the client's name
        System.out.println("\nUpdating client name...");
        clientService.setName(newClientId, "Updated Client");
        String updatedClient = clientService.getById(newClientId);
        System.out.println("Updated client: " + updatedClient);

        // 4. List all clients
        System.out.println("\nListing all clients...");
        List<Client> clients = clientService.listAll();
        System.out.println("Clients in the database:");
        for (Client c : clients) {
            System.out.println(c);
        }

        // 5. Delete the client
        System.out.println("\nDeleting client...");
        clientService.deleteById(newClientId);
        try {
            clientService.getById(newClientId);
        } catch (exceptions.ClientNotFoundException e) {
            System.out.println("Client successfully deleted. Exception caught: " + e.getMessage());
        }


        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + "ms");
    }
}