package com.niemiec.reliablealarmclock.validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class HourValidator {
    private static EditText hour;
    private static EditText minute;

    public static void addHourViewTextChangedListener(EditText h, EditText m) {
        hour = h;
        minute = m;

        hour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if (hour.hasFocus()) {
                    checkTheCorrectnessOfTheEnteredHour();
                }
            }
        });
    }

    private static void checkTheCorrectnessOfTheEnteredHour() {
        String s = hour.getText().toString();
        if (s.length() == 1) {
            blockTheEntryOfAnIncorrectFirstDigitOfTheHour(s);
        } else if (s.length() > 1) {
            blockTheEntryOfAnIncorrectSecondDigitOfTheHour(s);
        }
    }

    private static void blockTheEntryOfAnIncorrectFirstDigitOfTheHour(String s) {
        int h = Integer.parseInt(s);
        if (h > 2) {
            hour.setText("0" + s);
            hour.setNextFocusRightId(minute.getId());
            hour.focusSearch(View.FOCUS_RIGHT).requestFocus(); //przeniesienie aktywności na minuty
            minute.selectAll();
        }
    }

    private static void blockTheEntryOfAnIncorrectSecondDigitOfTheHour(String s) {
        int first = Integer.parseInt(s.substring(0, 1));
        int second = Integer.parseInt(s.substring(1, 2));
        if (first == 2) {
            checkIfLaterThn7Pm(first, second);
        } else {
            switchToTheMinutesViewAndSelectAll();
        }
    }

    private static void checkIfLaterThn7Pm(int first, int second) {
        if (second > 3) {
            hour.setText(Integer.toString(first));
            int position = Integer.toString(first).length();
            hour.setSelection(position);
        } else {
            switchToTheMinutesViewAndSelectAll();
        }
    }

    private static void switchToTheMinutesViewAndSelectAll() {
        hour.setNextFocusRightId(minute.getId());
        hour.focusSearch(View.FOCUS_RIGHT).requestFocus();
        minute.selectAll();
    }

}
