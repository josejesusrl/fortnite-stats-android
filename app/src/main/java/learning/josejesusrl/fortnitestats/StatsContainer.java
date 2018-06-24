package learning.josejesusrl.fortnitestats;

public class StatsContainer {
    private Stats br;
    int plataform;
    int gameMode;

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
