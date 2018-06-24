package learning.josejesusrl.fortnitestats;

public class GmodeStats {

    private GameStats solo;
    private GameStats duo;
    private GameStats squad;
    private GameStats all;

    public GameStats getSoloStats() {
        return solo;
    }

    public GameStats getDuoStats() {
        return duo;
    }

    public GameStats getSquadStats() {
        return squad;
    }

    public GameStats getAllStats() {
        return all;
    }

}
