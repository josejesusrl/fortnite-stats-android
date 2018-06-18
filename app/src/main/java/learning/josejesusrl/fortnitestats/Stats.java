package learning.josejesusrl.fortnitestats;

public class Stats {


    // Todos los datos que contienen las stats del juego

    private String id;
    private String lastUpdate;
    private String userID;
    private String diplayName;
    private String displayNameLowerCase;
    private GameStats gameStats;




    public Stats(String id, GameStats gameStats) {
        this.id = id;
        /**
        this.lastUpdate = lastUpdate;
        this.userID = userID;
        this.diplayName = diplayName;
        this.displayNameLowerCase = displayNameLowerCase;

         **/
        this.gameStats = gameStats;
    }

    // Getters

    public String getId() {
        return id;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getUserID() {
        return userID;
    }

    public String getDiplayName() {
        return diplayName;
    }

    public String getDisplayNameLowerCase() {
        return displayNameLowerCase;
    }

    public GameStats getGameStats() {
        return gameStats;
    }
}
