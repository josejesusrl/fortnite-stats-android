package learning.josejesusrl.fortnitestats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsContainer {
    // Atributos para filtrar Stats
    int plataform;
    int gameMode;

    // Datos del Json
    private Stats br;
    private String lastUpdate;
    private String userID;
    private String displayName;
    private String displayNameLowerCase;
    private Date lastLookup;
    private Date lastLookupUpdate;
    private String discordID;

    // Modos de Juego
    public static final int SOLO = 10;
    public static final int DUO = 11;
    public static final int SQUAD = 12;
    public static final int ALL = 13;

    // Plataformas
    public static final int PC = 20;
    public static final int PS4 = 21;
    public static final int XBOX = 22;


    // Metodo para obtener el objeto Stats sin ningun filtro
    public Stats getFullStats(){
        return br;
    }

    /**
     *
     * Metodos Getters del JSON
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getUserID() {
        return userID;
    }

    public String getDisplayName() {
        return displayName;
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

    /**
     *
     * Metodos para filtrar las estadisticas segun la plataforma y modo de juego
     */

    // Obtener el el objeto de la plataforma que se necesita
    private GmodeStats getStatsFrom(){
        switch (this.plataform) {
            case PC:
                return br.getStats().fromPC();
            case PS4:
                return br.getStats().fromPS4();
            case XBOX:
                return br.getStats().fromXBOX();
        }
        return null;
    }
    // Obtener las stats de un mododo de juego y plataforma especifica
    private GameStats getStatsOf(){
        switch (this.gameMode) {
            case SOLO:
                return getStatsFrom().getSoloStats();
            case DUO:
                return getStatsFrom().getDuoStats();
            case SQUAD:
                return getStatsFrom().getSquadStats();
            case ALL:
                return getStatsFrom().getAllStats();
        }
        return null;
    }
    // Metodo publico para obtener la plataforma.
    public GameStats getStatsFromOn(int plataform, int gamemode){
        if (br != null) {
            setPlataform(plataform);
            setGameMode(gamemode);

            return getStatsOf();

        }else{
            return null;
        }
    }

    /**
     *
     *  Getters And Setters genericos para obtener atributos del objeto
     */

    public void setPlataform(int plataform){
        if (plataform == PC){
            this.plataform = PC;
        }else if (plataform == PS4){
            this.plataform = PS4;
        }else if(plataform == XBOX){
            this.plataform = XBOX;
        }else {
            this.plataform = -1;
        }
    }
    public void setGameMode(int gameMode){
        if (gameMode == SOLO){
            this.gameMode = SOLO;
        }else if(gameMode == DUO){
            this.gameMode = DUO;
        }else if(gameMode == SQUAD){
            this.gameMode = SQUAD;
        }else if(gameMode  == ALL){
            this.gameMode = ALL;
        }else{
            this.gameMode = -1;
        }
    }
    // Metodo para obtener el nombre de la plataforma para el usuario final
    public String getPlataformName() {
        if (plataform == PC){
            return "PC";
        }else if (plataform == PS4){
            return "PlayStation 4";
        }else if(plataform == XBOX){
            return "Xbox One";
        }
        return null;
    }
    // Metodo para obtener el codigo de la paltaforma para el JSON
    private String getPlataform() {
        if (plataform == PC){
            return "pc";
        }else if (plataform == PS4){
            return "ps4";
        }else if(plataform == XBOX){
            return "xbox";
        }
        return null;
    }
    public String getGameMode() {
        if(gameMode == SOLO){
            return "Solo";
        }
        else if(gameMode == DUO){
            return "Duo";
        }
        else if(gameMode == SQUAD){
            return "Squad";
        }
        else if (gameMode == ALL){
            return "Todos los modos";
        }
        return null;
    }
}
