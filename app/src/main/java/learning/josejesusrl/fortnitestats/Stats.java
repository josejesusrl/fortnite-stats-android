package learning.josejesusrl.fortnitestats;

import java.util.Date;

public class Stats {

    // Clase donde se almacena la estructura principal despues de br

    private String lastUpdate;
    private String userID;
    private String diplayName;
    private String displayNameLowerCase;
    private Date lastLookup;
    private Date lasLookupUpdate;
    private String discordID;

    private PlataformStats stats;

    public PlataformStats getStats() {
        return stats;
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

    public Date getLastLookup() {
        return lastLookup;
    }

    public Date getLasLookupUpdate() {
        return lasLookupUpdate;
    }

    public String getDiscordID() {
        return discordID;
    }

}
