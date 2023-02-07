package pl.gus.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.gus.app.activity_life_cycle.LifeCycleActivity;


public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT_EDIT = "default-edit";
    public static final String COUNTER = "counter";

    private EditText mSearchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareActionBar();
    }

    /**
     * Proszę dodać MenuItem o nazwie main_search z ikoną lupki (search)
     * po kliknięciu na lupkę należy w Toast wyświetlić tekst z mSearchText
     */

    private void prepareActionBar(){
        ActionBar actionBar = getSupportActionBar();
        mSearchText = new EditText(this);
        mSearchText.setTextColor(Color.WHITE);
        if (actionBar != null){
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(mSearchText);
        }
    }
    public void onClickQuitButton(View v){
        startLifeCycleActivity();
    }

    private void startLifeCycleActivity() {
        Intent intent = new Intent(this, LifeCycleActivity.class);
        intent.putExtra(DEFAULT_EDIT, "Karol");
        intent.putExtra(COUNTER, 10);
        startActivity(intent);
    }

    public void onClickSendSMS(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:+48-123-456-789"));
        intent.putExtra("sms_body", "Hello from Android");
        Intent choose = Intent.createChooser(intent, "Wybierz aplikację");
        startActivity(choose);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_life:
                startLifeCycleActivity();
                return true;
            case R.id.main_configuration:
                Toast.makeText(this, "Configuration", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}