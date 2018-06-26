package learning.josejesusrl.fortnitestats;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ApiConnection {

    // Variable con la informacion del usuario
    private String user, apiKey;
    private int plataform, gameMode;
    private StatsContainer statsContainer;

    // Lista donde se guardaran los errores en tiempo de ejecuion
    private List<String> errors;

    // Endpoints
    private String EP_stats;


    public ApiConnection(String user, int plataform, int gameMode) {
        setUser(user);
        // Iniciamos nuestra lista de errores
        errors = new ArrayList<String>();
        // Agregamos el API Key
        apiKey = "fyHRmMRjJg6FTgkbfASi";
        // Agregamos los EndPoints
        EP_stats = "https://fortnite.y3n.co/v2/player/";
    }

    // Getters and Setters

    public List<String> getErrors(){
        return errors;
    }
    public StatsContainer getStatsContainer() {
        return statsContainer;
    }

    private void setUser(String user){
        this.user = user.replaceAll(" ", "%20");
    }


    /**
     *
     * Metodos principales para la establecer y parcear la conexion con la api
     *
     */
    // Serealizamos el JSON por la libreria GSON
    private StatsContainer parseJson(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in, "UTF-8");
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StatsContainer statsContainer = gson.fromJson(reader, StatsContainer.class);
            Log.d("ApiConn", "stats: " + statsContainer.toString());
            return statsContainer;
        } finally {
            reader.close();
        }
    }

    // Funcion principal para obtener las estadisticas del jugador
    public void downloadStats() {
        URL fortniteStatsEndPoint;

        try {
            fortniteStatsEndPoint = new URL(EP_stats + user);
            // Creamos la conexion al EndPoint
            HttpsURLConnection apiConn =
                    (HttpsURLConnection) fortniteStatsEndPoint.openConnection();

            // Agregamos headers para la conexion
            apiConn.setRequestProperty("User-Agent", " Java Request");
            apiConn.setRequestProperty("X-Key", apiKey);

            if (apiConn.getResponseCode() == 200) {
                InputStream responseBody = apiConn.getInputStream();

                // Enviamos a procesar los datos del Json y guaramos el objeto para ser consultado por otras clases
                statsContainer = parseJson(responseBody);

                apiConn.disconnect();

            } else if (apiConn.getResponseCode() == 503) {
                Log.w("ApiConn", "Error 503 el servidor");
                errors.add("Error 503: Demasiadas peticiones al mismo tiempo ");
            } else if (apiConn.getResponseCode() == 429) {
                Log.w("ApiConn", "Error 429 el servidor");
                errors.add("Error 429: Volumen de consultas excedidas");
            }else if(apiConn.getResponseCode() == 429){
                Log.w("ApiConn", "Error 403 Api Key Error");
                errors.add("Error 403: Acceso denegado");
            }else if(apiConn.getResponseCode() == 404){
                Log.w("ApiConn", "Error 404: Usuario no encontrado");
                errors.add("Usuario no encontrado, ingresa una cuenta de Epic Games valida");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}