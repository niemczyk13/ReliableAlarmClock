package com.niemiec.reliablealarmclock.view.activity.addAlarm;


public interface AddAlarmContractMVP {
    interface View {
        String getHour();
        String getMinute();
        String getPercentOrTimeValue();
        int getCheckedPercentOrTimeId();

        void showHour(String hour);
        void showMinute(String minute);
        void setNextFocusAfterHour();
        void setHourSelection(int position);
        void setMinuteSelection(int position);
        void checkedPercent();
        void setThePercentageOrTimeToDischarge(int value);
        void showSoundPath(String path);
        void setMaxVolume(int maxVolume);
        void setVolume(int volume);
        void setVibration(boolean value);
        void setRisingVolume(boolean risingVolume);
        void selectHour();
        void selectMinute();
        void setPercentOrTimeGroupCheck(boolean nothing, boolean percent, boolean time);
        void hidePercentOrTimeEditText(boolean hide);
        void transferActivityToMinutes();
        void selectAllMinute();
    }

     interface Presenter {
        void saveAlarm();
        void onPercentOrTimeRadioGroupClick(int id);
        void checkTheCorrectnessOfTheEnteredHour();
        void hourFocusChange(boolean hasFocus);
        void checkTheCorrectnessOfTheEnteredMinute();
        void minuteFocusChange(boolean hasFocus);
        void getActualTime();
        void getDischargeDefaultValue();
        void getDefaultSound();
        void getDefaultVolume();
        void getDefaultVibrationValue();
        void getDefaultRisingVolume();
        void activatedHourEditText();
     }
}
