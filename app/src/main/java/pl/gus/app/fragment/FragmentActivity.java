package pl.gus.app.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import pl.gus.app.R;

/***
 * 1. Prosze utworzyć pusty fragment o nazwie RegisterFragment
 * 2. 
 */
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainerView, MainFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }

    public void onClickButtonNextFragment(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, LoginFragment.class, null)
                .addToBackStack("login")
                .commit();
    }
}