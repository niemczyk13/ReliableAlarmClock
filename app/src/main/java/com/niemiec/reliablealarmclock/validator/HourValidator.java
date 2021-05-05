package com.niemiec.reliablealarmclock.validator;

import com.niemiec.reliablealarmclock.AddAlarmContractMVP;

public class HourValidator {
    private static AddAlarmContractMVP.View view;
    private static String result = "";

    public static String checkTheCorrectnessOfTheEnteredHour(AddAlarmContractMVP.View v) {
        view = v;
        String s = view.getHour();
        if (oneCharacterWasEntered(s.length())) {
            blockTheEntryOfAnIncorrectFirstDigitOfTheHour(s);
        } else if (moreThanOneCharacterHasBeenEntered(s.length())) {
            blockTheEntryOfAnIncorrectSecondDigitOfTheHour(s);
        }
        return result;
    }

    private static boolean oneCharacterWasEntered(int length) {
        return length == 1;
    }

    private static boolean moreThanOneCharacterHasBeenEntered(int length) {
        return length > 1;
    }

    private static void blockTheEntryOfAnIncorrectFirstDigitOfTheHour(String s) {
        int h = Integer.parseInt(s);
        if (h > 2) {
            result = "0" + s;

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
            checkIfLaterThan7Pm(first, second);
        } else {
            switchToTheMinutesViewAndSelectAll();
        }
    }

    private static void checkIfLaterThan7Pm(int first, int second) {
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
