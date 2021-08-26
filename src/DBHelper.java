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

    public void readAllGames(String filename){
        String url = "jdbc:sqlite:C:/sqlite/db/" + filename;
        //SQL statement Auslesen von Zeilen
        String sql = "SELECT * FROM GAME";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            /*rs.next();

            String gameName = rs.getString(2);
            gameName = rs.getString("GameName");
            System.out.println(gameName);

             */

            while (rs.next()){
                String gameName = rs.getString("GameName");
                System.out.println(gameName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void readGamesFilteredByGenre(String genre){
        //String url = "jdbc:sqlite:C:/sqlite/db/" + filename;
        String sql = "SELECT * FROM GAME WHERE GameGenre =' " + genre +"'";
        helpReadSqlStatement(sql);
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(sql);
//            String gameName = rs.getString("GameName");
//            String gameGenre = rs.getString("GameGenre");
//
//            while (rs.next()){
//                System.out.println(gameName + " " + gameGenre);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void readGamesFlteredByMaxLevel(int maxLevel){
       // String url = "jdbc:sqlite:C:/sqlite/db/" + filename;
        String sql = "SELECT * FROM GAME WHERE MaxLevel >' " + maxLevel + "'";
        helpReadSqlStatement(sql);

//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(sql);
//            String gameName = rs.getString("GameName");
//            //String gameGenre = rs.getString("GameGenre");
//
//            while (rs.next()){
//                System.out.println(gameName);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void readGamesOrderBy(String orderColumn){
        //String url = "jdbc:sqlite:C:/sqlite/db/" + filename;
        String sql = "SELECT * FROM GAME ORDER BY ' " +orderColumn + "'";
        helpReadSqlStatement(sql);
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(sql);
//            String gameName = rs.getString("GameName");
//            //String gameGenre = rs.getString("GameGenre");
//
//            while (rs.next()){
//                System.out.println(gameName);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private void helpReadSqlStatement (String sql){
        String fileName = "Donnerstag.db";
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        //SQL statement Auslesen von Zeilen


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            /*rs.next();

            String gameName = rs.getString(2);
            gameName = rs.getString("GameName");
            System.out.println(gameName);

             */

            while (rs.next()){
                String gameName = rs.getString("GameName");
                System.out.println(gameName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
