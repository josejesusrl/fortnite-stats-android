package learning.josejesusrl.fortnitestats;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NThread {

    // Variable con la informacion del usuario
    private String user;
    private int plataform;
    private Context context;
    private String apiKey;
    private int gameMode;
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

    // Constructor
    public NThread(Context context, String user, int plataform, int gameMode) {
        // Contexto de la app para enviar Toast
        this.context = context;

        setUser(user);
        setPlataform(plataform);
        setGameMode(gameMode);

        // Agregamos el API Key
        apiKey = "fyHRmMRjJg6FTgkbfASi";

        // Agregamos los EndPoints
        EP_stats = "https://fortnite.y3n.co/v2/player/";
    }





    // Getters
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

    public String getUser() {
        return user.replaceAll("%20"," ");
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
    public Stats getPlayerStats(){
        return stats;
    }


    // Setters

    public void setStats(Stats stats){
        this.stats = stats;
    }

    public void setUser(String user){
        this.user = user.replaceAll(" ", "%20");
    }
    // Setteamos la plataforma si no concide con ninguno pone -1
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
    // Setteamos el modo de juego si no conide con ninguno pone -1
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





    // Creamos un reader para serealizar el JSON
    private Stats readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readStats(reader);
        } finally {
            reader.close();
        }
    }

    // Funcion Iniciar y Finalizar el Serealizado de JSON
    private Stats readStats(JsonReader reader) throws IOException {
        // En el objeto Stats se guardara toda la informacion del jugador
        Stats stats = null;
        while (reader.hasNext()) {
            stats = readStatsUser(reader);
        }
        reader.endObject();
        return stats;
    }

    // Iniciamos el serealizado del Json extrayendo los primeros datos
    private Stats readStatsUser(JsonReader reader) throws IOException {
        String id = "-1";
        String lastUpdate;
        String userID;
        String diplayName;
        String displayNameLowerCase;
        GameStats gameStats = null;

        // TODO crear una copia del reader para extraer datos posteriores a "br" en el json
        JsonReader cReader = reader;

        reader.beginObject(); // Ingresamos a la estrucutra inicial del JSON
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("_id")) {
                id = reader.nextString();
            } else if (name.equals("br")) { // Estrucutura donde se encutra las stats del jugador
                gameStats = readGameStats(reader);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        return new Stats(id, gameStats);
    }

    // Ingresamos a la estrucura donde estan los datos y vemos si no contiene nada
    private GameStats readGameStats(JsonReader reader) throws IOException {
        reader.beginObject(); // Entramos a la estrucutra br
        while (reader.hasNext()) {
            if (reader.nextName().equalsIgnoreCase("stats")) {
                reader.beginObject(); // Entramos a Stats
                if (reader.nextName().equalsIgnoreCase(getPlataform()) && reader.hasNext()) {
                    reader.beginObject();// Entramos a la estructura de la paltafomra a buscar

                    // Buscamos la estructura  del modo de juego seleccionado
                    while (reader.hasNext()) {

                        if (reader.nextName().equalsIgnoreCase(getGameMode())) {
                            return readPlayerStats(reader); // Enviamos la estructura a su serializacion
                        } else {
                            reader.skipValue();
                        }
                    }

                } else {
                    return null;
                }
            } else {
                reader.skipValue();
            }
        }
        return null;
    }

    // Buscamos y asginamos las estadisticas del usuario al objeto GameStats para retornarlo
    private GameStats readPlayerStats(JsonReader reader) throws IOException {
        int kills = 0, matchesPlayed = 0;
        String lastMatch = null;
        int minutesPlayed = 0, wins = 0, top10 = 0, top25 = 0, deaths = 0;
        double kpd = 0, kpm = 0, tpm = 0, score = 0, winRate = 0;

        reader.beginObject(); // Entramos a la estrucutura de la plataforma seleccionada
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equalsIgnoreCase("kills")) {
                kills = reader.nextInt();
            } else if (name.equalsIgnoreCase("matchesPlayed") && reader.peek() != JsonToken.NULL) {
                matchesPlayed = reader.nextInt();
            } else if (name.equalsIgnoreCase("lastMatch") && reader.peek() != JsonToken.NULL) {
                lastMatch = reader.nextString();
            } else if (name.equalsIgnoreCase("minutesPlayed") && reader.peek() != JsonToken.NULL) {
                minutesPlayed = reader.nextInt();
            } else if (name.equalsIgnoreCase("wins") && reader.peek() != JsonToken.NULL) {
                wins = reader.nextInt();
            } else if (name.equalsIgnoreCase("top10") && reader.peek() != JsonToken.NULL) {
                top10 = reader.nextInt();
            } else if (name.equalsIgnoreCase("top25") && reader.peek() != JsonToken.NULL) {
                top25 = reader.nextInt();
            } else if (name.equalsIgnoreCase("deaths") && reader.peek() != JsonToken.NULL) {
                deaths = reader.nextInt();
            } else if (name.equalsIgnoreCase("kpd") && reader.peek() != JsonToken.NULL) {
                kpd = reader.nextDouble();
            } else if (name.equalsIgnoreCase("kpm") && reader.peek() != JsonToken.NULL) {
                kpm = reader.nextDouble();
            } else if (name.equalsIgnoreCase("tpm") && reader.peek() != JsonToken.NULL) {
                tpm = reader.nextDouble();
            } else if (name.equalsIgnoreCase("score") && reader.peek() != JsonToken.NULL) {
                score = reader.nextDouble();
            } else if (name.equalsIgnoreCase("winRate") && reader.peek() != JsonToken.NULL) {
                winRate = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new GameStats(kills, matchesPlayed, lastMatch, minutesPlayed, wins, top10, top25, deaths, kpd, kpm, tpm, score, winRate);
    }

    // Funcion principal para obtener las estadisticas del jugador
    public void getStats() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Stats estadisticas;
                // Creaos la URL con el EndPoint de Fortnite stats
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

                    // Recibiendo respuestas

                    if (statsConnection.getResponseCode() == 200) {
                        // Recibimos los datros
                        InputStream responseBody = statsConnection.getInputStream();

                        // Enviamos a procesar los datos del Json y guaramos el objeto para ser consultado por otras clases
                        setStats(readJsonStream(responseBody));

                        // Cerramos la conexion
                        statsConnection.disconnect();


                    } else if (statsConnection.getResponseCode() == 503) {
                        System.out.println("Error 503");
                    } else if (statsConnection.getResponseCode() == 429) {
                        System.out.println("Error 429");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}