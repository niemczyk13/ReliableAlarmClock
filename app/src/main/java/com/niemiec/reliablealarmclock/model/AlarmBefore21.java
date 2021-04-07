package com.niemiec.reliablealarmclock.model;

import java.sql.Time;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmBefore21 implements Alarm{
    private Long id;
    private Date alarmClock;
    private int batteryPrecentage;
    private Time timeToDischarge;
    private boolean activated;

    public AlarmBefore21() {

    }

    @Override
    public void setAlarmClock(Object alarmClock) {
        this.alarmClock = (Date) alarmClock;
    }

    @Override
    public void setTimeToDischarge(Object timeToDischarge) {
        this.timeToDischarge = (Time) timeToDischarge;
    }

    @Override
    public boolean getActivated() {
        return activated;
    }

    public static class Builder {
        //required parameters
        private Date alarmClock;
        private boolean activated;
        //optional parameters
        private int batteryPrecentage = 0;
        private Time timeToDischarge = null;

        public Builder(Date  alarmClock, boolean activated) {
            this.alarmClock = alarmClock;
            this.activated = activated;
        }

        public Builder batteryPrecentage(int batteryPrecentage) {
            this.batteryPrecentage = batteryPrecentage; return this;
        }
        public Builder timeToDischarge(Time timeToDischarge) {
            this.timeToDischarge = timeToDischarge; return this;
        }

        public AlarmBefore21 build() {
            return new AlarmBefore21(this);
        }

    }

    public AlarmBefore21(Builder builder) {
        this.alarmClock = builder.alarmClock;
        this.batteryPrecentage = builder.batteryPrecentage;
        this.timeToDischarge = builder.timeToDischarge;
        this.activated = builder.activated;
    }
}
