package pl.gus.app.form;

import android.net.Uri;
import android.widget.EditText;

import androidx.databinding.InverseMethod;

import java.time.LocalDate;

public class UriConverter {
    @InverseMethod("stringToUri")
    public static String uriToString(EditText view, Uri uri) {
        return uri.toString();
    }

    public static Uri stringToUri(EditText view, String uri) {
        return Uri.parse(uri);
    }
}
