package learning.josejesusrl.fortnitestats;

import java.util.Date;

public class Stats {


    // Todos los datos que contienen las stats del juego

    private String id;
    private GameStats stats;
    private String lastUpdate;
    private String userID;
    private String diplayName;
    private String displayNameLowerCase;
    private Date lastLookup;
    private Date lasLookupUpdate;
    private String discordID;




    public Stats(String id, GameStats stats) {
        this.id = id;
        /**
        this.lastUpdate = lastUpdate;
        this.userID = userID;
        this.diplayName = diplayName;
        this.displayNameLowerCase = displayNameLowerCase;

         **/
        this.stats = stats;
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

    public GameStats getStats() {
        return stats;
    }
}
