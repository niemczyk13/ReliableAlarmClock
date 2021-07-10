package com.niemiec.reliablealarmclock.view.activity.addAlarm;

import com.niemiec.reliablealarmclock.view.activity.BasePresenter;
import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.add.alarm.ActualTime;
import com.niemiec.reliablealarmclock.data.DefaultStringValues;
import com.niemiec.reliablealarmclock.data.DefaultValues;
import com.niemiec.reliablealarmclock.data.DefaultSwitchValues;
import com.niemiec.reliablealarmclock.model.alarm.Alarm;
import com.niemiec.reliablealarmclock.validator.HourValidator;
import com.niemiec.reliablealarmclock.validator.MinuteValidator;

public class AddAlarmPresenter extends BasePresenter<AddAlarmContractMVP.View> implements AddAlarmContractMVP.Presenter {
    private String dateFormat = "yyyy-MM-dd'T'HH:mm";

    //musi być data, żeby alarm włączył się tylko raz
    private Alarm alarm;

    private int radioButtonId = -1;

    public AddAlarmPresenter() {
        alarm = new Alarm();
    }

    // TODO
    @Override
    public void saveAlarm() {
        //get clock
        //get schedule or date

        //pobranie wszystkich danych i walidacja
        //alarm validate itd.

        //zapisanie ich w alarmie
        //pobranie alarmu
        //setAlarmClock();
        //procente czy czas
        //getRadioButtonValue();
        //pobrane wartości procentów lub czasu
        //pobranie dźwięku alarm
        int percent = view.getCheckedPercentOrTimeId();
    }

    // TODO - NIE MAM SIĘ ODNOSIC DO HOUR MINUTE ITP. TYLKO DO AKTYWNOSCI I WYWOLYWAC ODPOWIEDNIE METODY
    private void setAlarmClock() {
        String h = view.getHour();
        String m = view.getMinute();
/*
        alarm.setAlarmDataClock();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime clock = LocalTime.of(Integer.parseInt(h), Integer.parseInt(m));
            LocalDate date = LocalDate.now();
            if (clock.isBefore(LocalTime.now())) {
                date = date.plusDays(1L);
            }
            alarm.setAlarmClock(LocalDateTime.of(date, clock));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
            calendar.set(Calendar.MINUTE, Integer.parseInt(m));
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            Date date = calendar.getTime();
            alarm.setAlarmClock(date);
        }


 */
    }


    private void getRadioButtonValue() {
        int precent;
        int time;
        switch (radioButtonId) {
            case R.id.percent_choice_button:
                precent = Integer.parseInt(view.getPercentOrTimeValue());
                setBatteryPrecetange();
                break;
            case R.id.time_choice_button:
                time = Integer.parseInt(view.getPercentOrTimeValue());
                setTimeToDischarge();
                break;
            default:
                precent = 0;
                time = 0;
        }
    }

    // TODO
    private void setBatteryPrecetange() {

    }

    // TODO
    private void setTimeToDischarge() {
        // jeżeli > 0 to duration, inaczej Time
        int time = Integer.parseInt(view.getPercentOrTimeValue());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        //    alarm.setTimeToDischarge(Time.valueOf(Integer.toString(time )));
        }
    }

    // TODO W ZALEŻNOSCI CO KLIKNIE TO AKTYWUJE LUB ZMIENIA WARTOSC DOMYSLNA W OKNIE
    @Override
    public void onPercentOrTimeRadioGroupClick(int id) {
        //radioButtonId = view.getCheckedRadioButtonId();
        //dodania odpowiednich konfiguracji to EditText - jak procenty to tylko wartości od 0 do 100, a jak czas to czas w minutach
        if (id == R.id.nothing_choice_button) {
            //TODO jeżeli nothing kliknięte to ukrywamy wprowadzanie wartości
            view.hidePercentOrTimeEditText(false);
            view.setPercentOrTimeGroupCheck(true, false, false);
        }
        if (id == R.id.percent_choice_button) {
            view.hidePercentOrTimeEditText(true);
            view.setPercentOrTimeGroupCheck(false, true, false);
        }
        if (id == R.id.time_choice_button) {
            view.hidePercentOrTimeEditText(true);
            view.setPercentOrTimeGroupCheck(false, false, true);
        }
    }


    public void checkTheCorrectnessOfTheEnteredHour() {
        //TODO

        String hour = view.getHour();
        String result = HourValidator.checkTheCorrectnessOfTheEnteredHour(hour);

        if (!hour.equals(result))
            view.showHour(result);

        if (result.length() == 1) {
            int first = Integer.parseInt(result.substring(0, 1));
            int position = Integer.toString(first).length();
            view.setHourSelection(position);
        } else if (result.length() == 2){
            view.setNextFocusAfterHour();
            view.transferActivityToMinutes();
            view.selectAllMinute();
        }


    }

    @Override
    public void hourFocusChange(boolean hasFocus) {
        if (!hasFocus) {
            String hour = view.getHour();
            if (hour.isEmpty()) {
                hour = ActualTime.getActualHour();
                view.showHour(hour);
                return;
            }

            String result = HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(hour);

            if (!hour.equals(result)) {
                view.showHour(result);
            }
        }
    }

    @Override
    public void checkTheCorrectnessOfTheEnteredMinute() {
        String minute = view.getMinute();
        String result = MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(minute);

        if (minute != result)
            view.showMinute(result);

        if (result.length() == 1) {
            int first = Integer.parseInt(result.substring(0, 1));
            int position = Integer.toString(first).length();
            view.setMinuteSelection(position);
        } else {
            view.selectMinute();
        }
    }

    @Override
    public void minuteFocusChange(boolean hasFocus) {
        if (!hasFocus) {
            String minute = view.getMinute();
            if(minute.isEmpty()) {
                minute = ActualTime.getActualMinute();
                view.showMinute(minute);
                return;
            }

            String result = MinuteValidator.checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocus(minute);

            if(!minute.equals(result)) {
                view.showMinute(result);
            }
        }
    }


    @Override
    public void getActualTime() {
        String hour = ActualTime.getActualHour();
        String minute = ActualTime.getActualMinute();
        view.showHour(hour);
        view.showMinute(minute);
    }

    //TODO
    @Override
    public void getDischargeDefaultValue() {
        view.checkedPercent();
        view.setThePercentageOrTimeToDischarge(DefaultValues.PERCENT.value());
    }

    @Override
    public void getDefaultSound() {
        //TODO - pobierany z menu i z bazy danych
        view.showSoundPath(DefaultStringValues.SOUND_PATH.value());
    }

    @Override
    public void getDefaultVolume() {
        view.setMaxVolume(DefaultValues.MAX_VOLUME.value());
        view.setVolume(DefaultValues.VOLUME.value());
    }

    @Override
    public void getDefaultVibrationValue() {
        view.setVibration(DefaultSwitchValues.VIBRATION.value());
    }

    @Override
    public void getDefaultRisingVolume() {
        view.setRisingVolume(DefaultSwitchValues.RISING_VOLUME.value());
    }

    @Override
    public void activatedHourEditText() {
        view.selectHour();
    }
}
