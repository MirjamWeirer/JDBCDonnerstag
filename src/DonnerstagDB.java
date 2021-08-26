public class DonnerstagDB {
    public static void main(String[] args) {
        String fileName = "Donnerstag.db";
        DBHelper db = new DBHelper();
        String genre = "Action";
        int maxLevel = 60;
        String ordercolumn = "MaxLevel";
        /*
        db.createNewDatabase(fileName);
        db.connect(fileName);
        db.createGameTable(fileName);

        System.out.println("Finished - Table was created");
        */
        //db.readAllGames(fileName);
        db.readGamesFilteredByGenre(genre);
        db.readGamesFlteredByMaxLevel(maxLevel);
        db.readGamesOrderBy(ordercolumn);
    }
}
