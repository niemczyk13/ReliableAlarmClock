package com.niemiec.reliablealarmclock.view.fragment.dialog;

public class CalendarMinDateValidator {
    public static int calculateTheOffsetForTheSelectedTime(String h, String m, String actualHour, String actualMinute) {
        int hour = Integer.parseInt(h);
        int minute = Integer.parseInt(m);

        int aH = Integer.parseInt(actualHour);
        int aM = Integer.parseInt(actualMinute);

        if (hour < aH) {
            return 86400000;
        } else if (hour == aH && minute <= aM) {
            return 86400000;
        }

        return -1000;
    }
}
