package com.niemiec.reliablealarmclock.view.fragment.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import com.niemiec.reliablealarmclock.add.alarm.ActualTime;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CalendarDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);

        String hour = getArguments().getString("hour");
        String minute = getArguments().getString("minute");
        String actualHour = ActualTime.getActualHour();
        String actualMinute = ActualTime.getActualMinute();

        int timeToShift = CalendarMinDateValidator.calculateTheOffsetForTheSelectedTime(hour, minute, actualHour, actualMinute);

        dpd.getDatePicker().setMinDate(System.currentTimeMillis() + timeToShift);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dpd.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        }

        return dpd;
    }

}
