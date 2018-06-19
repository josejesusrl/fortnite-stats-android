package learning.josejesusrl.fortnitestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText etPlayer;
    Spinner snPlataform, snGamemode;
    ArrayList<String> arrayListView;
    ArrayAdapter<String> listViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        snGamemode = (Spinner) findViewById(R.id.snGameMode);
        snPlataform = (Spinner) findViewById(R.id.snPlataform);
        etPlayer = (EditText) findViewById(R.id.etNombre);

        String[] gameModes = {"Solo", "Duo", "Squad", "Todos"};
        String[] plataforms = {"PC", "PlayStation 4", "Xbox One"};
        arrayListView = new ArrayList<String>();
        arrayListView.add("Ingresa los datos para ver tus estadisticas de juego");

        ArrayAdapter<String> snPlataformAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, plataforms);
        ArrayAdapter<String> snGamemodeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gameModes);
        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListView);

        snGamemode.setAdapter(snGamemodeAdapter);
        snPlataform.setAdapter(snPlataformAdapter);
        listView.setAdapter(listViewAdapter);
    }

    private int getGamemode(){
        switch (snGamemode.getSelectedItemPosition()){
            case 0:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+NThread.SOLO);
                return NThread.SOLO;
            case 1:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+NThread.DUO);
                return NThread.DUO;
            case 2:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+NThread.SQUAD);
                return NThread.SQUAD;
            case 3:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+NThread.ALL);
                return NThread.ALL;
            default:
                Log.d("MainAct.getGamemode", "Modo de juego seleccionado: "+-1);
                return -1;
        }
    }
    private int getPlataform(){
        switch (snPlataform.getSelectedItemPosition()){
            case 0:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+NThread.PC);
                return NThread.PC;
            case 1:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+NThread.PS4);
                return NThread.PS4;
            case 2:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+NThread.XBOX);
                return NThread.XBOX;
            default:
                Log.d("MainAct.getPlataform", "Plataforma seleccionada: "+-1);
                return -1;
        }
    }
    // Metodo para crear el objeto Nthread y regresar el mismo objeto ya con las stats
    private NThread makeStats(){
        NThread nt = new NThread(this, etPlayer.getText().toString(), getPlataform(), getGamemode());
        nt.getStats();

        Toast.makeText(this, "Un momento porfavor...", Toast.LENGTH_LONG);

        // Esperamos al servidor y al parse
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {}

        // Retornamos el objeto Stats ya con las estadisticas del usuario
        return nt;
    }

    // Metodo para colocar las estadisticas en la GUI
    private void putStats(){
        NThread nt = makeStats();
        Stats stats = nt.getPlayerStats();

        if (stats != null){

            GameStats gameStats = stats.getGameStats();


            // Ingresamos todos los datos de las estadisticas
            arrayListView.add("Usuario: "+nt.getUser());
            arrayListView.add("Modo de jugo: "+nt.getGameMode());
            arrayListView.add("K/D ratio: "+gameStats.getKpd());
            arrayListView.add("Victorias: "+gameStats.getWins());
            arrayListView.add("Ratio de Victorias: "+gameStats.getWinRate());
            arrayListView.add("Partidas jugadas: "+gameStats.getMatchesPlayed());
            arrayListView.add("Ultima partida jugada: "+gameStats.getLastMatch());

            // Regrescamos el ListView
            listViewAdapter.notifyDataSetChanged();

            Log.i("MainAct.putStats", "Las Stats fueron añadidas: "+stats.getId());
        }else{
            Log.w("MainActivity", "Los Stats son nullos");
        }
    }

    // Obtenemos las estadicas llamando al objeto Stats que se encarga de depurar las estadisticas
    public void getStats(View v){
        if (getPlataform() == -1){
            Toast.makeText(this, "Porfavor selecciona una plataforma", Toast.LENGTH_SHORT);
            Log.i("MainActivity.getStats()", "No se a seleccionado ninguna plataforma");
        }
        if (getGamemode() == -1){
            Toast.makeText(this, "Porfavor selecciona un modo de juego", Toast.LENGTH_SHORT);
            Log.i("MainActivity.getStats()", "No se a seleccionado ningun modo de juego");
        }
        if (getPlataform() != -1 && getGamemode() != -1){
            putStats();
        }

    }

    public void saveUserPreference(View v){

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
}

