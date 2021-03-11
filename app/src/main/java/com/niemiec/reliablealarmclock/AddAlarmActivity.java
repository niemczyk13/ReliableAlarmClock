package com.niemiec.reliablealarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.niemiec.reliablealarmclock.add.alarm.AddAlarmManager;
import com.niemiec.reliablealarmclock.add.alarm.ActualTime;
import com.niemiec.reliablealarmclock.data.AlarmSound;
import com.niemiec.reliablealarmclock.data.EarlyActivationButtons;
import com.niemiec.reliablealarmclock.validator.HourValidator;
import com.niemiec.reliablealarmclock.validator.MinuteValidator;

public class AddAlarmActivity extends AppCompatActivity {
    public static final int DEFAULT_PRECENT_VALUE = 5;
    public static final String SOUND_PATH = "";

    private AddAlarmManager addAlarmManager;

    private EditText hour;
    private EditText minute;
    private RadioGroup radioButtonGroup;
    private RadioButton nothingChoiceButton;
    private RadioButton precentChoiceButton;
    private RadioButton timeChoiceButton;
    private EditText precentOrTime;
    private EditText soundPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        getViews();
        createAddAlarmManager();
        setDefaultValues();
        activatedHourEditText();
        HourValidator.addHourViewTextChangedListener(hour, minute);
        MinuteValidator.addMinutesViewTextChangedListener(minute);
    }

    private void getViews() {
        hour = findViewById(R.id.hours_value);
        minute = findViewById(R.id.minutes_value);
        radioButtonGroup = findViewById(R.id.radio_group);
        nothingChoiceButton = findViewById(R.id.nothing_choice_button);
        precentChoiceButton = findViewById(R.id.precent_choice_button);
        timeChoiceButton = findViewById(R.id.time_choice_button);
        precentOrTime = findViewById(R.id.precent_or_time_value);
        soundPath = findViewById(R.id.sound_path);
    }

    private void createAddAlarmManager() {
        addAlarmManager = new AddAlarmManager.Builder(hour, minute)
                .radioButtonGroup(radioButtonGroup)
                .nothingChoiceButton(nothingChoiceButton)
                .precentChoiceBuilder(precentChoiceButton)
                .timeChoiceButton(timeChoiceButton)
                .precentOrTime(precentOrTime)
                .soundPath(soundPath)
                .build();
    }

    private void setDefaultValues() {
        ActualTime.setActualTime(hour, minute);
        EarlyActivationButtons.setDefaultValue(precentChoiceButton, precentOrTime, DEFAULT_PRECENT_VALUE);
        AlarmSound.setDefaultSound(soundPath, SOUND_PATH);
    }

    private void activatedHourEditText() {
        hour.selectAll();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void cancelAddAlarm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveAlarm(View view) {
        addAlarmManager.saveAlarm(view);
    }

    public void onRadioButtonClicked(View view) {
        addAlarmManager.onRadioButtonClicked(view);
    }

    public void alarmEditTextClick(View view) {
        EditText et = findViewById(view.getId());
        et.selectAll();
    }
}