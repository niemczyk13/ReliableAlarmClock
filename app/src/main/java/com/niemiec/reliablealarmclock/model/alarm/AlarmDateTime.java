package com.niemiec.reliablealarmclock.model.alarm;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.Date;

public class AlarmDateTime {
    private LocalDateTime dt26;
    private Date dt;
    private AlarmSchedule alarmSchedule;

    public void setDateTime(String dt) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dt26 = LocalDateTime.now();
        } else {
            
        }
    }
}
