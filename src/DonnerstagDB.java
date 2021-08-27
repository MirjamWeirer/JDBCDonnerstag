public class DonnerstagDB {
    public static void main(String[] args) {
        String fileName = "Donnerstag.db";
        DBHelper db = new DBHelper();
        String genre = "Simulation";
        int maxLevel = 30;
        String ordercolumn = "MaxLevel";
        String order = "Nickname"; //order Column Player
        /*
        db.createNewDatabase(fileName);
        db.connect(fileName);
        db.createGameTable(fileName);

        System.out.println("Finished - Table was created");
        */
//        db.readAllGames(fileName);
//        db.readGamesFilteredByGenre(genre);
//        db.readGamesFlteredByMaxLevel(maxLevel);
//        db.readGamesOrderBy(ordercolumn);
//        db.createPlayerTable(fileName);
//        System.out.println("Finished - Table was created");
//        System.out.println("Read all");
//        db.readAllPlayers(fileName);
//        System.out.println("Read all order by Nickname");
//        db.readAllPlayersOrderdBy(order, fileName);
        //db.readGamesFilteredByGenrePrepare(genre);

//        db.insertGamePrepare("Sudoko","Puzzle",80);
//        db.readAllGames(fileName);

       // db.displayGameWithMaxLevelBetweenAandB(60,1000);
        DBHelperTyped dbHelperTyped = new DBHelperTyped();
//        Game g = new Game();
//        g.setGameName("Monopoly");
//        g.setGameGenre("board games");
//        g.setMaxLevel(20);
//
//        dbHelperTyped.addGame(g);
//
//        db.readAllGames(fileName);
//       Game game = dbHelperTyped.getGameById(3);
//        System.out.println(game);
//
//        System.out.println(dbHelperTyped.getAllGamesByGenre("Simulation"));
//
//        Game g = dbHelperTyped.getGameById(7);
//        g.setMaxLevel(90);
//       int shouldBeOne =  dbHelperTyped.updateGame(g);
//       if (shouldBeOne == 0){
//           System.out.println("Game was not found");
//       }else if (shouldBeOne > 1){
//           System.out.println("More than one Game have the same ID");
//       }else if (shouldBeOne == 1){
//           System.out.println("One Game was updatet");
//       }
//
//        int rowsAfferd = dbHelperTyped.incrementMaxLevelByXforGenre(50,"Action");
//        System.out.println(rowsAfferd);

//        Player p = new Player();
//        p.setFirstname("Anna");
//        p.setLastname("Robosch");
//        p.setNickname("Queer");
//        dbHelperTyped.addPlayer(p);

//        Player player = dbHelperTyped.getPlayerByID(2);
//        System.out.println(player);
        Player p = dbHelperTyped.getPlayerByID(5);
        p.setNickname("Fight Girl");
        dbHelperTyped.updatePlayer(p);
    }
}
