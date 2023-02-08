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
        Toast.makeText(mContext, "Position " + view.getTag() , Toast.LENGTH_SHORT).show();
    }
}