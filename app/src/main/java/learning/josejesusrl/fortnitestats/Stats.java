package learning.josejesusrl.fortnitestats;

import java.util.Date;

public class Stats {


    // Todos los datos que contienen las stats del juego

    private PlayerStats stats;
    private String lastUpdate;
    private String userID;
    private String diplayName;
    private String displayNameLowerCase;
    private Date lastLookup;
    private Date lasLookupUpdate;
    private String discordID;

    // Setters

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDiplayName(String diplayName) {
        this.diplayName = diplayName;
    }

    public void setDisplayNameLowerCase(String displayNameLowerCase) {
        this.displayNameLowerCase = displayNameLowerCase;
    }

    public void setLastLookup(Date lastLookup) {
        this.lastLookup = lastLookup;
    }

    public void setLasLookupUpdate(Date lasLookupUpdate) {
        this.lasLookupUpdate = lasLookupUpdate;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }



    public Stats(String id, PlayerStats stats) {
        this.stats = stats;
    }

    // Getters


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

    public PlayerStats getStats() {
        return stats;
    }
}
