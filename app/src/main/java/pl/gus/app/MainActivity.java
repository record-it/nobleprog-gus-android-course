package pl.gus.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import pl.gus.app.activity_life_cycle.LifeCycleActivity;


public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT_EDIT = "default-edit";
    public static final String COUNTER = "counter";

    private Button mQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickQuitButton(View v){
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


}