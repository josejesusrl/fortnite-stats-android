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
    

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDiplayName() {
        return diplayName;
    }

    public void setDiplayName(String diplayName) {
        this.diplayName = diplayName;
    }

    public String getDisplayNameLowerCase() {
        return displayNameLowerCase;
    }

    public void setDisplayNameLowerCase(String displayNameLowerCase) {
        this.displayNameLowerCase = displayNameLowerCase;
    }

    public Date getLastLookup() {
        return lastLookup;
    }

    public void setLastLookup(Date lastLookup) {
        this.lastLookup = lastLookup;
    }

    public Date getLasLookupUpdate() {
        return lasLookupUpdate;
    }

    public void setLasLookupUpdate(Date lasLookupUpdate) {
        this.lasLookupUpdate = lasLookupUpdate;
    }

    public String getDiscordID() {
        return discordID;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }




}
