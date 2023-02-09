package pl.gus.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DownloadService extends Service {
    private static final String TAG = "DOWNLOAD";
    private ExecutorService executor;
    private IBinder mBinder = new DownloadServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newSingleThreadExecutor();
    }

    public class DownloadServiceBinder extends Binder {
        DownloadService getService(){
            return DownloadService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }

    public void download(String s, Consumer<String> callback){
        if (s == null){
            callback.accept(null);
            return;
        }
        Runnable task = () ->{
            try {
                URL url = new URL(s);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");
                if (200 == con.getResponseCode()){
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String body = reader.lines().collect(Collectors.joining());
                    callback.accept(body);
                    Log.i(TAG, "Download completed!");
                }
            } catch (IOException e) {
                Log.i(TAG, "Download failure! " + e.getMessage());
                callback.accept(null);
            }
        };
        executor.execute(task);
    }
}
