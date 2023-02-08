package pl.gus.app.files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import pl.gus.app.R;
import pl.gus.app.databinding.ActivityFileBinding;

public class FileActivity extends AppCompatActivity {
    ActivityFileBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ActivityFileBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
//        try(InputStream stream = openFileInput("text.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
//            reader.lines().forEach(line -> {
//                mBind.filesText.append(line);
//                mBind.filesText.append(System.lineSeparator());
//            });
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File dir = getFilesDir();
        Path path = Paths.get(dir.getAbsolutePath(), "text.txt");
        try {
            Files.lines(path)
                .forEach(line -> {
                mBind.filesText.append(line);
                mBind.filesText.append(System.lineSeparator());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSaveButtonStream(View w) {
        try (OutputStream stream = openFileOutput("text.txt", MODE_PRIVATE);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        ) {
            String string = mBind.filesText.getText().toString();
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSaveButtonFiles(View w) {
        File dir = getFilesDir();
        Path path = Paths.get(dir.getAbsolutePath(), "text.txt");
        try {
            Files.write(path, mBind.filesText.getText().toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}