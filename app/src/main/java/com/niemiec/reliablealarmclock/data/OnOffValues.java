package com.niemiec.reliablealarmclock.data;

public enum OnOffValues {
    ON(true),
    OFF(false);

    private final boolean value;

    OnOffValues(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }
}
