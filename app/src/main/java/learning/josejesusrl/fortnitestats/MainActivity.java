package learning.josejesusrl.fortnitestats;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText etPlayer;
    private Spinner snPlataform, snGamemode;
    private ArrayList<String> arrayListView;
    private ArrayAdapter<String> listViewAdapter;

    protected ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        snGamemode = (Spinner) findViewById(R.id.snGameMode);
        snPlataform = (Spinner) findViewById(R.id.snPlataform);
        etPlayer = (EditText) findViewById(R.id.etNombre);

        progressDialog = new ProgressDialog(this);

        String[] gameModes = {"Solo", "Duo", "Squad", "Todos"};
        String[] plataforms = {"PC", "PlayStation 4", "Xbox One"};
        arrayListView = new ArrayList<String>();
        arrayListView.add("Ingresa los datos para ver tus estadísticas de juego");

        ArrayAdapter<String> snPlataformAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, plataforms);
        ArrayAdapter<String> snGamemodeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gameModes);
        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListView);

        snGamemode.setAdapter(snGamemodeAdapter);
        snPlataform.setAdapter(snPlataformAdapter);
        listView.setAdapter(listViewAdapter);

        // Cargamos las preferencias del usuario
        getUserPreference();
    }

    private void getUserPreference(){
        SharedPreferences preferences = getSharedPreferences("FortniteStats", Context.MODE_PRIVATE);
        etPlayer.setText(preferences.getString("playerName", ""));
        snGamemode.setSelection(preferences.getInt("gamemode", 0));
        snPlataform.setSelection(preferences.getInt("plataform", 0));
    }

    // Funcion para limpiar todos los campos
    public void clearFields(View v){

        // Limpiamos el ListView
        arrayListView.clear();
        listViewAdapter.notifyDataSetChanged();

        // Limpiamos el spinner snPlataform
        snPlataform.setSelection(0);

        // Limpiamos el spinner snGamemode
        snGamemode.setSelection(0);

        // Limpiamos el editText usuario
        etPlayer.setText("");
    }

    // Guardamos los datos del usuario
    public void saveUserPreference(View v){
        String playerName = etPlayer.getText().toString();
        int gamemode = snGamemode.getSelectedItemPosition();
        int plataform = snPlataform.getSelectedItemPosition();

        SharedPreferences preferences = getSharedPreferences("FortniteStats", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Verificamos si ya tiene guardadas las preferencias si es asi borrar el archivo para dejar de cargar los archivos guardados al inicio de la app
        if (preferences.contains("playerName")){
            editor.clear();
            editor.commit();
            Toast.makeText(this, "Los datos del jugador fueron borrados", Toast.LENGTH_SHORT).show();
        }else {
            editor.putString("playerName", playerName);
            editor.putInt("gamemode", gamemode);
            editor.putInt("plataform", plataform);
            editor.commit();

            Toast.makeText(this, "Los datos del jugador han sido guardados", Toast.LENGTH_SHORT).show();
        }
    }

    // Obtenemos las estadicas llamando al objeto Stats que se encarga de depurar las estadisticas
    public void getStats(View v){
        if (getPlataformFromSpinner() == -1){
            Toast.makeText(this, "Por favor selecciona una plataforma", Toast.LENGTH_SHORT);
            Log.i("MainAct.downloadStats()", "No se a seleccionado ninguna plataforma");
        }
        if (getGamemodeFromSpinner() == -1){
            Toast.makeText(this, "Por favor selecciona un modo de juego", Toast.LENGTH_SHORT);
            Log.i("MainAct.downloadStats()", "No se a seleccionado ningun modo de juego");
        }
        if (getPlataformFromSpinner() != -1 && getGamemodeFromSpinner() != -1){
            // Creamos el AsyncTask para mantener la gui en un thread diferente
            GetStatsOnAsyncTask getStatsOnAsyncTask = new GetStatsOnAsyncTask();
            getStatsOnAsyncTask.execute(etPlayer.getText().toString(), String.valueOf(getPlataformFromSpinner()), String.valueOf(getGamemodeFromSpinner()));
        }

    }

    // Metodo para colocar las estadisticas en la GUI obtenidas del AsyncTask
    private void putStats(ApiConnection nt){
        Stats stats = nt.getPlayerStats();
        GmodeStats gmodeStats = null;
        GameStats gameStats = null;
        if (stats != null){

            arrayListView.clear();

            // Ingresamos todos los datos de las estadisticas
            arrayListView.add("Usuario: "+nt.getUser());
            arrayListView.add("Modo de jugo: "+nt.getPlayerStats());
            arrayListView.add("K/D ratio: "+gameStats.getKpd());
            arrayListView.add("Victorias: "+gameStats.getWins());
            arrayListView.add("Ratio de Victorias: "+gameStats.getWinRate());
            arrayListView.add("Partidas jugadas: "+gameStats.getMatchesPlayed());
            arrayListView.add("Ultima partida jugada: "+gameStats.getLastMatch());

            // Regrescamos el ListView
            listViewAdapter.notifyDataSetChanged();

        }else{
            Log.w("MainActivity", "Los Stats son nullos");
            Toast.makeText(this,"No se encontró el nombre de jugador", Toast.LENGTH_SHORT).show();
        }
    }



    private int getGamemodeFromSpinner(){
        switch (snGamemode.getSelectedItemPosition()){
            case 0:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+ StatsContainer.SOLO);
                return StatsContainer.SOLO;
            case 1:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+ StatsContainer.DUO);
                return StatsContainer.DUO;
            case 2:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+ StatsContainer.SQUAD);
                return StatsContainer.SQUAD;
            case 3:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+ StatsContainer.ALL);
                return StatsContainer.ALL;
            default:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+-1);
                return -1;
        }
    }
    private int getPlataformFromSpinner(){
        switch (snPlataform.getSelectedItemPosition()){
            case 0:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+ StatsContainer.PC);
                return StatsContainer.PC;
            case 1:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+ StatsContainer.PS4);
                return StatsContainer.PS4;
            case 2:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+ StatsContainer.XBOX);
                return StatsContainer.XBOX;
            default:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+-1);
                return -1;
        }
    }

    private class GetStatsOnAsyncTask extends AsyncTask<String, Void, ApiConnection>{

        @Override
        protected void onPostExecute(ApiConnection stats) {
           // super.onPostExecute(apiConnection);
            progressDialog.dismiss();
            if (stats == null){
                Toast.makeText(getBaseContext(), "No se encontro el jugador", Toast.LENGTH_SHORT).show();
                Log.w("GetStatsAsync", "No se encontró el jugador u ocurrió un error al descargar las estadísticas ");
            }else{
                // Ya con las Stats descargadas colocamos las stats en la gui
                putStats(stats);
                Toast.makeText(getBaseContext(), "Descarga de estadísticas finalizada", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Descargando estadísticas");
            progressDialog.show();
        }

        @Override
        protected ApiConnection doInBackground(String... strings) {
            // Obtenemos los valores para inicializar el objeto Nthread
            ApiConnection apiConnection = new ApiConnection(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            apiConnection.downloadStats();
            return apiConnection;
        }
    }

}


