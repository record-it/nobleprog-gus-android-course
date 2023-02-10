package pl.gus.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pl.gus.app.R;
import pl.gus.app.databinding.FragmentLoginBinding;
import pl.gus.app.databinding.FragmentMainBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding mBind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBind = FragmentLoginBinding.inflate(inflater, container, false);
        View v = mBind.getRoot();
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBind.login.setEnabled(true);
        mBind.login.setOnClickListener(w -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .hide(this)
                    .replace(R.id.fragmentContainerView, RegisterFragment.class, null, "register")
                    .commit();
        });
    }
}
