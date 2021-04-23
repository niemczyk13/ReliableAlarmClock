package com.niemiec.reliablealarmclock.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.niemiec.reliablealarmclock.AddAlarmContractMVP;
import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.add.alarm.AddAlarmManager;
import com.niemiec.reliablealarmclock.add.alarm.ActualTime;
import com.niemiec.reliablealarmclock.data.AlarmSound;
import com.niemiec.reliablealarmclock.data.EarlyActivationButtons;
import com.niemiec.reliablealarmclock.validator.HourValidator;
import com.niemiec.reliablealarmclock.validator.MinuteValidator;

import java.time.LocalDateTime;

public class AddAlarmActivity extends AppCompatActivity implements AddAlarmContractMVP.View {
    public static final int DEFAULT_PRECENT_VALUE = 5;
    public static final String SOUND_PATH = "";

    private AddAlarmManager addAlarmManager;

    @BindView(R.id.hours_value) EditText hour;
    @BindView(R.id.minutes_value) EditText minute;
    @BindView(R.id.radio_group) RadioGroup radioButtonGroup;
    @BindView(R.id.nothing_choice_button) RadioButton nothingChoiceButton;
    @BindView(R.id.precent_choice_button) RadioButton precentChoiceButton;
    @BindView(R.id.time_choice_button) RadioButton timeChoiceButton;
    @BindView(R.id.precent_or_time_value) EditText precentOrTime;
    @BindView(R.id.sound_path) EditText soundPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);
        createAddAlarmManager();
        setDefaultValues();
        activatedHourEditText();
        addHourViewTextChangedListener();
        addMinutesViewTextChangedListener();
    }

    private void addMinutesViewTextChangedListener() {
        minute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (minute.hasFocus()) {
                    addAlarmManager.checkTheCorrectnessOfTheEnteredMinute();
                }
            }
        });
    }

    private void addHourViewTextChangedListener() {
        hour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if (hour.hasFocus()) {
                    addAlarmManager.checkTheCorrectnessOfTheEnteredHour();
                }
            }
        });
    }

    private void createAddAlarmManager() {
        addAlarmManager = new AddAlarmManager();
        addAlarmManager.attach(this);
    }

    //TODO - dopisać dodawanie aktualnej daty
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
        addAlarmManager.saveAlarm();
    }

    public void onRadioButtonClicked(View view) {
        addAlarmManager.onRadioButtonClicked();
    }

    public void alarmEditTextClick(View view) {
        EditText et = findViewById(view.getId());
        et.selectAll();
    }

    @Override
    public String getHour() {
        return hour.getText().toString();
    }

    @Override
    public String getMinute() {
        return minute.getText().toString();
    }

    @Override
    public String getPrecentOrTimeValue() {
        return precentOrTime.getText().toString();
    }

    @Override
    public int getCheckedRadioButtonId() {
        return radioButtonGroup.getCheckedRadioButtonId();
    }

    @Override
    public void setHour(String hour) {
        this.hour.setText(hour);
    }

    @Override
    public void setMinute(String minute) {
        this.minute.setText(minute);
    }

    @Override
    public void setNextFocusAfterHour() {
        hour.setNextFocusRightId(minute.getId());
    }

    @Override
    public void transferActivityToMinutes() {
        hour.focusSearch(View.FOCUS_RIGHT).requestFocus(); //przeniesienie aktywności na minuty
    }

    @Override
    public void selectAllMinute() {
        minute.selectAll();
    }

    @Override
    public void setHourSelection(int position) {
        hour.setSelection(position);
    }
}