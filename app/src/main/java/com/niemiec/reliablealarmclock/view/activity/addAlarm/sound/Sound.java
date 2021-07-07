package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import lombok.Getter;
import lombok.Setter;

public class Sound {
    private boolean checked = false;
    private String name;
    private int id;

    public Sound(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
