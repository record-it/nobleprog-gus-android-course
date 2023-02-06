package pl.gus.app.activity_life_cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import pl.gus.app.MainActivity;
import pl.gus.app.R;

public class LifeCycleActivity extends AppCompatActivity {
    private static final String TAG = "LIFE";
    public static final String EDIT = "edit";
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        mEdit = findViewById(R.id.life_edit);
        Intent intent = getIntent();
        String edit = intent.getStringExtra(MainActivity.DEFAULT_EDIT);
        int counter = intent.getIntExtra(MainActivity.COUNTER, 0);
        if (savedInstanceState != null){
            String content = savedInstanceState.getString(EDIT);
            mEdit.setText(content);
        } else {
            mEdit.setText(edit);
        }
        Log.i(TAG, "onCreate");
    }
    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(EDIT, mEdit.getText().toString());
        Log.i(TAG, "onSaveInstanceState");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }
}