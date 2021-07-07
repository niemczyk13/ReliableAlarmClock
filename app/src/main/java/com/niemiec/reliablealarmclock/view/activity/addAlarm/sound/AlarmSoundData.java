package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import com.niemiec.reliablealarmclock.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmSoundData {
    private List<Sound> sounds;

    public AlarmSoundData() {
        sounds = new ArrayList<>();
        sounds.add(new Sound("Pierwszy", R.raw.closer));
        sounds.add(new Sound("Drugi", R.raw.creep));
    }

    public int getSize() {
        return sounds.size();
    }

    public Sound get(int position) {
        return sounds.get(position);
    }
}
