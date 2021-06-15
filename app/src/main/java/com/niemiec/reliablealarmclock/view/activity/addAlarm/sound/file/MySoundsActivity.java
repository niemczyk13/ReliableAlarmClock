package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter.MusicListAdapter;


public class MySoundsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, MySoundsContractMVP.View {

    private MusicListAdapter adapter;

    private ActionBar actionBar;
    private MySoundPresenter presenter;

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

        showMusicList();
    }

    private void showMusicList() {
        getSupportLoaderManager().initLoader(1, null, this);
        //LoaderManager.getInstance(this).initLoader(0, null, null);

        filesListView.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long id) -> {
            Toast.makeText(MySoundsActivity.this, "TEKST!", Toast.LENGTH_SHORT).show();
            System.out.println("TEKST!");
        });
    }

    private void createMySoundsPresenter() {
        presenter = new MySoundPresenter();
        presenter.attach(this);
    }

    private void addBackArrow() {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        CursorLoader cl =  new CursorLoader(this, uri, null, null, null, null);
        cl.setSortOrder(MediaStore.MediaColumns.TITLE + " ASC" );
        return cl;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        adapter = new MusicListAdapter(this, cursor);
        filesListView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}