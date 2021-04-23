package com.niemiec.reliablealarmclock.model;

import java.util.Map;

public class Week {
    private Map<DaysOfTheWeek, Boolean> days;

    public enum DaysOfTheWeek {
        MONDAY, SATURDAY;
    }
}
