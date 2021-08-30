public class GameLanguagesAndAge {
    private int GameLanguagesAndAgeId, GameId;
    private String NameDeutsch, NameEnglish, NameSpanisch;
    private double MinAge;

    public GameLanguagesAndAge() {
    }

    public GameLanguagesAndAge(int gameLanguagesAndAgeId, int gameId, String nameDeutsch, String nameEnglish, String nameSpanisch, double minAge) {
        GameLanguagesAndAgeId = gameLanguagesAndAgeId;
        GameId = gameId;
        NameDeutsch = nameDeutsch;
        NameEnglish = nameEnglish;
        NameSpanisch = nameSpanisch;
        MinAge = minAge;
    }

    public int getGameLanguagesAndAgeId() {
        return GameLanguagesAndAgeId;
    }

    public void setGameLanguagesAndAgeId(int gameLanguagesAndAgeId) {
        GameLanguagesAndAgeId = gameLanguagesAndAgeId;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public String getNameDeutsch() {
        return NameDeutsch;
    }

    public void setNameDeutsch(String nameDeutsch) {
        NameDeutsch = nameDeutsch;
    }

    public String getNameEnglish() {
        return NameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        NameEnglish = nameEnglish;
    }

    public String getNameSpanisch() {
        return NameSpanisch;
    }

    public void setNameSpanisch(String nameSpanisch) {
        NameSpanisch = nameSpanisch;
    }

    public double getMinAge() {
        return MinAge;
    }

    public void setMinAge(double minAge) {
        MinAge = minAge;
    }

    @Override
    public String toString() {
        return "GameLanguagesAndAge{" +
                "GameLanguagesAndAgeId=" + GameLanguagesAndAgeId +
                ", GameId=" + GameId +
                ", NameDeutsch='" + NameDeutsch + '\'' +
                ", NameEnglish='" + NameEnglish + '\'' +
                ", NameSpanisch='" + NameSpanisch + '\'' +
                ", MinAge=" + MinAge +
                '}';
    }
}
