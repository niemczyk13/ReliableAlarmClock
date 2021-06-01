package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file;

import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
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
    private List<File> files = new ArrayList<>();
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

        String m_root = Environment.getExternalStorageDirectory().getPath();
        String p = Environment.getExternalStorageDirectory().getAbsolutePath();

        path = "/";
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }
        setTitle(path);

        List values = new ArrayList();
        File dir = new File(p);
        File[] files33 = dir.listFiles();

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

        File file = files33[8];
        File[] files2 = file.listFiles();

        Collections.sort(values);

        AssetManager mgr = getAssets();

        try {
            String list2[] = mgr.list(m_root);
            for (int i = 0; i < list2.length; i++) {
                System.out.println(list2[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getExternalFilesDir(Environment.DIRECTORY_MUSIC);

        getFiles(files33);

        System.out.println("Sciezka " + dir.getName() + " " + dir.getAbsolutePath());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, files);
        filesListView.setAdapter(adapter);
    }

    private void getFiles(File[] f) {
        List<File> dir = new ArrayList<>();
        List<File> fil = new ArrayList<>();

        System.out.println("JESTEM TU!!!!!!!!!!!!!!! " + files.size());
        if (f.length != 0) {

            for (File ff : f) {
                int index = ff.getAbsolutePath().lastIndexOf(".");
                String filePath = ff.getAbsolutePath();
                if (ff.isDirectory()) {
                    dir.add(ff);
                } else {
                    if (filePath.substring(index).equalsIgnoreCase(".mp3")) {
                        files.add(ff);
                    }
                    fil.add(ff);
                }
            }
            if (!dir.isEmpty()) {
                File[] abc = new File[dir.size()];

                getFiles(dir.toArray(abc));
            }

            for (File a : files) {
                System.out.print(a.getName() + ", ");
            }
            System.out.println();
        }
        System.out.println("JESTEM TU!");

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