import java.sql.*;

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
}
