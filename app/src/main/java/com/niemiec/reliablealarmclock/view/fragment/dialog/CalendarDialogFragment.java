package com.niemiec.reliablealarmclock.view.fragment.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niemiec.reliablealarmclock.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CalendarDialogFragment extends DialogFragment {
    public CalendarDialogFragment() {

    }

    public static CalendarDialogFragment newInstance() {
        CalendarDialogFragment frag = new CalendarDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Kalendarz");
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container);
    }
}
