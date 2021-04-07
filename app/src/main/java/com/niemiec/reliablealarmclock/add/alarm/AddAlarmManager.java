package com.niemiec.reliablealarmclock.add.alarm;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.niemiec.reliablealarmclock.AddAlarmContractMVP;
import com.niemiec.reliablealarmclock.BasePresenter;
import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.model.Alarm;
import com.niemiec.reliablealarmclock.model.Alarm21;
import com.niemiec.reliablealarmclock.model.AlarmBefore21;
import com.niemiec.reliablealarmclock.validator.HourValidator;
import com.niemiec.reliablealarmclock.validator.MinuteValidator;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class AddAlarmManager extends BasePresenter<AddAlarmContractMVP.View> implements AddAlarmContractMVP.Presenter {
    private String dateFormat = "yyyy-MM-dd'T'HH:mm";

    //musi być data, żeby alarm włączył się tylko raz
    private Alarm alarm;

    private int radioButtonId = -1;

    public AddAlarmManager() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            alarm = new Alarm21();
        } else {
            alarm = new AlarmBefore21();
        }
    }
    // TODO
    @Override
    public void saveAlarm() {
        //pobranie alarmu
        getAlarmClock();
        //procente czy czas
        getRadioButtonValue();
        //pobrane wartości procentów lub czasu
        //pobranie dźwięku alarm
    }

    // TODO - NIE MAM SIĘ ODNOSIC DO HOUR MINUTE ITP. TYLKO DO AKTYWNOSCI I WYWOLYWAC ODPOWIEDNIE METODY
    private void getAlarmClock() {
        String h = view.getHour();
        String m = view.getMinute();

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
    }


    private void getRadioButtonValue() {
        int precent;
        int time;
        switch (radioButtonId) {
            case R.id.precent_choice_button:
                precent = Integer.parseInt(view.getPrecentOrTimeValue());
                setBatteryPrecetange();
                break;
            case R.id.time_choice_button:
                time = Integer.parseInt(view.getPrecentOrTimeValue());
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
        int time = Integer.parseInt(view.getPrecentOrTimeValue());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            alarm.setTimeToDischarge(Time.valueOf(Integer.toString(time )));
        }
    }

    // TODO W ZALEŻNOSCI CO KLIKNIE TO AKTYWUJE LUB ZMIENIA WARTOSC DOMYSLNA W OKNIE
    @Override
    public void onRadioButtonClicked() {
        radioButtonId = view.getCheckedRadioButtonId();
        //dodania odpowiednich konfiguracji to EditText - jak procenty to tylko wartości od 0 do 100, a jak czas to czas w minutach
    }


    public void checkTheCorrectnessOfTheEnteredHour() {
        HourValidator.checkTheCorrectnessOfTheEnteredHour(view);
    }

    @Override
    public void checkTheCorrectnessOfTheEnteredMinute() {
        MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(view);
    }
}
