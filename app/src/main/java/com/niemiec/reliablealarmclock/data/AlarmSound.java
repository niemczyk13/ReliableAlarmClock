package com.niemiec.reliablealarmclock.data;

import android.widget.EditText;

public class AlarmSound {
    private static EditText soundPath;

    public static void setDefaultSound(EditText soundPath, String path) {
        soundPath.setText(path);
    }
}
