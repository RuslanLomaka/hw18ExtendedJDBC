import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void init() {
        String connectionUrl = "jdbc:h2:./test";

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.
                configure().
                dataSource(connectionUrl, null, null)
                .load();

        // Start the migration
        flyway.migrate();


    }
}