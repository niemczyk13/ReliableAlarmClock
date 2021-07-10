package com.niemiec.reliablealarmclock.data;

public enum DefaultValues {
    SOUND_POSITION(1),
    PERCENT(5),
    MAX_VOLUME(100),
    VOLUME(80);

    private final int value;

    DefaultValues(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
