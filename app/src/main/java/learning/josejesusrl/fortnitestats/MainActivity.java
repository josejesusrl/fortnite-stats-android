package learning.josejesusrl.fortnitestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NThread nt = new NThread(this, "josejesusrl", "pc", "solo");
        nt.getStats();

    }
}

