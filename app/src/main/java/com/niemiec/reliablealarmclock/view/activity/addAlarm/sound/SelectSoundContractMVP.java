package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import android.content.Context;

public interface SelectSoundContractMVP {
    interface View {
        void updateListView();
    }

    interface Presenter {
        void itemClick(int position);
    }
}
