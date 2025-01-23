import database_entities.*;
import database_entities.combined.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseQueryService.class);

    public List<MaxProjectCountClient> findMaxProjectCountClients() {
        return executeQuery(
                Database.MAX_PROJECTS_CLIENT_SQL,
                resultSet -> new MaxProjectCountClient(
                        resultSet.getString("NAME"),
                        resultSet.getInt("project_count")
                )
        );
    }

    public List<LongestProject> findLongestProjects() {
        return executeQuery(
                Database.FIND_LONGEST_PROJECT_SQL,
                resultSet -> new LongestProject(
                        resultSet.getString("NAME"),
                        resultSet.getInt("project_length"),
                        resultSet.getInt("ID")
                )
        );
    }

    public List<MaxSalaryWorker> findMaxSalaryWorkers() {
        return executeQuery(
                Database.MAX_SALARY_WORKER_SQL,
                resultSet -> new MaxSalaryWorker(
                        resultSet.getString("NAME"),
                        resultSet.getInt("SALARY")
                )
        );
    }

    public List<WorkerByAge> findYoungestAndEldestWorkers() {
        return executeQuery(
                Database.YOUNGEST_ELDEST_WORKERS_SQL,
                resultSet -> new WorkerByAge(
                        resultSet.getString("TYPE"),
                        resultSet.getString("NAME"),
                        resultSet.getString("BIRTHDAY")
                )
        );
    }

    public List<ProjectPrice> findProjectPrices() {
        return executeQuery(
                Database.PRINT_PROJECT_PRICES,
                resultSet -> new ProjectPrice(
                        resultSet.getString("NAME"),
                        resultSet.getInt("PRICE")
                )
        );
    }

    private <E extends DataBaseEntity> List<E> executeQuery(String sqlFileUrl, ResultSetMapper<E> mapper) {
        List<E> entities = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             Statement statement = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(sqlFileUrl))) {

            StringBuilder sql = new StringBuilder();
            String line;
            String resultSetData = "";
            while ((line = reader.readLine()) != null) {
                sql.append(" ").append(line);
                if (line.trim().endsWith(";")) {
                    try (ResultSet resultSet = statement.executeQuery(sql.toString())) {
                        resultSetData = resultSet.toString();
                        while (resultSet.next()) {
                            entities.add(mapper.map(resultSet));
                        }
                    }
                    sql.setLength(0); // Clear the SQL builder for the next statement
                }
            }
            System.out.println("\nData retrieved by " + sqlFileUrl + " query" + "\n" + "ResultSet:" + resultSetData);
        } catch (Exception e) {
            logger.error("SQL statement problem: ", e);
        }
        return entities;
    }
}