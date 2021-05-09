package com.niemiec.reliablealarmclock.validator;

import android.widget.Toast;

import com.niemiec.reliablealarmclock.AddAlarmContractMVP;
import com.niemiec.reliablealarmclock.view.activity.AddAlarmActivity;

public class HourValidator {

    public static String checkTheCorrectnessOfTheEnteredHour(String hour) {
        if (oneCharacterWasEntered(hour.length())) {
            return blockTheEntryOfAnIncorrectFirstDigitOfTheHour(hour);
        } else if (moreThanOneCharacterHasBeenEntered(hour.length())) {
            return blockTheEntryOfAnIncorrectSecondDigitOfTheHour(hour);
        }
        return hour;
    }

    private static boolean oneCharacterWasEntered(int length) {
        return length == 1;
    }

    private static boolean moreThanOneCharacterHasBeenEntered(int length) {
        return length > 1;
    }

    private static String blockTheEntryOfAnIncorrectFirstDigitOfTheHour(String hour) {
        int h = Integer.parseInt(hour);
        if (h > 2 && h < 10) {
            String hReturn = "0" + hour;
            return hReturn;
        }
        return hour;
    }

    private static String blockTheEntryOfAnIncorrectSecondDigitOfTheHour(String hour) {
        int h = Integer.parseInt(hour);

        if (checkIf24IsEntered(h)) {
            return "00";
        }

        if (checkIfThereIsAnHourGreaterThan24(h)) {
            return "2";
        }

        return hour;
    }

    private static boolean checkIf24IsEntered(int hour) {
        return hour == 24;
    }

    private static boolean checkIfThereIsAnHourGreaterThan24(int hour) {
        return hour > 23;
    }

}