import java.sql.*;

public class KundenUndTelefonnumern {
    private String connectionString ="jdbc:sqlite:C:/sqlite/db/dienstag.db";

    public void createTable(){
        String createTableKundenSql = "CREATE TABLE IF NOT EXISTS Kunden(\n"
                + "KDNR INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "Vorname VARCHAR(20) );";
        String createTableKundenTelefonnummerSql = "CREATE TABLE IF NOT EXISTS KundenTelfonnumer(\n"
                + "	KDId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	KDNR INTEGER,\n"
                + " Telefonnummer varchar(20), \n"
                + " FOREIGN KEY(KDNR ) REFERENCES Kunden(KDNR))";



        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmtCreateKunde = conn.createStatement();
             Statement stmtCreateTelefon = conn.createStatement()) {

            stmtCreateKunde.execute(createTableKundenSql);
            stmtCreateTelefon.execute(createTableKundenTelefonnummerSql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertValues(){
        String insKunde="INSERT INTO Kunden(Vorname) VALUES(?)";
        String insTelefon="INSERT INTO KundenTelfonnumer(KDNR, Telefonnummer)\n" +
                "VALUES(?,?)";
        String pragmaForeignKey="PRAGMA foreign_keys=on;";

        String joinKundeUndTelefon="SELECT k.Vorname, t.Telefonnummer\n" +
                "FROM Kunden k JOIN KundenTelfonnumer t\n" +
                "ON k.KDNR = t.KDNR";


        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmtPragma = conn.createStatement();
             Statement stmtSelectMitJoin = conn.createStatement();
             PreparedStatement pStmtInsKunden = conn.prepareStatement(insKunde);
             PreparedStatement pStmtInsTelefon = conn.prepareStatement(insTelefon)) {

            stmtPragma.execute(pragmaForeignKey);

            String vorname = "Steff";
            pStmtInsKunden.setString(1,vorname);
            //pStmtInsKunden.executeUpdate();

            //Erster Parameter mit KDNR 1 befüllen
            pStmtInsTelefon.setInt(1,2);
            //Zweiter Parameter mit Telefonnummer
            pStmtInsTelefon.setString(2,"0316");
           // pStmtInsTelefon.executeUpdate();

            pStmtInsTelefon.setInt(1,2);
            pStmtInsTelefon.setString(2,"0676");
           // pStmtInsTelefon.executeUpdate();

            ResultSet rs = stmtSelectMitJoin.executeQuery(joinKundeUndTelefon);
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

            while (rs.next()){
                System.out.println("Vorname: " + rs.getString("Vorname") + ", Telefonnummer: " + rs.getString("Telefonnummer"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

