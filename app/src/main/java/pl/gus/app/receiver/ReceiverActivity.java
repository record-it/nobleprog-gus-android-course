package pl.gus.app.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.DownloadListener;

import java.io.File;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivityReceiverBinding;

public class ReceiverActivity extends AppCompatActivity {
    DownloadManager mManager;
    private long mTaskId;
    ActivityReceiverBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityReceiverBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        mBind.progressBar.setVisibility(View.VISIBLE);
        downloadFile("https://www.tabletowo.pl/wp-content/uploads/2021/12/apple-car-1946c46a51e6c13a9286261261fef3a4.jpeg");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * http://host.pl/jdfhksjdf/soidfuso/image.jpg
     *
     * @param fileUrl
     */
    private void downloadFile(String fileUrl) {
        if (mManager == null) {
            mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        }
        String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName);
        Uri destination = Uri.fromFile(file);
        request.setTitle(fileUrl);
        request.setVisibleInDownloadsUi(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setDestinationUri(destination);
        mTaskId = mManager.enqueue(request);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == mTaskId){
                Uri downloadedFileUri = mManager.getUriForDownloadedFile(id);
                mBind.imageView.setImageURI(downloadedFileUri);
                mBind.progressBar.setVisibility(View.GONE);
            }
        }
    };
}