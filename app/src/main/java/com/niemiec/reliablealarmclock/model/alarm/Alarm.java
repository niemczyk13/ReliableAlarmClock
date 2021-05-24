package com.niemiec.reliablealarmclock.model.alarm;

import java.sql.Time;

public class Alarm {
    private Long id;
    private String name;
    private AlarmDateTime alarmDateTime;
    private int batteryPercentage;
    private Time timeToDischarge;
    private boolean vibration;
    private Volume volume;
    private IncreaseVolume increaseVolume;
    private boolean active;

    /*
    Long getId();
    void setId(Long id);
    Object getAlarmClock();
    void setAlarmClock(Object alarmClock);
    int getBatteryPercentage();
    void setBatteryPercentage(int batteryPercentage);
    Object getTimeToDischarge();
    void setTimeToDischarge(Object timeToDischarge);
    boolean getActivated();
    void setActivated(boolean activated);
     */
}
