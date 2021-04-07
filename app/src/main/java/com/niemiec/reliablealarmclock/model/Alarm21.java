package com.niemiec.reliablealarmclock.model;

import android.os.Build;

import java.time.Duration;
import java.time.LocalDateTime;

import androidx.annotation.RequiresApi;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RequiresApi(api = Build.VERSION_CODES.O)
public class Alarm21 implements  Alarm {
    private Long id;
    private LocalDateTime alarmClock;
    private int batteryPrecentage;
    private Duration timeToDischarge;
    private boolean activated;

    public Alarm21() {

    }

    @Override
    public void setAlarmClock(Object alarmClock) {
        this.alarmClock = (LocalDateTime) alarmClock;
    }

    @Override
    public void setTimeToDischarge(Object timeToDischarge) {
        this.timeToDischarge = (Duration) timeToDischarge;
    }

    @Override
    public boolean getActivated() {
        return activated;
    }

    public static class Builder {
        //required parameters
        private LocalDateTime alarmClock;
        private boolean activated;
        //optional parameters
        private int batteryPrecentage = 0;
        private Duration timeToDischarge = null;

        public Builder(LocalDateTime alarmClock, boolean activated) {
            this.alarmClock = alarmClock;
            this.activated = activated;
        }

        public Builder batteryPrecentage(int batteryPrecentage) {
            this.batteryPrecentage = batteryPrecentage; return this;
        }
        public Builder timeToDischarge(Duration timeToDischarge) {
            this.timeToDischarge = timeToDischarge; return this;
        }

        public Alarm21 build() {
            return new Alarm21(this);
        }

    }

    public Alarm21(Builder builder) {
        this.alarmClock = builder.alarmClock;
        this.batteryPrecentage = builder.batteryPrecentage;
        this.timeToDischarge = builder.timeToDischarge;
        this.activated = builder.activated;
    }
}
