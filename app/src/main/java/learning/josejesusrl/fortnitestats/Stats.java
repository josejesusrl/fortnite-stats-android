package learning.josejesusrl.fortnitestats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stats {

    // Clase donde se almacena la estructura principal despues de br

    private String lastUpdate;
    private String userID;
    private String diplayName;
    private String displayNameLowerCase;
    private Date lastLookup;
    private Date lastLookupUpdate;
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

    public String getLastLookup() {
        try {
            String dateSubString = lastLookup.toString().substring(0, 10);
            SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

            Date date = parser.parse(dateSubString);
            return formater.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLasLookupUpdate() {
        try {
            String dateSubString = lastLookupUpdate.toString().substring(0, 10);
            SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

            Date date = parser.parse(dateSubString);
            return formater.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDiscordID() {
        return discordID;
    }

}
