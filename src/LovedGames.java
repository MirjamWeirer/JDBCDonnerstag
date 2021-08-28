public class LovedGames {
    private int LovedGamesId, PlayerId, GameId, Rank;

    public LovedGames() {
    }

    public LovedGames(int lovedGamesId, int playerId, int gameId, int rank) {
        LovedGamesId = lovedGamesId;
        PlayerId = playerId;
        GameId = gameId;
        Rank = rank;
    }

    public int getLovedGamesId() {
        return LovedGamesId;
    }

    public void setLovedGamesId(int lovedGamesId) {
        LovedGamesId = lovedGamesId;
    }

    public int getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(int playerId) {
        PlayerId = playerId;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    @Override
    public String toString() {
        return "LovedGames{" +
                "LovedGamesId=" + LovedGamesId +
                ", PlayerId=" + PlayerId +
                ", GameId=" + GameId +
                ", Rank=" + Rank +
                '}';
    }
}
