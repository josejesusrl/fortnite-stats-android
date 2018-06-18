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
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NThread {

    // Variable con la informacion del usuario
    private String user;
    private String plataform;
    private Context context;
    private String apiKey;
    private String gameMode;

    // Endpoints
    private String EP_stats;

    // Constructor
    public NThread(Context context, String user, String plataform, String gameMode) {
        // Contexto de la app para enviar Toast
        this.context = context;

        this.user = user;
        this.plataform = plataform;
        this.gameMode = gameMode;

        // Seteamos el api Key
        apiKey = "fyHRmMRjJg6FTgkbfASi";

        // Seteamos los EndPoints
        EP_stats = "https://fortnite.y3n.co/v2/player/";
    }

    public String getPlataform() {
        return plataform;
    }

    public String getUser() {
        return user;
    }

    public String getGameMode() {
        return gameMode;
    }

    // Funcion para leer el archivo Json
    private Stats readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readStats(reader);
        } finally {
            reader.close();
        }
    }

    // Creamos varias stats para difreentes usuarios
    private Stats readStats(JsonReader reader) throws IOException {
        // Creamos el objeto stats donde se guardara toda la info
        Stats stats = null;
        //reader.beginArray();
        while (reader.hasNext()) {
            stats = readStatsUser(reader);
        }
        reader.endObject();
        return stats;
    }

    private Stats readStatsUser(JsonReader reader) throws IOException {
        String id = "-1";
        String lastUpdate;
        String userID;
        String diplayName;
        String displayNameLowerCase;
        GameStats gameStats = null;

        // Copiamos el reader para extraer otra informacion
        JsonReader cReader = reader;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("_id")) {
                id = reader.nextString();
                System.out.println(id);
            } else if (name.equals("br")) {
                gameStats = readGameStats(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        // Sacamos los datos despues de br


        return new Stats(id, gameStats);
    }

    // Funcion para leer las game stats
    private GameStats readGameStats(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equalsIgnoreCase("stats")) {
                reader.beginObject(); // Etramos a Stats
                if (reader.nextName().equalsIgnoreCase(getPlataform())) {
                    reader.beginObject();// Entramos a la paltafomra
                    while (reader.hasNext()) {
                        if (reader.nextName().equalsIgnoreCase(getGameMode())) {
                            return readPlayerStats(reader);
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

    // Leemos las stats del jugador segun el game mode
    private GameStats readPlayerStats(JsonReader reader) throws IOException {
        int kills = 0, matchesPlayed = 0;
        String lastMatch = null;
        int minutesPlayed = 0, wins = 0, top10 = 0, top25 = 0, deaths = 0;
        double kdp = 0, kpm = 0, tpm = 0, score = 0, winRate = 0;

        reader.beginObject(); // Entramos a la paltaforma
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equalsIgnoreCase("kills")) {
                kills = reader.nextInt();
            } else if (name.equalsIgnoreCase("matchesPlayed")) {
                matchesPlayed = reader.nextInt();
            } else if (name.equalsIgnoreCase("lastMatch")) {
                lastMatch = reader.nextString();
            } else if (name.equalsIgnoreCase("minutesPlayed")) {
                minutesPlayed = reader.nextInt();
            } else if (name.equalsIgnoreCase("wins")) {
                wins = reader.nextInt();
            } else if (name.equalsIgnoreCase("top10")) {
                top10 = reader.nextInt();
            } else if (name.equalsIgnoreCase("top25")) {
                top25 = reader.nextInt();
            } else if (name.equalsIgnoreCase("deaths")) {
                deaths = reader.nextInt();
            } else if (name.equalsIgnoreCase("kdp")) {
                kdp = reader.nextDouble();
            } else if (name.equalsIgnoreCase("kpm")) {
                kpm = reader.nextDouble();
            } else if (name.equalsIgnoreCase("tpm")) {
                tpm = reader.nextDouble();
            } else if (name.equalsIgnoreCase("score")) {
                score = reader.nextDouble();
            } else if (name.equalsIgnoreCase("winRate")) {
                winRate = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new GameStats(kills, matchesPlayed, lastMatch, minutesPlayed, wins, top10, top25, deaths, kdp, kpm, tpm, score, winRate);
    }

    // Funcion para obtener stats del usuario
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

                        // Enviamos a procesar los datos del Json
                        estadisticas = readJsonStream(responseBody);

                        // Cerramos la conexion
                        statsConnection.disconnect();

                        System.out.println("Kills: " + estadisticas.getGameStats().getKills());


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