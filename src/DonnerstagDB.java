public class DonnerstagDB {
    public static void main(String[] args) {
        String fileName = "Donnerstag.db";
        DBHelper db = new DBHelper();
        String genre = "Simulation";
        //int maxLevel = 30;
        String ordercolumn = "MaxLevel";
        String order = "Nickname"; //order Column Player
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        Player p = new Player();
        Game g = new Game();
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
//        p.setFirstname("Martin");
//        p.setLastname("Amschl");
//        p.setNickname("Dr.");


//        Player playerinsert = dbHelperTyped.addPlayer(p);

//        Player player = dbHelperTyped.getPlayerByID(2);
//        System.out.println(player);
//        Player p = dbHelperTyped.getPlayerByID(5);
//        p.setNickname("Fight Girl");
//        dbHelperTyped.updatePlayer(p);

//        Player player = new Player(0,"Fabian","Weissitsch","Fabs");
//        dbHelperTyped.addPlayer(player);
//        db.createTableLovedGames(url);
//        System.out.println("Created Table");

//        LovedGames lovedGames = new LovedGames(0,1,3,700);
//        dbHelperTyped.addLovedGame(lovedGames);
//        db.createTableGameHistory(url);
//        System.out.println("Created Table");
//        LovedGames l = dbHelperTyped.getLovedGames(2);
//        l.setRank(40);
//        dbHelperTyped.updateLovedGames(l);

//        p.setPlayerId(1);
//        p.fillLovedGames();
//        p.fillLovedGamesOOP();
//        System.out.println(p.getMyLovedGamesOOP());

//        p.setPlayerId(4);
////        g.setGameId(8);
////        p.addLovedGame(g);

        //p = dbHelperTyped.getPlayerWithMostLovedGames();
//        System.out.println(p);

//       int rank = dbHelperTyped.joinLovedGame(g,p,0);
//        System.out.println(rank);

        //dbHelperTyped.printMetaData();

        db.createTableGameLanguagesAndAge(url);
    }
}
