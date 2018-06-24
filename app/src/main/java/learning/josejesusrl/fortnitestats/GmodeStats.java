package learning.josejesusrl.fortnitestats;

public class GmodeStats {

    private GameStats solo = null;
    private GameStats duo = null;
    private GameStats squad = null;
    private GameStats all = null;

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
