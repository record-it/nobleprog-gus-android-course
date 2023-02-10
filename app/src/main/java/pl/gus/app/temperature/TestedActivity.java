package pl.gus.app.temperature;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Przykładowa klasa do testowania, która tworzy przycik programowo przycisk o okreslonej etykiecie
 * Parametry są przekazywane do aktywności w intencji
 * create - wartości logiczna, dla której tworzony jest przycisk
 * label - tekst z etykietą przycisku
 */
public class TestedActivity extends AppCompatActivity{
        public static final int BUTTON_ID = 100;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            String label = getIntent().getStringExtra("label");
            boolean create = getIntent().getBooleanExtra("create", false);
            LinearLayout layout = new LinearLayout(this);
            setContentView(new LinearLayout(this));
            if (create) {
                Button button = new Button(this);
                button.setId(BUTTON_ID);
                button.setText(label);
                layout.addView(button);
            }
        }
}
