package com.niemiec.reliablealarmclock.view.fragment.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.add.alarm.ActualTime;
import com.niemiec.reliablealarmclock.view.activity.AddAlarmActivity;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CalendarDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int hour = Integer.parseInt(getArguments().getString("hour"));
        int minute = Integer.parseInt(getArguments().getString("minute"));

        int actualHour = Integer.parseInt(ActualTime.getActualHour());
        int actualMinute = Integer.parseInt(ActualTime.getActualMinute());

        int time = -1000;

        if (hour < actualHour && minute < actualMinute) {
            time = 86400000;
        }

        System.out.println(hour + " " + minute + ", " + actualHour + " " + actualMinute);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog d = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        //TODO DODATKOWO, JEŻELI GODZINA < NIŻ MOŻLIWA NA DZIS TO W KALENDARZU ZABLOKOWANY DZISIEJSZY DZIEN
        d.getDatePicker().setMinDate(System.currentTimeMillis() + time);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        }

        return d;
    }
}
