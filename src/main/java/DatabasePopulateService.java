import database_entities.simple.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePopulateService {
    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulateService.class);
    private static final String INSERT_WORKER_SQL =
            "INSERT INTO worker (ID, NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_PROJECT_SQL =
            "INSERT INTO project (ID, CLIENT_ID, NAME, START_DATE, FINISH_DATE) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_CLIENT_SQL =
            "INSERT INTO client (ID, NAME) VALUES (?, ?);";
    private static final String INSERT_PROJECT_WORKER_SQL =
            "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?);";

    private final List<Worker> workersList = new ArrayList<>();
    private final List<Project> projectsList = new ArrayList<>();
    private final List<Client> clientsList = new ArrayList<>();
    private final List<ProjectWorker> projectWorkersList = new ArrayList<>();

    public void clearAllTables() {
        Database.getInstance().executeSqlStatement(Database.CLEAR_ALL_TABLES_SQL);
    }

    public void populateFromCollections() {
        Connection connection = Database.getInstance().getConnection();

        try (PreparedStatement workerStatement = connection.prepareStatement(INSERT_WORKER_SQL)) {
            for (Worker worker : workersList) {
                workerStatement.setInt(1, worker.id());
                workerStatement.setString(2, worker.name());
                workerStatement.setString(3, worker.birthday());
                workerStatement.setString(4, worker.level());
                workerStatement.setInt(5, worker.salary());
                workerStatement.addBatch();
                workerStatement.executeBatch();
            }
        } catch (SQLException e) {
            logger.error("Something wrong with the population from Workers collection", e);
        }

        try (PreparedStatement clientStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            for (Client client : clientsList) {
                clientStatement.setInt(1, client.id());
                clientStatement.setString(2, client.name());
                clientStatement.addBatch();
            }
            clientStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("Something wrong with the population from Clients collection", e);
        }



        try (PreparedStatement projectStatement = connection.prepareStatement(INSERT_PROJECT_SQL)) {
            for (Project project : projectsList) {
                projectStatement.setInt(1, project.id());
                projectStatement.setInt(2, project.clientId());
                projectStatement.setString(3, project.name());
                projectStatement.setString(4, project.startDate());
                projectStatement.setString(5, project.finishDate());
                projectStatement.addBatch();
            }
            projectStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("Something wrong with the population from Projects collection", e);
        }


        try (PreparedStatement projectWorkerStatement = connection.prepareStatement(INSERT_PROJECT_WORKER_SQL)) {
            for (ProjectWorker projectWorker : projectWorkersList) {
                projectWorkerStatement.setInt(1, projectWorker.projectId());
                projectWorkerStatement.setInt(2, projectWorker.workerId());
                projectWorkerStatement.addBatch();
            }
            projectWorkerStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("Something wrong with the population from collections", e);
        }

    }

    public void collectionsInit() {
        //here im planning to fill the collection of objects with the values from the database
        Connection connection = Database.getInstance().getConnection();
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {

            statement.execute("SELECT WORKER.ID, NAME, BIRTHDAY, LEVEL, SALARY FROM worker");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                workersList.add(new Worker(
                        resultSet.getInt("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("BIRTHDAY"),
                        resultSet.getString("LEVEL"),
                        resultSet.getInt("SALARY")
                ));
            }
            statement.execute("SELECT PROJECT.ID,CLIENT_ID,NAME,START_DATE,FINISH_DATE FROM project");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                projectsList.add(new Project(
                        resultSet.getInt("ID"),
                        resultSet.getInt("CLIENT_ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("START_DATE"),
                        resultSet.getString("FINISH_DATE")
                ));
            }
            statement.execute("SELECT CLIENT.id, name FROM client");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                clientsList.add(new Client(
                        resultSet.getInt("ID"),
                        resultSet.getString("NAME")
                ));
            }
            statement.execute("SELECT PROJECT_WORKER.PROJECT_ID, WORKER_ID FROM project_worker");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                projectWorkersList.add(new ProjectWorker(
                        resultSet.getInt("PROJECT_ID"),
                        resultSet.getInt("WORKER_ID")
                ));
            }
        } catch (SQLException e) {
            logger.error("Something wrong with the collection initialization", e);
        }

        System.out.println("Workers list: " + workersList);
        System.out.println("Projects list = " + projectsList);
        System.out.println("Clients list = " + clientsList);
        System.out.println("Project-Workers list = " + projectWorkersList);

    }

    public void populate() {
        Database.getInstance().executeSqlStatement(Database.POPULATE_SQL);
    }
}