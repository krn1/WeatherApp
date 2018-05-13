package weather.co.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class DateUtil {

    public static String getDayFrom(String dateStr) {

        String DATE_FORMAT_I = "yyyy-MM-dd HH:mm:ss";
        String DATE_FORMAT_O = "EEEE";
        try {
            SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I, Locale.US);
            SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O, Locale.US);
            Date date = formatInput.parse(dateStr);
            return formatOutput.format(date);

        } catch (ParseException e) {
            Timber.e("Parse exception " + e.getLocalizedMessage());
        }

        return "";
    }

    public static String getDateFrom(long timestamp){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000L);
        String date =  DateFormat.format("yyyy-MM-dd HH:mm:ss", cal).toString();
        return getDayFrom(date);
    }
}
