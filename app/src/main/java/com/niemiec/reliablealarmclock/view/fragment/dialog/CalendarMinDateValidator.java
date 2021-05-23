package com.niemiec.reliablealarmclock.view.fragment.dialog;

import com.niemiec.reliablealarmclock.add.alarm.ActualTime;

public class CalendarMinDateValidator {
    public static int calculateTheOffsetForTheSelectedTime(String h, String m) {
        int hour = Integer.parseInt(h);
        int minute = Integer.parseInt(m);

        int actualHour = Integer.parseInt(ActualTime.getActualHour());
        int actualMinute = Integer.parseInt(ActualTime.getActualMinute());

        if (hour < actualHour) {
            return 86400000;
        } else if (hour == actualHour && minute <= actualMinute) {
            return 86400000;
        }

        return -1000;
    }
}
