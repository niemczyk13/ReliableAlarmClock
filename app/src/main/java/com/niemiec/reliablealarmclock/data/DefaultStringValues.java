package com.niemiec.reliablealarmclock.data;

public enum DefaultStringValues {
    SOUND_PATH("abc/abc.mp3");

    private final String value;

    DefaultStringValues(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
