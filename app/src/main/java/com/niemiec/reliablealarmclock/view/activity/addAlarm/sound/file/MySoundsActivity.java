package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.niemiec.reliablealarmclock.R;
import com.niemiec.reliablealarmclock.view.activity.MainActivity;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.AddAlarmActivity;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.SelectSoundActivity;
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter.MusicListAdapter;


public class MySoundsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, MySoundsContractMVP.View {

    private MusicListAdapter adapter;
    private ActionBar actionBar;
    private MySoundPresenter presenter;
    private String filter;

    @BindView(R.id.sounds_list_view)
    ListView filesListView;

    @BindView(R.id.sound_search_view)
    SearchView searchView;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sounds);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        addBackArrow();
        showMusicList();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter = MediaStore.MediaColumns.TITLE + " LIKE '%" + query + "%' OR " + MediaStore.Audio.AlbumColumns.ARTIST + " LIKE '%" + query + "%'";
                getSupportLoaderManager().restartLoader(1, null, MySoundsActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter = MediaStore.MediaColumns.TITLE + " LIKE '%" + newText + "%' OR " + MediaStore.Audio.AlbumColumns.ARTIST + " LIKE '%" + newText + "%'";
                getSupportLoaderManager().restartLoader(1, null, MySoundsActivity.this);
                return false;
            }
        });
    }

    private void showMusicList() {
        getSupportLoaderManager().initLoader(1, null, this);

        filesListView.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long id) -> {
            cursor.moveToPosition(position);
            Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));

            Intent intent = new Intent();
            intent.putExtra("uri", uri.toString());
            setResult(RESULT_OK, intent);
            finish();

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO DO DOPRACOWANIA
        adapter.stopMusic();
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
        CursorLoader cl =  new CursorLoader(this, uri, null, filter, null, null);
        cl.setSortOrder(MediaStore.MediaColumns.TITLE + " ASC" );
        return cl;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        this.cursor = cursor;
        adapter = new MusicListAdapter(this, cursor);
        filesListView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}