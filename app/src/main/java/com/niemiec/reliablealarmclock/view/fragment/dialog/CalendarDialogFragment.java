package com.niemiec.reliablealarmclock.view.fragment.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.view.activity.AddAlarmActivity;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CalendarDialogFragment extends DialogFragment {

/*
    public static CalendarDialogFragment newInstance() {
        CalendarDialogFragment frag = new CalendarDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Kalendarz");
        frag.setArguments(args);
        return frag;
    }
*/
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog d = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        d.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        return d;
    }

    /*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container);
    }

     */
}
