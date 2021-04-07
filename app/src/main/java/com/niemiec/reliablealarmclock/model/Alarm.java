package com.niemiec.reliablealarmclock.model;

public interface Alarm {
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
}
