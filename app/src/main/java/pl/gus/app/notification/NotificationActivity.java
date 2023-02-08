package pl.gus.app.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import pl.gus.app.R;
import pl.gus.app.form.FormActivity;

public class NotificationActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "12345";
    private static final String TAG = "NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notofication);
        createChannel();
        //sendNotification(getSimpleNotification("Hello from Android"));
        sendNotification(getActionNotification("Hello from Android"));
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "GUS",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("GUS channel");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification getSimpleNotification(String content) {
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("GUS")
                .setContentText(content)
                .setSmallIcon(R.drawable.baseline_settings_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
    }

    private Notification getActionNotification(String content) {
        Intent intent = new Intent(this, FormActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("Click");
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE);
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("GUS")
                .setContentText(content)
                .addAction(R.drawable.baseline_settings_24,"Click",pendingIntent)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.baseline_settings_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
    }

    private void sendNotification(Notification notification) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Brak zgody na powiadomienia");
            return;
        }
        NotificationManagerCompat.from(this).notify(121, notification);
    }


}