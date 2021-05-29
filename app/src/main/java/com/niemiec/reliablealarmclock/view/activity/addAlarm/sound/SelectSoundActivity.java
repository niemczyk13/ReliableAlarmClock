package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.MySoundsActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SelectSoundActivity extends AppCompatActivity implements SelectSoundContractMVP.View {

    private SelectSoundPresenter presenter;
    @BindView(R.id.file_list_view)
    ListView fileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sound);
        ButterKnife.bind(this);

        addBackArrow();


        createSelectSoundPresenter();


    }

    private void addBackArrow() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return true;
    }

    private void createSelectSoundPresenter() {
        presenter = new SelectSoundPresenter();
        presenter.attach(this);
    }

    @OnClick(R.id.add_new_sound_text_view)
    public void addNewSoundTextViewClick(View view) {
        Intent intent = new Intent(this, MySoundsActivity.class);
        startActivity(intent);
    }
}