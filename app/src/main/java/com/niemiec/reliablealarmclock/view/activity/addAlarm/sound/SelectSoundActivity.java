package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.MenuItem;

import com.niemiec.reliablealarmclock.R;

public class SelectSoundActivity extends AppCompatActivity implements SelectSoundContractMVP.View {

    private SelectSoundPresenter presenter;

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
}