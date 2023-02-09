package pl.gus.app.service;

import androidx.annotation.NonNull;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    public void onClickDownloadButtonOkHttpClient(View v) {
        HttpUrl.Builder builder = HttpUrl.parse("http://api.nbp.pl/api/exchangerates/tables/C/2023-02-09/")
                .newBuilder();
        builder.addQueryParameter("format", "json");
        Request request = new Request.Builder()
                .url(builder.build())
                .addHeader("Accept", "application/json")
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    mBind.serviceJson.setText("Problem with request! No body " + e.getMessage());
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        if (response.body() != null) {
                            try {
                                mBind.serviceJson.setText(response.body().string());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                }
            }
        });

    }


    public void onClickDownloadButton(View v) {
        Consumer<String> updateUI = body -> {
            if (body == null) {
                Log.e(TAG, "No body!");
                return;
            }
            //Handler handler = new Handler(Looper.getMainLooper());
            Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
            handler.post(() -> {
                        //mBind.serviceJson.setText(body);
                        Gson gson = new GsonBuilder().create();
                        CurrencyRates[] currencyRates = gson.fromJson(body, CurrencyRates[].class);
                        mBind.serviceJson.setText("");
                        mBind.serviceJson.append("Effective Date: " + currencyRates[0].effectiveDate + "\n");
                        mBind.serviceJson.append("No: " + currencyRates[0].no + "\n");
                        String s = gson.toJson(currencyRates);
                        Map<String, Object> obj = new HashMap<>();
                        obj.put("counter", 10);
                        obj.put("playerName", "Karol");
                        String json = gson.toJson(obj);
                        mBind.serviceJson.append(json + "\n");
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