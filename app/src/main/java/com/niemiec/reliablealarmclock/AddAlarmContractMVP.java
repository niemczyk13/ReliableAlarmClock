package com.niemiec.reliablealarmclock;

import java.time.LocalDateTime;

public interface AddAlarmContractMVP {
    interface View {
        String getHour();
        String getMinute();
        String getPrecentOrTimeValue();
        int getCheckedRadioButtonId();

        void setHour(String hour);
        void setMinute(String minute);
        void setNextFocusAfterHour();
        void setHourSelection(int position);

        void transferActivityToMinutes();
        void selectAllMinute();
    }

     interface Presenter {
        void saveAlarm();
        void onRadioButtonClicked();
        void checkTheCorrectnessOfTheEnteredHour();
        void checkTheCorrectnessOfTheEnteredMinute();
     }
}
