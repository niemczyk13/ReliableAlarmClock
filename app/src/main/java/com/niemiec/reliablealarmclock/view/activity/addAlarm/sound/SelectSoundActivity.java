package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.niemiec.reliablealarmclock.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SelectSoundActivity extends AppCompatActivity implements SelectSoundContractMVP.View {

    private SelectSoundPresenter presenter;
    private String path;
    @BindView(R.id.file_list_view)
    ListView fileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sound);
        ButterKnife.bind(this);

        addBackArrow();


        createSelectSoundPresenter();

        String m_root = Environment.getExternalStorageDirectory().getPath();
        String p = Environment.getExternalStorageDirectory().getAbsolutePath();

        path = "/";
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }
        setTitle(path);

        List values = new ArrayList();
        File dir = new File(m_root);
        File[] files = dir.listFiles();

        if (!dir.canRead()) {
            setTitle(getTitle() + " (inaccessible)");
        }
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }

        Collections.sort(values);

        System.out.println("Sciezka " + dir);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, values);
        fileListView.setAdapter(adapter);
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