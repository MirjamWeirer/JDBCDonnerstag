import org.sqlite.SQLiteConfig;

import java.sql.*;

public class GameLanguagesAndAge {
    private int gameLanguagesAndAgeId, gameId;
    private String nameDeutsch, nameEnglish, nameSpanisch;
    private double minAge;

    private String connectionString ="jdbc:sqlite:C:/sqlite/db/Donnerstag.db";
    public GameLanguagesAndAge() {
    }

    public GameLanguagesAndAge(int gameLanguagesAndAgeId, int gameId, String nameDeutsch, String nameEnglish, String nameSpanisch, double minAge) {
        this.gameLanguagesAndAgeId = gameLanguagesAndAgeId;
        this.gameId = gameId;
        this.nameDeutsch = nameDeutsch;
        this.nameEnglish = nameEnglish;
        this.nameSpanisch = nameSpanisch;
        this.minAge = minAge;
    }

    public int getGameLanguagesAndAgeId() {
        return gameLanguagesAndAgeId;
    }

    public void setGameLanguagesAndAgeId(int gameLanguagesAndAgeId) {
        this.gameLanguagesAndAgeId = gameLanguagesAndAgeId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getNameDeutsch() {
        return nameDeutsch;
    }

    public void setNameDeutsch(String nameDeutsch) {
        this.nameDeutsch = nameDeutsch;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameSpanisch() {
        return nameSpanisch;
    }

    public void setNameSpanisch(String nameSpanisch) {
        this.nameSpanisch = nameSpanisch;
    }

    public double getMinAge() {
        return minAge;
    }

    public void setMinAge(double minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return "GameLanguagesAndAge{" +
                "GameLanguagesAndAgeId=" + gameLanguagesAndAgeId +
                ", GameId=" + gameId +
                ", NameDeutsch='" + nameDeutsch + '\'' +
                ", NameEnglish='" + nameEnglish + '\'' +
                ", NameSpanisch='" + nameSpanisch + '\'' +
                ", MinAge=" + minAge +
                '}';
    }

    public void createTable (){
        String sql = "CREATE TABLE IF NOT EXISTS GameLanguagesAndAge (\n"
                + "GameLanguagesAndAgeId INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "GameId INTEGER,\n"
                + "NameDeutsch VARCHAR(255),\n"
                + "NameEnglish VARCHAR(255),\n"
                + "NameSpanisch VARCHAR(255),\n"
                + "MinAGE DECIMAL,\n"
                + "FOREIGN KEY (GameId) REFERENCES game (GameId)"
                + ");";
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement()) {
            // create a new table
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            stmt.execute(sql);
        }    catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save() {
        String insertSQL = "INSERT INTO GameLanguagesAndAge (GameId, NameDeutsch, NameEnglish, NameSpanisch, MinAGE)";
        insertSQL += " VALUES(?,?,?,?,?)";
        String pragmaForeinKey = "PRAGMA foreign_keys=on; ";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt =  conn.prepareStatement(insertSQL)) {
            conn.setAutoCommit(false);
            PreparedStatement activiateForeignKey = conn.prepareStatement(pragmaForeinKey);
            activiateForeignKey.executeUpdate();
                stmt.setInt(1,gameId);
                stmt.setString(2,nameDeutsch);
                stmt.setString(3,nameEnglish);
                stmt.setString(4,nameSpanisch);
                stmt.setDouble(5,(minAge));
                stmt.executeUpdate();
            String sqlText ="SELECT last_insert_rowid() as rowid";
            PreparedStatement stmtAutoincrement = conn.prepareStatement(sqlText);
            ResultSet rs = stmtAutoincrement.executeQuery();
            rs.next();
            int autoincrementValue = rs.getInt("rowid");
           setGameLanguagesAndAgeId(autoincrementValue);
            System.out.println(autoincrementValue);
            stmtAutoincrement.close();
          conn.commit(); //zum hinzufügen
            //conn.rollback ->rückgängig machen zurück hollen eines datensatzes bevor er hinzugefügt wird
          conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void printMetaDataGameLanguageAndAge(){
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GameLanguagesAndAge")){
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

    public void printInfo() {
        String selectSQL = "SELECT * FROM GameLanguagesAndAge";

        try {
            Connection conn = DriverManager.getConnection(connectionString);
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            String nameTranslated="";
            rs.next();
            nameTranslated = rs.getString("NameDeutsch");
            if (rs.wasNull()){
                System.out.println("Die deutsche Bezeichnung war leer");
            } else {
                System.out.println("Deutsch: " + nameTranslated);
            }
            nameTranslated = rs.getString("NameEnglish");
            if (rs.wasNull()){
                System.out.println("Die englische Bezeichnung war leer");
            } else {
                System.out.println("English: " + nameTranslated);
            }
            nameTranslated = rs.getString("NameSpanisch");
            if (rs.wasNull()){
                System.out.println("Die spanisch Bezeichnung war leer");
            } else {
                System.out.println("Spanisch: " + nameTranslated);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
