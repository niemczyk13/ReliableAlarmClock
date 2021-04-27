package com.niemiec.reliablealarmclock.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.niemiec.reliablealarmclock.AddAlarmContractMVP;
import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.add.alarm.AddAlarmManager;
import com.niemiec.reliablealarmclock.add.alarm.ActualTime;
import com.niemiec.reliablealarmclock.data.AlarmSound;
import com.niemiec.reliablealarmclock.data.EarlyActivationButtons;

public class AddAlarmActivity extends AppCompatActivity implements AddAlarmContractMVP.View {
    public static final int DEFAULT_PRECENT_VALUE = 5;
    public static final String SOUND_PATH = "";

    private AddAlarmManager addAlarmManager;

    @BindView(R.id.hour_edit_text) EditText hour;
    @BindView(R.id.minute_edit_text) EditText minute;
    @BindView(R.id.calendar_image_button) ImageButton calendar;

    @BindView(R.id.day_1_button) MaterialButton day1;
    @BindView(R.id.day_2_button) MaterialButton day2;
    @BindView(R.id.day_3_button) MaterialButton day3;
    @BindView(R.id.day_4_button) MaterialButton day4;
    @BindView(R.id.day_5_button) MaterialButton day5;
    @BindView(R.id.day_6_button) MaterialButton day6;
    @BindView(R.id.day_7_button) MaterialButton day7;

    @BindView(R.id.percent_time_radio_group) RadioGroup radioButtonGroup;
    @BindView(R.id.nothing_choice_button) RadioButton nothingChoiceButton;
    @BindView(R.id.percent_choice_button) RadioButton percentChoiceButton;
    @BindView(R.id.time_choice_button) RadioButton timeChoiceButton;

    @BindView(R.id.percent_or_time_edit_text) EditText percentOrTime;
    @BindView(R.id.sound_path_edit_text) EditText soundPath;

    @BindView(R.id.alarm_volume_seek_bar) SeekBar alarmVolume;

    @BindView(R.id.vibration_switch) Switch vibration;

    @BindView(R.id.rising_sound_switch) Switch risingSoundSwitch;
    @BindView(R.id.rising_sound_edit_text) EditText risingSoundText;

    @BindView(R.id.cancel_button) Button cancel;
    @BindView(R.id.save_button) Button save;

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
        EarlyActivationButtons.setDefaultValue(percentChoiceButton, percentOrTime, DEFAULT_PRECENT_VALUE);
        AlarmSound.setDefaultSound(soundPath, SOUND_PATH);
    }

    private void activatedHourEditText() {
        hour.selectAll();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @OnClick({R.id.hour_edit_text, R.id.minute_edit_text, R.id.percent_or_time_edit_text, R.id.rising_sound_edit_text})
    public void selectAllInEditText(View view) {
        EditText et = findViewById(view.getId());
        et.selectAll();
    }

    @OnClick(R.id.calendar_image_button )
    public void calendarImageButtonClick(View view) {
    }

    @OnClick({R.id.day_1_button, R.id.day_2_button, R.id.day_3_button, R.id.day_4_button,
        R.id.day_5_button, R.id.day_6_button, R.id.day_7_button})
    public void dayButtonClick(View view) {
        Toast.makeText(getApplicationContext(), "Test " + view.getId(), Toast.LENGTH_LONG).show();

    }

    @OnClick({R.id.nothing_choice_button, R.id.percent_choice_button, R.id.time_choice_button})
    public void onPercentOrTimeRadioGroupClick(View view) {
        addAlarmManager.onRadioButtonClicked();
    }

    @OnClick(R.id.cancel_button)
    public void cancelAddAlarm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.save_button)
    public void saveAlarm(View view) {
        addAlarmManager.saveAlarm();
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
        return percentOrTime.getText().toString();
    }

    @Override
    public int getCheckedRadioButtonId() {
        return radioButtonGroup.getCheckedRadioButtonId();
    }

    @Override
    public void showHour(String hour) {
        this.hour.setText(hour);
    }

    @Override
    public void showMinute(String minute) {
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