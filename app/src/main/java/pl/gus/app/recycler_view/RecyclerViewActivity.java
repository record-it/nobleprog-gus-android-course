package pl.gus.app.recycler_view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import pl.gus.app.databinding.ActivityRecyclerViewBinding;
import pl.gus.app.form.UserViewModel;

public class RecyclerViewActivity extends AppCompatActivity {
    ActivityRecyclerViewBinding mBind;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        mContext = getBaseContext();
        List<UserViewModel> users = List.of(
                new UserViewModel("Karol", null),
                new UserViewModel("Ewa", "Kowal"),
                new UserViewModel("Adam", null)
        );
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(users);
        mBind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBind.recyclerView.setAdapter(adapter);
    }

    public void clickOnButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] options = {"AA", "BB", "CC"};
        builder.setTitle("Wybierz kolor")
                .setItems(options, (d, i) -> {
                    Toast.makeText(mContext, "Wybrałeś " + options[i] , Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "Clicked positive button " + i, Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}