package com.niemiec.reliablealarmclock.model;

import java.time.Duration;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alarm {
    private Long id;
    private LocalTime alarmClock;
    private int batteryPrecentage;
    private Duration timeToDischarge;
    private boolean activated;

    public static class Builder {
        //required parameters
        private LocalTime alarmClock;
        private boolean activated;
        //optional parameters
        private int batteryPrecentage = 0;
        private Duration timeToDischarge = null;

        public Builder(LocalTime alarmClock, boolean activated) {
            this.alarmClock = alarmClock;
            this.activated = activated;
        }

        public Builder batteryPrecentage(int batteryPrecentage) {
            this.batteryPrecentage = batteryPrecentage; return this;
        }
        public Builder timeToDischarge(Duration timeToDischarge) {
            this.timeToDischarge = timeToDischarge; return this;
        }

        public Alarm build() {
            return new Alarm(this);
        }

    }

    private Alarm(Builder builder) {
        this.alarmClock = builder.alarmClock;
        this.batteryPrecentage = builder.batteryPrecentage;
        this.timeToDischarge = builder.timeToDischarge;
        this.activated = builder.activated;
    }
}
