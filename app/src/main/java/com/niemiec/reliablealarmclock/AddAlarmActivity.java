package com.niemiec.reliablealarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddAlarmActivity extends AppCompatActivity {
    private String clock;
    //private EditText clock;
    private int radioButtonId = 0;
    //private boolean precentOrTime;
    private int precent = 0;
    private int time = 0;
    //private EditText precentOrTimeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
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
        EditText alarmValue = findViewById(R.id.alarmValue);
        clock = alarmValue.getText().toString();
        RadioButton radioButton;
        switch (radioButtonId) {
            case R.id.precent_choice_button:
                radioButton = findViewById(R.id.precent_choice_button);
                precent = Integer.parseInt((String) radioButton.getText());
                break;
            case R.id.time_choice_button:
                radioButton = findViewById(R.id.time_choice_button);
                time = Integer.parseInt((String) radioButton.getText());
                break;
        }

        TextView textView = findViewById(R.id.melody_choice);
        textView.setText(clock);
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioButtonId = radioGroup.getCheckedRadioButtonId();
        //dodania odpowiednich konfiguracji to EditText - jak procenty to tylko wartości od 0 do 100, a jak czas to czas w minutach
    }
}