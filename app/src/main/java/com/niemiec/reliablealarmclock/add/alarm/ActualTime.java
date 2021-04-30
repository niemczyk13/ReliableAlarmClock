package com.niemiec.reliablealarmclock.add.alarm;

import android.os.Build;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import androidx.annotation.RequiresApi;

public class ActualTime {
    private EditText hour;
    private EditText minute;

    //TODO
    public static String getActualHour() {
        return null;
    }

    //TODO
    public static String getActualMinute() {
        return null;
    }

    public void setActualTime(EditText h, EditText m) {
        hour = h;
        minute = m;

       // useDate();
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            useLocalTime();
        } else {
            useDate();
        } */
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void useDate() {
        Date date = new Date();
        SimpleDateFormat h = new SimpleDateFormat("HH");
        SimpleDateFormat m = new SimpleDateFormat("mm");

        hour.setText(h.format(date));
        minute.setText(m.format(date));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void useDate2() {
        LocalTime localTime = LocalTime.now();

        String h = Integer.toString(localTime.getHour());
        hour.setText(h.length() != 2 ? "0" + h : h);

        String m = Integer.toString(localTime.getMinute());
        minute.setText(m.length() != 2 ? "0" + m : m);
    }

}
