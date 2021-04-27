package com.niemiec.reliablealarmclock.validator;

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
            view.showMinute("0" + s);
        }
    }

    private static boolean oneNumberWasEntered(String s) {
        return s.length() == 1;
    }
}
