package com.niemiec.reliablealarmclock.model.pack;

import com.niemiec.reliablealarmclock.model.alarm.Alarm;

import java.util.List;

public class AlarmList {
    private Long id;
    private List<Alarm> alarms;
    private List<Boolean> activity;
}
