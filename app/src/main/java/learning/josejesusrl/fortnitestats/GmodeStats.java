package learning.josejesusrl.fortnitestats;

public class GmodeStats {

    private GameStats solo;
    private GameStats duo;
    private GameStats squad;
    private GameStats all;

    public GameStats getSolo() {
        return solo;
    }

    public void setSolo(GameStats solo) {
        this.solo = solo;
    }

    public GameStats getDuo() {
        return duo;
    }

    public void setDuo(GameStats duo) {
        this.duo = duo;
    }

    public GameStats getSquad() {
        return squad;
    }

    public void setSquad(GameStats squad) {
        this.squad = squad;
    }

    public GameStats getAll() {
        return all;
    }

    public void setAll(GameStats all) {
        this.all = all;
    }

}
