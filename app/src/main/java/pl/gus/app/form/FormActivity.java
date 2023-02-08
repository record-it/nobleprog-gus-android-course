package pl.gus.app.form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivityFormBinding;
import pl.gus.app.sqlite.BaseContext;
import pl.gus.app.sqlite.User;

public class FormActivity extends AppCompatActivity {
    private static final String TAG = "FORM";
    private ActivityFormBinding mBind;
    private UserViewModel mModel;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        int userId = getIntent().getIntExtra(getString(R.string.user_id), -1);
        if(userId == -1){
            Toast.makeText(this, "No valid user id, cand display data", Toast.LENGTH_SHORT).show();
            return;
        }
        executor.execute(() -> {
            User user = BaseContext.getInstance(this).userDao().findById(userId);
            if (user == null){
                return;
            }
            runOnUiThread(() -> {
                mModel = new UserViewModel();
                mBind.setUser(mModel);
                mModel.setBirth(user.getBirth());
                mModel.setLastName(user.getLastName());
                mModel.setFirstName(user.getFirstName());
            });
        });
    }

    public void onClickBirthButton(View v) {
        DatePickerDialog datePicker = new DatePickerDialog(this);
        datePicker.setOnDateSetListener((datePicker1, year, month, day) -> {
            mModel.setBirth(LocalDate.of(year, month, day).format(DateConverter.FORMATTER));
        });
        datePicker.show();
    }

    public void onClickSaveButton(View w) {
        saveUser();
    }

    private void saveUser() {
        BaseContext baseContext = BaseContext.getInstance(this);
        executor.execute(() -> {
            baseContext.userDao().save(mModel.toEntity());
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Czy zapisać dane?")
                .setPositiveButton("Tak", (v, i) -> {
                    saveUser();
                    super.onBackPressed();
                })
                .setNegativeButton("Nie", (v, i) -> {
                    super.onBackPressed();
                })
                .setOnCancelListener((v) -> {
                    super.onBackPressed();
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdownNow();
    }
}