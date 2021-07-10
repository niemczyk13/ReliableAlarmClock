package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import android.content.Context;
import android.content.Intent;

public interface SelectSoundContractMVP {
    interface View {
        void updateListView();
        void setResultAndFinish(Intent intent);
    }

    interface Presenter {
        void itemClick(int position);
        void okButtonClick();
    }
}
