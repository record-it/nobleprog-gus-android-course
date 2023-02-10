package pl.gus.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PreciseDataConnectionState;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.gus.app.activity_life_cycle.LifeCycleActivity;
import pl.gus.app.databinding.ActivityMainBinding;
import pl.gus.app.files.FileActivity;
import pl.gus.app.form.FormActivity;
import pl.gus.app.fragment.FragmentActivity;
import pl.gus.app.notification.NotificationActivity;
import pl.gus.app.open_street_map.OpenMapActivity;
import pl.gus.app.receiver.ReceiverActivity;
import pl.gus.app.recycler_view.RecyclerViewActivity;
import pl.gus.app.sensor.SensorActivity;
import pl.gus.app.service.ServiceActivity;
import pl.gus.app.slider.SliderActivity;


public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT_EDIT = "default-edit";
    public static final String COUNTER = "counter";

    private EditText mSearchText;
    private ActivityMainBinding mBind;
    List<String> mSet;

    Map<String, Runnable> mActivityMap;

    private void createConfiguration() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean exists = prefs.getBoolean("exists", false);
        if (!exists) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(getString(R.string.prefs_exists), true);
            editor.putString(getString(R.string.prefs_message), "Default message");
            editor.commit();
        }
    }

    //Odczytać z Shared Preferencies w aktywności NotificationActivity wartość prefs_message w Toast
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        prepareActionBar();
        createConfiguration();
        mActivityMap = new HashMap<>(Map.of(
                "01. Uruchom LifeCycleActivity", () -> {
                    runActivity(LifeCycleActivity.class);
                },
                "02. Uruchom FormActivity", () -> {
                    runActivity(FormActivity.class);
                },
                "03. Uruchom RecyclerView Activity", () -> {
                    runActivity(RecyclerViewActivity.class);
                },
                "04. Uruchom NotificationActivity", () -> {
                    runActivity(NotificationActivity.class);
                },
                "05. Uruchom FileActivity", () -> {
                    runActivity(FileActivity.class);
                },
                "06. Uruchom ServiceActivity", () -> {
                    runActivity(ServiceActivity.class);
                },
                "07. Uruchom ReceiverActivity", () -> {
                    runActivity(ReceiverActivity.class);
                },
                "08. Uruchom SensorActivity", () -> {
                    runActivity(SensorActivity.class);
                },
                "09. Uruchom OpenMapActivity", () -> {
                    runActivity(OpenMapActivity.class);
                },
                "10. Uruchom FragmentActivity", () -> {
                    runActivity(FragmentActivity.class);
                }
        ));
        mActivityMap.put("11. Uruchom SliderActivity", () -> runActivity(SliderActivity.class));
        mSet = new ArrayList<>(mActivityMap.keySet());
        mSet.sort(String::compareTo);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                mSet
        );
        mBind.activitySpinner.setAdapter(adapter);
        mBind.activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private <T> void runActivity(Class<T> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * Proszę dodać MenuItem o nazwie main_search z ikoną lupki (search)
     * po kliknięciu na lupkę należy w Toast wyświetlić tekst z mSearchText
     */

    private void prepareActionBar() {
        ActionBar actionBar = getSupportActionBar();
        mSearchText = new EditText(this);
        mSearchText.setTextColor(Color.WHITE);
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(mSearchText);
        }
    }

    public void onClickQuitButton(View v) {
        mActivityMap.get(mSet.get(mBind.activitySpinner.getSelectedItemPosition())).run();
    }

    private void startLifeCycleActivity() {
        Intent intent = new Intent(this, LifeCycleActivity.class);
        intent.putExtra(DEFAULT_EDIT, "Karol");
        intent.putExtra(COUNTER, 10);
        startActivity(intent);
    }

    public void onClickSendSMS(View view) {
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
        switch (item.getItemId()) {
            case R.id.main_life:
                //startLifeCycleActivity();
                startFormActivity();
                return true;
            case R.id.main_configuration:
                Toast.makeText(this, "Configuration", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    //napisz metodę startFormActivity, która uchamia aktywność FormActivity
    public void startFormActivity() {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
}