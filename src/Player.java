public class Player {
    private int PlayerId;
    private String Firstname, Lastname, Nickname;

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
}
