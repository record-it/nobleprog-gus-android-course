package pl.gus.app.form;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @InverseMethod("stringToDate")
    public static String dateToString(EditText view, LocalDate date) {
        return date.format(FORMATTER);
    }

    public static LocalDate stringToDate(EditText view, String dateString) {
        return LocalDate.parse(dateString, FORMATTER);
    }
}
