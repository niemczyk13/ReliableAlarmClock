package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.data.DefaultValues;

import java.util.ArrayList;
import java.util.List;

public final class AlarmSoundData {
    private static AlarmSoundData instance;
    private static List<Sound> sounds;
    private static int markedPosition;

    private AlarmSoundData() {
        sounds = new ArrayList<>();
        sounds.add(new Sound("Pierwszy", R.raw.closer));
        sounds.add(new Sound("Drugi", R.raw.creep));

        markedPosition = DefaultValues.SOUND_POSITION.value();
        sounds.get(markedPosition).setChecked(true);
    }

    public static AlarmSoundData getInstance() {
        if (instance == null) {
            instance = new AlarmSoundData();
        }
        return instance;
    }

    public static int getSize() {
        return sounds.size();
    }

    public static Sound get(int position) {
        return sounds.get(position);
    }

    public static int getMarkedPosition() {
        return markedPosition;
    }

    public static void setMarkedPosition(int markedPosition) {
        AlarmSoundData.markedPosition = markedPosition;
    }
}
