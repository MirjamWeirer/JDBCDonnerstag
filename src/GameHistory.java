import java.util.Date;

public class GameHistory {
    private int gameHistory, gameId;
    private Date gamePlayedUTC;

    public GameHistory() {
    }

    public GameHistory(int gameHistory, int gameId, Date gamePlayedUTC) {
        this.gameHistory = gameHistory;
        this.gameId = gameId;
        this.gamePlayedUTC = gamePlayedUTC;
    }

    public int getGameHistory() {
        return gameHistory;
    }

    public void setGameHistory(int gameHistory) {
        this.gameHistory = gameHistory;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getGamePlayedUTC() {
        return gamePlayedUTC;
    }

    public void setGamePlayedUTC(Date gamePlayedUTC) {
        this.gamePlayedUTC = gamePlayedUTC;
    }

    @Override
    public String toString() {
        return "GameHistory{" +
                "gameHistory=" + gameHistory +
                ", gameId=" + gameId +
                ", gamePlayedUTC=" + gamePlayedUTC +
                '}';
    }
}
