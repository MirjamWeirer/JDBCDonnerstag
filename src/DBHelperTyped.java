import java.sql.*;
import java.util.ArrayList;

public class DBHelperTyped {

    private String url ="jdbc:sqlite:C:/sqlite/db/Donnerstag.db";

    public void addGame (Game g){
        String insertSQL = "INSERT INTO game (GameName, GameGenre, MaxLevel)";
        insertSQL += " VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(insertSQL)) {
            stmt.setString(1,g.getGameName());
            stmt.setString(2,g.getGameGenre());
            stmt.setInt(3,g.getMaxLevel());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Game> getAllGamesByGenre(String genre){
        ArrayList<Game> meineSpiele = new ArrayList<Game>();

        String sql = "SELECT * FROM game WHERE GameGenre = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(sql)) {
            stmt.setString(1,genre);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int gameId = rs.getInt("GameId");
                Game g = getGameById(gameId);
                meineSpiele.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return meineSpiele;
    }
    public Game getGameById(int gameId){
        Game g = new Game();
        String sql = "SELECT * FROM game WHERE GameID = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(sql)) {
            stmt.setInt(1,gameId);
          
            ResultSet rs = stmt.executeQuery();
           if (rs.next()){
               g.setGameId(gameId);
               g.setGameName(rs.getString("GameName"));
               g.setGameGenre(rs.getString("GameGenre"));
               g.setMaxLevel(rs.getInt("MaxLevel"));
           }else {
               g.setGameName("not found");
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return g;
    }

    public int updateGame(Game g){
        int afferdRows = 0;
        String updateSQL="";
        updateSQL = "UPDATE Game SET ";
        updateSQL += " GameName=?, ";
        updateSQL += " GameGenre=?, ";
        updateSQL += " MaxLevel=? ";
        updateSQL += " WHERE GameId=? ";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setString(1,g.getGameName());
            stmt.setString(2,g.getGameGenre());
            stmt.setInt(3,g.getMaxLevel());
            stmt.setInt(4,g.getGameId());
            afferdRows = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return afferdRows;
    }

    public int incrementMaxLevelByXforGenre(int incrementValue, String genre){
        int afferdRows = 0;
        String updateSQL="";
        updateSQL = "UPDATE Game SET ";
        updateSQL += " MaxLevel= MaxLeveL + ? ";
        updateSQL += " WHERE GameGenre =? ";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setInt(1,incrementValue);
            stmt.setString(2,genre);

            afferdRows = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return afferdRows;
    }
}
