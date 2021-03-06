package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter.FileListAdapter;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter.MusicListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFileActivity extends AppCompatActivity implements MySoundsContractMVP.View {
    private Cursor cursor;
    private MusicListAdapter adapter;

    private ActionBar actionBar;
    private MySoundPresenter presenter;
    private String m_root = Environment.getExternalStorageDirectory().getPath();
    private String actualPath;
    private List<String> lastPaths = new ArrayList<>();
    private List<File> allFiles = new ArrayList<>();
    private List<File> directories = new ArrayList<>();

    @BindView(R.id.sounds_list_view)
    ListView filesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sounds);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        addBackArrow();
        createMySoundsPresenter();


        showFirstFileManagerList();

    }


    private void showFirstFileManagerList() {


        getDirFromRoot(m_root);
    }

    public void getDirFromRoot(String rootPath) {
        List<String> items = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        List<String> files = new ArrayList<>();
        List<String> filesPath = new ArrayList<>();

        File mFile = new File(rootPath);
        File[] filesArray = mFile.listFiles();

        Arrays.sort(filesArray);

        cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        cursor.moveToFirst();
        System.out.println("CURSOR COLUMN: " + cursor.toString());

        for (int i = 0; i < filesArray.length; i++) {
            File f = filesArray[i];

            if (f.isDirectory()) {
                items.add(f.getName());
                paths.add(f.getPath());

            } else {
                //filtr czy plik dźwiękowy
                files.add(f.getName());
                filesPath.add(f.getPath());
            }
        }

        for (String addFile : files) {
            items.add(addFile);
        }

        for (String addPath : filesPath) {
            paths.add(addPath);
        }



        FileListAdapter adapter1 = new FileListAdapter(this, items, paths, false);
        filesListView.setAdapter(adapter1);

        filesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                File isFile = new File(paths.get(position));

                if (isFile.isDirectory()) {
                    lastPaths.add(rootPath);
                    actualPath = isFile.toString();
                    getDirFromRoot(isFile.toString());
                } else {
                    Toast.makeText(MyFileActivity.this, "PLIK!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
    public void onBackPressed() {
        //DLA PRZEGLĄDANIA FOLDERÓW
        /*
        if (!lastPaths.isEmpty()) {
            actualPath = lastPaths.get(lastPaths.size() - 1);
            lastPaths.remove(lastPaths.size() - 1);
            getDirFromRoot(actualPath);
        } else {
            super.onBackPressed();
        }

         */

    }
    //DLA PRZEGLĄDANIA FOLDERÓW
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (m_root.equals(actualPath)) {
            this.finish();
            return true;
        }

        getDirFromRoot(lastPaths.get(lastPaths.size() - 1));
        return true;
    }

 */

    private void createMySoundsPresenter() {
        presenter = new MySoundPresenter();
        presenter.attach(this);
    }

    private void addBackArrow() {

        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
