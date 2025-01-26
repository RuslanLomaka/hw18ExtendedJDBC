import database_entities.simple.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
*
* ---FOR REFERENCE--- script to create the table in the database
        CREATE TABLE client (
        ID INT PRIMARY KEY,
        NAME VARCHAR(1000) NOT NULL);
*/

public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);


    public String getById(long id) {
        try (PreparedStatement preparedStatement = Database.getInstance().getConnection()
                .prepareStatement("SELECT CLIENT.id, name FROM client WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client cl = new Client(
                            resultSet.getInt("ID"),
                            resultSet.getString("NAME")
                    );
                    return cl.toString();
                } else {
                    logger.warn("Client with id={} not found", id);
                    return "Client not found";
                }
            }
        } catch (SQLException e) {
            logger.error("Error accessing database when fetching client by id={}", id, e);
            return "Error: Unable to fetch client";
        }
    }


    void setName(long id, String name) {
        if (name.length() > 1000 || name.isEmpty()) {
            throw new IllegalArgumentException("Name must be VARCHAR(1000) NOT NULL");
        }
        Connection connection = Database.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?;")) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            logger.error("Something wrong when setting client's name by id= {}, name= {}", id, name, e);
        }
    }

    void deleteById(long id) {
        Connection connection = Database.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM client WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            logger.error("Something wrong when deleting client by id={}", id, e);
        }
    }

    List<Client> listAll() {
        List<Client> clientsList = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT CLIENT.id, name FROM client");
            while (resultSet.next()) {
                clientsList.add(new Client(
                        resultSet.getInt("ID"),
                        resultSet.getString("NAME")
                ));
            }
        } catch (
                SQLException e) {
            logger.error("Something wrong when getting list of clients", e);
        }
        return clientsList;
    }

    long create(String name) throws IllegalArgumentException {
        if (name.length() > 1000 || name.isEmpty()) {
            throw new IllegalArgumentException("Name must be VARCHAR(1000) NOT NULL");
        }
        long id = 0;
        try (PreparedStatement preparedStatement = Database.getInstance().getConnection()
                .prepareStatement("INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Something went wrong when adding client with name={}", name, e);
        }
        return id;
    }


}