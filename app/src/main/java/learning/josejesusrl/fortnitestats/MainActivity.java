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

        String[] gameModes = {"Solo", "Duo", "Squad", "En todos"};
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


    // Obtenemos las estadicas llamando al objeto Stats que se encarga de depurar las estadisticas
    public void getStats(View v){
        NThread nt = new NThread(this, etPlayer.getText().toString(), snPlataform.getSelectedItem().toString(), snGamemode.getSelectedItem().toString());
        nt.getStats();
        Toast.makeText(this, "Un momento porfavor...", Toast.LENGTH_LONG);
        // Obtenemos las estadisticas del jugador para manipularlo mas facil
        GameStats gameStats = nt.getPlayerStats().getGameStats();
        try {
            // Esperamos al servidor y al parse
            Thread.sleep(3000);
        } catch(InterruptedException e) {}


        if (nt.getPlayerStats() != null){
            // Borramos el mensaje inicial
            arrayListView.remove(0);
            // Ingresamos todos los datos de las estadisticas
            arrayListView.add("Plataforma: "+nt.getPlataform());
            arrayListView.add("Modo de juego: "+nt.getGameMode());
            arrayListView.add("K/D ratio: "+gameStats.getKpd());
            arrayListView.add("Victorias: "+gameStats.getWins());
            arrayListView.add("Ratio de Victorias: "+gameStats.getWinRate());
            arrayListView.add("Partidas jugadas: "+gameStats.getMatchesPlayed());
            arrayListView.add("Ultima partida jugada: "+gameStats.getLastMatch());

            // Regrescamos el ListView
            listViewAdapter.notifyDataSetChanged();

            Log.i("MainActivity", "Las estats fueron añadidas");


        }else{
            Log.w("MainActivity", "Los Stats son nullos");
        }
    }

    public void saveUserPreference(View v){

    }

    public void clearFields(View v){

    }
}

