package pl.gus.app.form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding mBind;
    private UserViewModel mModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        mModel = new UserViewModel("Karol", "Nowak");
        mBind.setUser(mModel);
    }

    public void onClickSaveButton(View w){

        Toast.makeText(this, mBind.getUser().toString(), Toast.LENGTH_SHORT).show();
    }
}