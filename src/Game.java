public class Game {
    private int GameId;
    private String GameName, GameGenre;
    private int MaxLevel;

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getGameGenre() {
        return GameGenre;
    }

    public void setGameGenre(String gameGenre) {
        GameGenre = gameGenre;
    }

    public int getMaxLevel() {
        return MaxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        MaxLevel = maxLevel;
    }
}
