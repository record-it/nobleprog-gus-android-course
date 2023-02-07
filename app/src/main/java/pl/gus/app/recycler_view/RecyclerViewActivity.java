package pl.gus.app.recycler_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import pl.gus.app.databinding.ActivityRecyclerViewBinding;

public class RecyclerViewActivity extends AppCompatActivity {
    ActivityRecyclerViewBinding mBind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
    }
}