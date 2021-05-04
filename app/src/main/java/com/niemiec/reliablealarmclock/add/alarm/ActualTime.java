package com.niemiec.reliablealarmclock.add.alarm;

import android.os.Build;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import androidx.annotation.RequiresApi;

public class ActualTime {

    public static String getActualHour() {
        Date date = new Date();
        SimpleDateFormat h = new SimpleDateFormat("HH");
        return h.format(date);
    }

    public static String getActualMinute() {
        Date date = new Date();
        SimpleDateFormat m = new SimpleDateFormat("mm");
        return m.format(date);
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void useDate2() {
        LocalTime localTime = LocalTime.now();

        String h = Integer.toString(localTime.getHour());
        String hh = h.length() != 2 ? "0" + h : h;

        String m = Integer.toString(localTime.getMinute());
        String mm = m.length() != 2 ? "0" + m : m;

    }

     */

}
