import java.sql.*;
import java.util.ArrayList;

public class Player {
    private int PlayerId;
    private String Firstname, Lastname, Nickname;
    private String url ="jdbc:sqlite:C:/sqlite/db/Donnerstag.db";
    private float points;
    ArrayList<LovedGames> myLovedGames;
    ArrayList<Game> myLovedGamesOOP;


    public Player() {

    }

    public Player(int playerId, String firstname, String lastname, String nickname) {
        PlayerId = playerId;
        Firstname = firstname;
        Lastname = lastname;
        Nickname = nickname;
    }

    public int getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(int playerId) {
        PlayerId = playerId;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    @Override
    public String toString()  {
        return "Player{" +
                "PlayerId=" + PlayerId +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Nickname='" + Nickname + '\'' +
                '}';
    }

    public void fillLovedGames(){
        if (myLovedGames == null) {
            myLovedGames = new ArrayList<>();
        }else {
            myLovedGames.clear();
        }
        String sqlSelect = "SELECT * FROM LovedGames WHERE PlayerId = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(sqlSelect)) {
            stmt.setInt(1,PlayerId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                LovedGames g = new LovedGames();
                g.setPlayerId(PlayerId);
                g.setLovedGamesId(rs.getInt("LovedGamesId"));
                g.setPlayerId(rs.getInt("PlayerId"));
                g.setGameId(rs.getInt("GameId"));
                g.setRank(rs.getInt("Rank"));
                myLovedGames.add(g);
            }
            System.out.println(myLovedGames);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void fillLovedGamesOOP(){
        if (myLovedGamesOOP == null) {
            myLovedGamesOOP = new ArrayList<>();
        }else {
            myLovedGamesOOP.clear();
        }
        DBHelperTyped dbHelperTyped = new DBHelperTyped();
        String sqlSelect = "SELECT * FROM LovedGames WHERE PlayerId = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt =  conn.prepareStatement(sqlSelect)) {
            stmt.setInt(1,PlayerId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Game g = dbHelperTyped.getGameById(rs.getInt("GameId"));
                myLovedGamesOOP.add(g);
            }
            //System.out.println(myLovedGames);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addLovedGame (Game g){
        String insSQL = " INSERT INTO LovedGames (PlayerId, GameId)";
        insSQL += " VALUES(?,?)";
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement stmt =  conn.prepareStatement(insSQL)) {
                stmt.setInt(1,getPlayerId());
                stmt.setInt(2,g.getGameId());
                //stmt.setInt(3, l.getRank());
                stmt.executeUpdate();
//                stmt.close();
//                String sqlText ="SELECT last_insert_rowid() as rowid";
//                PreparedStatement stmtAutoincrement = conn.prepareStatement(sqlText);
//                ResultSet rs = stmtAutoincrement.executeQuery();
//                rs.next();
//                int autoincrementValue = rs.getInt("rowid");
//                l.setPlayerId(autoincrementValue);
//                System.out.println(autoincrementValue);
//                stmtAutoincrement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void setMyLovedGames(ArrayList<LovedGames> myLovedGames) {
        this.myLovedGames = myLovedGames;
    }

    public ArrayList<LovedGames> getMyLovedGames() {
        return myLovedGames;
    }

    public ArrayList<Game> getMyLovedGamesOOP() {
        return myLovedGamesOOP;
    }

    public void setMyLovedGamesOOP(ArrayList<Game> myLovedGamesOOP) {
        this.myLovedGamesOOP = myLovedGamesOOP;
    }

    public void alterTable(){
        String alterSQL = "ALTER TABLE player ADD COLUMN Points DECIMAL;";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(alterSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePoints(){
        String sqlUpdate = "UPDATE player SET Points = 100";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferPointsFromUserAToB(int playerId, int playerIdB, int points){
        String updateSQLPlayerA ="UPDATE player SET ";
            updateSQLPlayerA += " Points = Points - ? ";
            updateSQLPlayerA += " WHERE PlayerId = ? ";
        String updateSQLPlayerB ="UPDATE player SET ";
            updateSQLPlayerB += " Points = Points + ?";
            updateSQLPlayerB += " WHERE PlayerId = ?";
        String selectPlayerA = "SELECT * FROM Player WHERE PlayerId = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(updateSQLPlayerA);
             PreparedStatement stmtB = conn.prepareStatement(updateSQLPlayerB);
             PreparedStatement stmtSelectPlayer = conn.prepareStatement(selectPlayerA)) {
            stmt.setFloat(1,points);
            stmt.setInt(2,playerId);
            stmtB.setFloat(1,points);
            stmtB.setInt(2,playerIdB);
            stmtSelectPlayer.setInt(1,playerId);
            conn.setAutoCommit(false);

            stmt.executeUpdate();
            stmtB.executeUpdate();
            ResultSet rs = stmtSelectPlayer.executeQuery();
            rs.next();
            float newPoints = rs.getFloat("Points");

            if (newPoints >= 0){
                conn.commit();
            }else {
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
