package com.niemiec.reliablealarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.niemiec.reliablealarmclock.data.ActualTime;
import com.niemiec.reliablealarmclock.validator.HourValidator;
import com.niemiec.reliablealarmclock.validator.MinuteValidator;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class AddAlarmActivity extends AppCompatActivity {
    private EditText hour;
    private EditText minute;
    private RadioGroup radioButtonsGroup;
    private EditText precentOrTime;
    private EditText soundPath;


    //chwilowe zmienne
    private String clock;
    //private EditText clock;
    private int radioButtonId = 0;
    //private boolean precentOrTime;
    private int precent = 0;
    private int time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        getViews();
        ActualTime.setActualTime(hour, minute);
        activatedHourEditText();
        HourValidator.addHourViewTextChangedListener(hour, minute);
        MinuteValidator.addMinutesViewTextChangedListener(minute);
    }

    private void activatedHourEditText() {
        hour.selectAll();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void getViews() {
        hour = findViewById(R.id.hours_value);
        minute = findViewById(R.id.minutes_value);
        radioButtonsGroup = findViewById(R.id.radio_group);
        precentOrTime = findViewById(R.id.precent_or_time_value);
        soundPath = findViewById(R.id.sound_path);
    }

    public void cancelAddAlarm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveAlarm(View view) {
        //pobranie wszystkich wpisywanych wartości
        getValues(view);
        //weryfikacja czy wprowadzone wartości są poprawne
    }

    private void getValues(View view) {
        EditText alarmValue = findViewById(R.id.hours_value);
        clock = alarmValue.getText().toString();
        getRadioButtonValue();
        EditText soundPathView = findViewById(R.id.sound_path);
    }

    private void getRadioButtonValue() {
        EditText precentOfTime;
        switch (radioButtonId) {
            case R.id.precent_choice_button:
                precentOfTime = findViewById(R.id.precent_or_time_value);
                precent = Integer.parseInt(precentOfTime.getText().toString());
                break;
            case R.id.time_choice_button:
                precentOfTime = findViewById(R.id.precent_or_time_value);
                time = Integer.parseInt(precentOfTime.getText().toString());
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioButtonId = radioGroup.getCheckedRadioButtonId();
        //dodania odpowiednich konfiguracji to EditText - jak procenty to tylko wartości od 0 do 100, a jak czas to czas w minutach
    }

    public void alarmEditTextClick(View view) {
        EditText et = findViewById(view.getId());
        et.selectAll();
    }
}