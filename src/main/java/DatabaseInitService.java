
public class DatabaseInitService {
    public void init() {
        Database.getInstance().executeSqlStatement(Database.INIT_SQL);
    }
}