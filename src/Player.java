import java.sql.*;
import java.util.ArrayList;

public class Player {
    private int PlayerId;
    private String Firstname, Lastname, Nickname;
    private String url ="jdbc:sqlite:C:/sqlite/db/Donnerstag.db";
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
}
