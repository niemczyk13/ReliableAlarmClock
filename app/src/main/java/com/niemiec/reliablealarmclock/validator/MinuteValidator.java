package com.niemiec.reliablealarmclock.validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MinuteValidator {
    private static EditText minute;
    private static String textBeforeChanged;

    public static void addMinutesViewTextChangedListener(EditText m) {
        minute = m;

        minute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textBeforeChanged = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (minute.hasFocus()) {
                    checkTheCorrectnessOfTheEnteredMinute();
                }
            }
        });
    }

    private static void checkTheCorrectnessOfTheEnteredMinute() {
        String s = minute.getText().toString();
        if (s.length() == 1) {
            int m = Integer.parseInt(s);
            if (m > 5) {
                minute.setText("0" + s);

            }
        } else {
           // minute.setText(textBeforeChanged);
        }
        minute.selectAll();
    }
}
