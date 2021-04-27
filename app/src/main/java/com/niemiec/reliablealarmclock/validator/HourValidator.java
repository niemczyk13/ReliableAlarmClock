package com.niemiec.reliablealarmclock.validator;

import com.niemiec.reliablealarmclock.AddAlarmContractMVP;

public class HourValidator {
    private static AddAlarmContractMVP.View view;

    public static void checkTheCorrectnessOfTheEnteredHour(AddAlarmContractMVP.View v) {
        view = v;
        String s = view.getHour();
        if (s.length() == 1) {
            blockTheEntryOfAnIncorrectFirstDigitOfTheHour(s);
        } else if (s.length() > 1) {
            blockTheEntryOfAnIncorrectSecondDigitOfTheHour(s);
        }
    }

    private static void blockTheEntryOfAnIncorrectFirstDigitOfTheHour(String s) {
        int h = Integer.parseInt(s);
        if (h > 2) {
            view.showHour("0" + s);
            view.setNextFocusAfterHour();
            view.transferActivityToMinutes();
            view.selectAllMinute();
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
            view.showHour(Integer.toString(first));
            int position = Integer.toString(first).length();
            view.setHourSelection(position);
        } else {
            switchToTheMinutesViewAndSelectAll();
        }
    }

    private static void switchToTheMinutesViewAndSelectAll() {
        view.setNextFocusAfterHour();
        view.transferActivityToMinutes();
        view.selectAllMinute();
    }

}
