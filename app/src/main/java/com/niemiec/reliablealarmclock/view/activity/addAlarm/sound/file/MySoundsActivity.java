package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file;

import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Environment;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.niemiec.reliablealarmclock.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySoundsActivity extends AppCompatActivity implements MySoundsContractMVP.View {

    private MySoundPresenter presenter;
    private String path;
    private List<File> allFiles = new ArrayList<>();
    private List<File> directories = new ArrayList<>();

    @BindView(R.id.sounds_list_view)
    ListView filesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sounds);
        ButterKnife.bind(this);

        addBackArrow();
        createMySoundsPresenter();

        showFirstFileManagerList();



    }

    private void showFirstFileManagerList() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String m_root = Environment.getExternalStorageDirectory().getPath();
        List values = new ArrayList();
        File dir = new File(path);
        File[] files33 = dir.listFiles();

        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
               // if (!file.startsWith(".")) {
               //     values.add(file);
               // }
            }
        }

        for (File f : files33) {
            values.add(f.getName());
        }

        Collections.sort(values);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, values);
        filesListView.setAdapter(adapter);
    }

    //TODO do dokończenia dopracowanie metody - kiedyś jako drugie rozwiązanie
    private void getFiles(File[] f) {
        List<File> dir = new ArrayList<>();
        List<File> fil = new ArrayList<>();

        if (f != null && f.length != 0) {

            for (File ff : f) {
                System.out.print(ff.getName() + ", ");
                if (!(ff.getName().equalsIgnoreCase( "Android"))) {
                    int index = ff.getAbsolutePath().lastIndexOf(".");
                    String filePath = ff.getAbsolutePath();
                    if (index == -1 || (ff.isDirectory() && !ff.getName().equalsIgnoreCase("date"))) {
                        dir.add(ff);
                    } else {
                            if (filePath.substring(index).equalsIgnoreCase(".mp3")) {
                        allFiles.add(ff);
                           }
                        fil.add(ff);
                    }
                }
            }
            if (!dir.isEmpty()) {

                for (File d : dir) {
                    getFiles(d.listFiles());
                }

            }

        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return true;
    }

    private void createMySoundsPresenter() {
        presenter = new MySoundPresenter();
        presenter.attach(this);
    }

    private void addBackArrow() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}