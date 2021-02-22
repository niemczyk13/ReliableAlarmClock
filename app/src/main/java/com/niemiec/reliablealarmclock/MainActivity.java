package com.niemiec.reliablealarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        final FloatingActionButton addAlarmButton = findViewById(R.id.add_alarm_button);
        //JEŻELI PIERWSZY RAZ TO TWORZĘ BAZĘ DANYCH
        //POBIERAM Z BAZY LISTĘ ALARMÓW I WYŚWIETLAM
    }

    //TWORZENIE NOWEGO ALARMU --> PRZENOSI DO AKTYWNOŚCI ADDALARM
    public void addAlarm(View view) {
        ListView listView = findViewById(R.id.alarmsList);
        String n[] = new String[] {
                "nana"
        };
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, n));
        Intent intent = new Intent(this, AddAlarmActivity.class);
        startActivity(intent);
    }

    //USUWANIE ALARMÓW --> PRZYCISK KOSZA I MOŻLIWOŚĆ WYBORU I POJAWIJĄ SIĘ PRZYCISKI USUŃ I ANULUJ
}