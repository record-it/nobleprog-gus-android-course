package pl.gus.app.slider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.os.Bundle;
import android.view.View;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivitySliderBinding;

public class SliderActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {
    ActivitySliderBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBind = ActivitySliderBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(mBind.getRoot());
        mBind.sliderLeft.setOnClickListener(v -> {
            mBind.getRoot().openPane();
        });
        mBind.sliderRight.setOnClickListener(v ->{
            mBind.getRoot().closePane();
        });

    }

    @Override
    public void onPanelSlide(@NonNull View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(@NonNull View panel) {

    }

    @Override
    public void onPanelClosed(@NonNull View panel) {

    }
}