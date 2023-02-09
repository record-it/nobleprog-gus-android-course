package pl.gus.app.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivitySensorBinding;
import pl.gus.app.databinding.ActivityServiceBinding;

public class SensorActivity extends AppCompatActivity {
    ActivitySensorBinding mBind;
    SensorManager mService;
    SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            mBind.textView.setText("");
            if (sensorEvent.values.length == 3) {
                mBind.textView.append("x: " + sensorEvent.values[0] + "\n");
                mBind.textView.append("y: " + sensorEvent.values[1] + "\n");
                mBind.textView.append("z: " + sensorEvent.values[2] + "\n");
            } else {
                mBind.textView.append("x: " + sensorEvent.values[0] + "\n");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivitySensorBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        mService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList();
    }

    private void sensorList(){
        for(Sensor  sensor: mService.getSensorList(Sensor.TYPE_ALL)){
            Log.i("SENSOR", sensor.getName() +" " + sensor.getVendor() +" " + (sensor.getMinDelay() == 0 ? "no stream" : "stream" ));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mService.registerListener(mListener, mService.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mService.unregisterListener(mListener);
    }
}