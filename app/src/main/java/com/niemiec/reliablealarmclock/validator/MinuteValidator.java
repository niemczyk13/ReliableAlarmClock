package com.niemiec.reliablealarmclock.validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.niemiec.reliablealarmclock.AddAlarmContractMVP;

public class MinuteValidator {
    private static AddAlarmContractMVP.View view;

    public static void checkTheCorrectnessOfTheEnteredMinute(AddAlarmContractMVP.View v) {
        view = v;
        String s = view.getMinute();
        if (oneNumberWasEntered(s)) {
            addZeroForTheAppropriateNumbers(s);
        } else {
            view.selectAllMinute();
        }

    }

    private static void addZeroForTheAppropriateNumbers(String s) {
        int m = Integer.parseInt(s);
        if (m > 5) {
            view.setMinute("0" + s);
        }
    }

    private static boolean oneNumberWasEntered(String s) {
        return s.length() == 1;
    }
}
