import java.sql.*;

public class DBHelper {
    public  void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void connect (String filename){
        String url = "jdbc:sqlite:C:/sqlite/db/" + filename;
        try (Connection conn = DriverManager.getConnection(url)){
            System.out.println("Connection to SQLite has been established.");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void createGameTable(String filename){
        String url = "jdbc:sqlite:C:/sqlite/db/" + filename;
        String sql = "CREATE TABLE IF NOT EXISTS game (\n"
                + "GameId INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "GameName VARCHAR(255) NOT NULL, \n"
                + "GameGenre VARCHAR(255) NOT NULL,\n"
                + "MaxLevel INTEGER\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);

            String insStatement = "INSERT INTO Game (GameName, GameGenre, MaxLevel)";
            insStatement += "VALUES ('Solitaire','Action',100)";

            stmt.execute(insStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
