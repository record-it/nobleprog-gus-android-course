package pl.gus.app.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.util.function.Consumer;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {
    private static final String TAG = "SERVICE";
    ActivityServiceBinding mBind;
    DownloadService mDownloadService;
    boolean mBound;
    final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DownloadService.DownloadServiceBinder binder = (DownloadService.DownloadServiceBinder) iBinder;
            mDownloadService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());

    }

    public void onClickDownlodButton(View v) {
        Consumer<String> updateUI = body -> {
            if (body == null){
                Log.e(TAG, "No body!");
                return;
            }
            //Handler handler = new Handler(Looper.getMainLooper());
            Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
            handler.post(() -> {
                    mBind.serviceJson.setText(body);
                    }
            );
        };
        mDownloadService.download("http://api.nbp.pl/api/exchangerates/tables/C/2023-02-09/", updateUI);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }
}