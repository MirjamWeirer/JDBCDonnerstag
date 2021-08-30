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

    public Player addPlayer (Player p){
        String insertSQL = "INSERT INTO player (Firstname, Lastname, Nickname)";
        insertSQL += " VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(insertSQL)) {
            stmt.setString(1,p.getFirstname());
            stmt.setString(2,p.getLastname());
            stmt.setString(3,p.getNickname());

            stmt.executeUpdate();
            stmt.close();
            String sqlText ="SELECT last_insert_rowid() as rowid";
            PreparedStatement stmtAutoincrement = conn.prepareStatement(sqlText);
            ResultSet rs = stmtAutoincrement.executeQuery();
            rs.next();
            int autoincrementValue = rs.getInt("rowid");
            p.setPlayerId(autoincrementValue);
            System.out.println(autoincrementValue);
            stmtAutoincrement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public Player getPlayerByID (int playerId) {
        Player p = new Player();
        String sql = "SELECT * FROM player WHERE PlayerID = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(sql)) {
            stmt.setInt(1,playerId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                p.setPlayerId(playerId);
                p.setFirstname(rs.getString("Firstname"));
                p.setLastname(rs.getString("Lastname"));
                p.setNickname(rs.getString("Nickname"));
            }else {
                p.setFirstname("not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }
    //PlayerId -- WHERE
    public void updatePlayer(Player p){
        String updateSQL="";
        updateSQL = "UPDATE player SET ";
        updateSQL += " Firstname=?, ";
        updateSQL += " Lastname=?, ";
        updateSQL += " Nickname=? ";
        updateSQL += " WHERE PlayerID=? ";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setString(1,p.getFirstname());
            stmt.setString(2,p.getLastname());
            stmt.setString(3,p.getNickname());
            stmt.setInt(4,p.getPlayerId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public LovedGames addLovedGame (LovedGames l){
        String insertSQL = "INSERT INTO LovedGames (playerId, gameId, rank)";
        insertSQL += " VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(insertSQL)) {
            stmt.setInt(1,l.getPlayerId());
            stmt.setInt(2,l.getGameId());
            stmt.setInt(3, l.getRank());
            stmt.executeUpdate();
            stmt.close();
            String sqlText ="SELECT last_insert_rowid() as rowid";
            PreparedStatement stmtAutoincrement = conn.prepareStatement(sqlText);
            ResultSet rs = stmtAutoincrement.executeQuery();
            rs.next();
            int autoincrementValue = rs.getInt("rowid");
            l.setPlayerId(autoincrementValue);
            System.out.println(autoincrementValue);
            stmtAutoincrement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    public LovedGames getLovedGames (int lovedGamesId) {
        LovedGames l = new LovedGames();
        String sql = "SELECT * FROM LovedGames WHERE LovedGamesId = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(sql)) {
            stmt.setInt(1,lovedGamesId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                l.setGameId(rs.getInt("LovedGamesId"));
                l.setPlayerId(rs.getInt("PlayerId"));
                l.setPlayerId(rs.getInt("GameId"));
                l.setPlayerId(rs.getInt("Rank"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    public void updateLovedGames(LovedGames l){
        String updateSQL="";
        updateSQL = "UPDATE LovedGames SET ";
        updateSQL += " PlayerId=?, ";
        updateSQL += " GameId=?, ";
        updateSQL += " Rank=? ";
        updateSQL += " WHERE LovedGamesId=? ";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setInt(1,l.getPlayerId());
            stmt.setInt(2,l.getGameId());
            stmt.setInt(3,l.getRank());
            stmt.setInt(4,l.getLovedGamesId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Player getPlayerWithMostLovedGames(){
        Player p = new Player();
        String selectSQL = "SELECT PlayerId, Count(*)\n" +
                "FROM LovedGames\n" +
                "GROUP BY PlayerId\n" +
                "ORDER BY Count (*) DESC";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt =  conn.prepareStatement(selectSQL)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int playerId = rs.getInt("PlayerId");
               p = getPlayerByID(playerId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public int joinLovedGame(Game g, Player p, int l) { //int lovedGames?
        String selectSQL = "";
        boolean lastReadWasNull;
        int rank=0;
        selectSQL = "SELECT p.Nickname, l.Rank, g.GameName, l.Rank " +
                "FROM LovedGames AS l " +
                "INNER JOIN Player AS p " +
                "ON p.PlayerID = l.PlayerId " +
                "JOIN Game g " +
                "ON g.GameID = l.GameId";
        selectSQL += " WHERE p.PlayerId =? ";
        selectSQL += " AND g.GameId=? ";
        //selectSQL += " AND LovedGamesID=? ";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(selectSQL)) {
            stmt.setInt(1, p.getPlayerId());
            stmt.setInt(2, g.getGameId());
            //stmt.setInt(3, l.getLovedGamesID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                rank=rs.getInt("Rank");
                lastReadWasNull = rs.wasNull();
                System.out.println(lastReadWasNull);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rank;
    }

    public void printMetaData(){
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Player")){
             ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            int numerics = 0;

            for ( int i = 1; i <= meta.getColumnCount(); i++ )
            {
                System.out.printf( "%-20s %-20s%n", meta.getColumnLabel( i ),
                        meta.getColumnTypeName( i ) );

                if ( meta.isSigned( i ) )
                    numerics++;
            }

            System.out.println();
            System.out.println( "Spalten: " + meta.getColumnCount() +
                    ", Numerisch: " + numerics );
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
    }
}
