package com.niemiec.reliablealarmclock.data;

public enum DefaultSwitchValues {
    VIBRATION(OnOffValues.ON.value()),
    RISING_VOLUME(OnOffValues.OFF.value());

    private final boolean value;

    DefaultSwitchValues(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }
}
