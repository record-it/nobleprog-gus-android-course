package pl.gus.app.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import pl.gus.app.R;


public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainerView, MainFragment.class, null, "main")
                    .setReorderingAllowed(true)
                    .commit();
        }
    }

    public void onClickButtonNextFragment(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, LoginFragment.class, null)
                .commit();
    }
    /***
     * 1. Prosze utworzyć pusty fragment o nazwie RegisterFragment
     * 2. Kliknięcie na textView w MainFragment powinno wyświetlić
     *    RegisterFragment
     */
    public void onClickTextRegisterFragment(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, RegisterFragment.class, null)
                .addToBackStack("register")
                .commit();
    }
}