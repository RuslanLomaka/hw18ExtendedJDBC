import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseInitService {
    public void init() {
        Database.getInstance().executeSqlStatement(Database.INIT_SQL);
    }


    public void executeBatch(List<String> sqlStatements) {


        for(String sql : sqlStatements){

        }


        try (Connection conn = Database.getInstance().getConnection();
             Statement statement = conn.createStatement()) {
            for (String sql : sqlStatements) {
                statement.addBatch(sql);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error("SQL statement problem: ", e);
        }
    }


}