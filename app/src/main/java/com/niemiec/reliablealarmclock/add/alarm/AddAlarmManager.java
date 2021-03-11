package com.niemiec.reliablealarmclock.add.alarm;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.niemiec.reliablealarmclock.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class AddAlarmManager {
    private String dateFormat = "yyyy-MM-dd'T'HH:mm";

    private EditText hour;
    private EditText minute;
    private RadioGroup radioButtonGroup;
    private RadioButton nothingChoiceButton;
    private RadioButton precentChoiceButton;
    private RadioButton timeChoiceButton;
    private EditText precentOrTime;
    private EditText soundPath;

    //musi być data, żeby alarm włączył się tylko raz
    private LocalDateTime alarmClock;

    private int radioButtonId;

    private AddAlarmManager(Builder builder) {
        this.hour = builder.hour;
        this.minute = builder.minute;
        this.nothingChoiceButton = builder.nothingChoiceButton;
        this.precentChoiceButton = builder.precentChoiceButton;
        this.timeChoiceButton = builder.timeChoiceButton;
        this.precentOrTime = builder.precentOrTime;
        this.soundPath = builder.soundPath;
    }

    // TODO
    public void saveAlarm(View view) {
        //pobranie alarmu
        getAlarmClock();
        //procente czy czas
        //pobrane wartości procentów lub czasu
        //pobranie dźwięku alarm
    }

    private void getAlarmClock() {
        String h = hour.getText().toString();
        String m = minute.getText().toString();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime clock = LocalTime.of(Integer.parseInt(h), Integer.parseInt(m));
            LocalDate date = LocalDate.now();
            if (clock.isBefore(LocalTime.now())) {
                date = date.plusDays(1L);
            }
            alarmClock = LocalDateTime.of(date, clock);
            precentOrTime.setText(alarmClock.toString());
        } else {
            Date date = new Date();
        }
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat(dateFormat);
        //precentOrTime.setText(f.format(date).toString());
    }

    private void getRadioButtonValue() {
        int precent;
        int time;
        switch (radioButtonId) {
            case R.id.precent_choice_button:
                precent = Integer.parseInt(precentOrTime.getText().toString());
                break;
            case R.id.time_choice_button:
                time = Integer.parseInt(precentOrTime.getText().toString());
                break;
        }
    }

    // TODO
    public void onRadioButtonClicked(View view) {
        radioButtonId = radioButtonGroup.getCheckedRadioButtonId();
        //dodania odpowiednich konfiguracji to EditText - jak procenty to tylko wartości od 0 do 100, a jak czas to czas w minutach
    }

    public static class Builder {
        private EditText hour;
        private EditText minute;
        private RadioGroup radioButtonGroup;
        private RadioButton nothingChoiceButton = null;
        private RadioButton precentChoiceButton = null;
        private RadioButton timeChoiceButton = null;
        private EditText precentOrTime = null;
        private EditText soundPath = null;

        public Builder(EditText hour, EditText minute) {
            this.hour = hour; this.minute = minute;
        }

        public Builder radioButtonGroup(RadioGroup radioButtonGroup) {
            this.radioButtonGroup = radioButtonGroup; return this;
        }

        public Builder nothingChoiceButton(RadioButton nothingChoiceButton) {
            this.nothingChoiceButton = nothingChoiceButton; return this;
        }

        public Builder precentChoiceBuilder(RadioButton precentChoiceButton) {
            this.precentChoiceButton = precentChoiceButton; return this;
        }

        public Builder timeChoiceButton(RadioButton timeChoiceButton) {
            this.timeChoiceButton = timeChoiceButton; return this;
        }

        public Builder precentOrTime(EditText precentOrTime) {
            this.precentOrTime = precentOrTime; return this;
        }

        public Builder soundPath(EditText soundPath) {
            this.soundPath = soundPath; return this;
        }

        public AddAlarmManager build() {
            return new AddAlarmManager(this);
        }
    }


}
