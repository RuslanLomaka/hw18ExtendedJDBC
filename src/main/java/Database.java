import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SuppressWarnings("java:S6548") // Suppress SonarQube warning
// Singleton pattern is used here because Database connections must be managed globally.
public class Database {
    private Connection connection;
    private static final Database instance = new Database();
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    public static final String INIT_SQL = "sql/init_db.sql";
    public static final String POPULATE_SQL = "sql/populate_db.sql";
    public static final String MAX_PROJECTS_CLIENT_SQL = "sql/find_max_projects_client.sql";
    public static final String YOUNGEST_ELDEST_WORKERS_SQL = "sql/find_youngest_eldest_workers.sql";
    public static final String MAX_SALARY_WORKER_SQL = "sql/find_max_salary_worker.sql";
    public static final String FIND_LONGEST_PROJECT_SQL = "sql/find_longest_project.sql";
    public static final String PRINT_PROJECT_PRICES = "sql/print_project_prices.sql";
    public static final String CLEAR_ALL_TABLES_SQL ="sql/clear_all_tables.sql";

    static{
        LinkedList<String> statementsUrlList = new LinkedList<>();

        statementsUrlList.add(Database.MAX_PROJECTS_CLIENT_SQL);
        statementsUrlList.add(Database.YOUNGEST_ELDEST_WORKERS_SQL);
        statementsUrlList.add(Database.MAX_SALARY_WORKER_SQL);
        statementsUrlList.add(Database.FIND_LONGEST_PROJECT_SQL);
        statementsUrlList.add(Database.PRINT_PROJECT_PRICES);

        LinkedList<String> statementsList = new LinkedList<>();

        for (String statementUrl : statementsUrlList) {
            try (BufferedReader reader = new BufferedReader(new FileReader(statementUrl))) {
                StringBuilder sql = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sql.append(line).append(" ");
                    if (line.trim().endsWith(";")) {
                        statementsList.add(sql.toString().trim());
                        sql.setLength(0); // Clear the SQL builder for the next statement
                    }
                }
            } catch (Exception e) {
                logger.error("Error reading SQL file: {}", statementUrl, e);
            }
        }

        // Print statements for verification
        for (String statement : statementsList) {
            System.out.println(statement);
        }
    }


    public static Database getInstance() {
        if (instance.connection == null || isConnectionClosed(instance.connection)) {
            instance.initializeConnection();
        }
        return instance;
    }

    private static boolean isConnectionClosed(Connection connection) {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            logger.error("Failed to check if connection is closed: ", e);
            return true;
        }
    }


    private void initializeConnection() {
        String connectionURL = "jdbc:h2:./test";
        try {
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException e) {
            logger.error("Failed to initialize the database connection: ", e);
        }
    }

    private Database() {
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String connectionURL = "jdbc:h2:./test";
                connection = DriverManager.getConnection(connectionURL);
            }
        } catch (SQLException e) {
            logger.error("Failed to get a database connection: ", e);
        }
        return connection;
    }

    public void executeSqlStatement(String sqlFileUrl) {
        Connection conn = Database.getInstance().getConnection();
        try (Statement statement = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(sqlFileUrl))) {
            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    statement.execute(sql.toString());
                    sql.setLength(0);
                }
            }
            System.out.println(sqlFileUrl + "   Statement execution completed successfully!");
        } catch (Exception e) {
            logger.error("SQL statement problem: ", e);
        }
    }
}