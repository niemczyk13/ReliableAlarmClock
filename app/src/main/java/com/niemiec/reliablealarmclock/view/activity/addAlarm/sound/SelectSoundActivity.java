package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.data.DefaultValues;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.MyFileActivity;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.MySoundsActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SelectSoundActivity extends AppCompatActivity implements SelectSoundContractMVP.View {

    private SelectSoundPresenter presenter;
    private AlarmSoundData data = new AlarmSoundData(DefaultValues.SOUND_POSITION.value());
    private SelectSoundAdapter adapter;
    private MediaPlayer mediaPlayer;
    @BindView(R.id.file_list_view)
    ListView fileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sound);
        ButterKnife.bind(this);

        addBackArrow();


        createSelectSoundPresenter();
        //TODO
        adapter = new SelectSoundAdapter(this, data);
        fileListView.setAdapter(adapter);


        fileListView.setOnItemClickListener((adapterView, view, position, l) -> {
            presenter.itemClick(position);
        });



        //TODO Dopisanie nowych melodii i lista i wyyberanie i powrót do AddAlarmActivity
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
        presenter = new SelectSoundPresenter(this, data);
        presenter.attach(this);
    }

    @OnClick(R.id.add_new_sound_text_view)
    public void addNewSoundTextViewClick(View view) {
        Intent intent = new Intent(this, MySoundsActivity.class);
        startActivityForResult(intent, 1);
    }

    //TODO to co zwraca wywołana aktywność
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (data != null) {
                    String uri = data.getStringExtra("uri");
                    Intent intent = new Intent();
                    intent.putExtra("uri", uri);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void updateListView() {
        adapter.notifyDataSetChanged();
    }
}