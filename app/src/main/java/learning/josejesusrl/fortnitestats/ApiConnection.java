package learning.josejesusrl.fortnitestats;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiConnection {

    // Variable con la informacion del usuario
    private String user, apiKey;
    private int plataform, gameMode;
    private Stats stats;

    // Endpoints
    private String EP_stats;

    // Constantes

    // Modos de Juego
    public static final int SOLO = 10;
    public static final int DUO = 11;
    public static final int SQUAD = 12;
    public static final int ALL = 13;

    // Plataformas
    public static final int PC = 20;
    public static final int PS4 = 21;
    public static final int XBOX = 22;

    // Fin Constantes


    public ApiConnection(String user, int plataform, int gameMode) {
        setUser(user);
        setPlataform(plataform);
        setGameMode(gameMode);
        // Agregamos el API Key
        apiKey = "fyHRmMRjJg6FTgkbfASi";
        // Agregamos los EndPoints
        EP_stats = "https://fortnite.y3n.co/v2/player/";
    }


    // Getters
    public Stats getPlayerStats(){
        return stats;
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



    // Setters

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

    // Metodos principales

    public void setUser(String user){
        this.user = user.replaceAll(" ", "%20");
    }
    public String getUser() {
        return user.replaceAll("%20"," ");
    }

    // Creamos un reader para serealizar el JSON
    private Stats parseJson(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in, "UTF-8");
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StatsContainer statsContainer = gson.fromJson(reader, StatsContainer.class);
            Log.d("ApiConn", "stats: "+statsContainer.toString());
            return statsContainer.getStats();
        } finally {
            reader.close();
        }
    }

    // Funcion principal para obtener las estadisticas del jugador
    public void downloadStats() {

        Stats estadisticas;
        URL fortniteStatsEndPoint = null;


        try {
            fortniteStatsEndPoint = new URL(EP_stats + getUser());


            // Creamos la conexion al EndPoint
            HttpsURLConnection statsConnection =
                    (HttpsURLConnection) fortniteStatsEndPoint.openConnection();
            // Agregamos headers para la conexion
            statsConnection.setRequestProperty("User-Agent", " Java Request");
            // Agregamos la Key del api de Fortnite Stats
            statsConnection.setRequestProperty("X-Key", apiKey);

            if (statsConnection.getResponseCode() == 200) {
                // Recibimos los datros
                InputStream responseBody = statsConnection.getInputStream();

                // Enviamos a procesar los datos del Json y guaramos el objeto para ser consultado por otras clases
                stats = parseJson(responseBody);

                // Cerramos la conexion
                statsConnection.disconnect();


            } else if (statsConnection.getResponseCode() == 503) {
                Log.w("ApiConn", "Error 503 el servidor");
            } else if (statsConnection.getResponseCode() == 429) {
                Log.w("ApiConn", "Error 429 el servidor");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}