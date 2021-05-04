package com.niemiec.reliablealarmclock;

import java.time.LocalDateTime;

public interface AddAlarmContractMVP {
    interface View {
        String getHour();
        String getMinute();
        String getPercentOrTimeValue();
        int getCheckedRadioButtonId();

        void showHour(String hour);
        void showMinute(String minute);
        void setNextFocusAfterHour();
        void setHourSelection(int position);
        void checkedPercent();
        void setThePercentageOrTimeToDischarge(int value);
        void showSoundPath(String path);
        void setMaxVolume(int maxVolume);
        void setVolume(int volume);
        void setVibration(boolean value);
        void setRisingVolume(boolean risingVolume);

        void transferActivityToMinutes();
        void selectAllMinute();
    }

     interface Presenter {
        void saveAlarm();
        void onRadioButtonClicked();
        void checkTheCorrectnessOfTheEnteredHour();
        void checkTheCorrectnessOfTheEnteredMinute();
        void getActualTime();
        void getDischargeDefaultValue();
        void getDefaultSound();
        void getDefaultVolume();
        void getDefaultVibrationValue();
        void getDefaultRisingVolume();
     }
}
