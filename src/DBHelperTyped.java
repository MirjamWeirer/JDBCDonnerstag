import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
