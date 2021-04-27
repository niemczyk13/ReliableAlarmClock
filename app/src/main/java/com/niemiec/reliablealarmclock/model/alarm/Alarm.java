package com.niemiec.reliablealarmclock.model.alarm;

import java.sql.Time;

public class Alarm {
    private Long id;
    private AlarmDateTime alarmDateTime;
    private int batteryPrecentage;
    private Time timeToDischarge;
    private boolean vibration;
    private Volume volume;
    private IncreaseVolume increaseVolume;
    boolean active;

    /*
    Long getId();
    void setId(Long id);
    Object getAlarmClock();
    void setAlarmClock(Object alarmClock);
    int getBatteryPrecentage();
    void setBatteryPrecentage(int batteryPrecentage);
    Object getTimeToDischarge();
    void setTimeToDischarge(Object timeToDischarge);
    boolean getActivated();
    void setActivated(boolean activated);
     */
}
