package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import com.niemiec.reliablealarmclock.R;

public class PlayButtonManager {
    private Context context;
    private Cursor cursor;
    private MediaPlayer mediaPlayer;
    private boolean playingSong = false;
    private int playingSoundPosition;
    private ImageButton playingImageButton;
    private int positionClicked;
    private ImageButton imageButtonClicked;

    public PlayButtonManager(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public void onClick(View view) {
        getPositionAndButtonClicked(view);

        if (playingSong) {
            stop();
            ifClickPlayAnotherSong();
        } else {
            play();
        }
    }

    private void getPositionAndButtonClicked(View view) {
        imageButtonClicked = (ImageButton) view;
        positionClicked = getPosition(view);
    }

    private int getPosition(View view) {
        GridLayout gl = (GridLayout) view.getParent();
        ListView lv = (ListView) gl.getParent();
        return lv.getPositionForView(gl);
    }

    private void stop() {
        playingImageButton.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
        mediaPlayer.stop();
        playingSong = false;
    }

    private void ifClickPlayAnotherSong() {
        if (playingSoundPosition != positionClicked) {
            play();
        }
    }

    private void play() {
        Uri uri = getSongUri();
        setTheCurrentButtonAndPosition();
        startMediaPlayer(uri);
    }

    private Uri getSongUri() {
        cursor.moveToPosition(positionClicked);
        return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
    }

    private void setTheCurrentButtonAndPosition() {
        playingImageButton = imageButtonClicked;
        playingSoundPosition = positionClicked;
    }

    private void startMediaPlayer(Uri uri) {
        imageButtonClicked.setImageResource(R.drawable.ic_baseline_stop_circle_24);
        mediaPlayer = MediaPlayer.create(context, uri);
        mediaPlayer.start();
        playingSong = true;
    }

    public void stopMusic() {
        mediaPlayer.stop();
        playingSong = false;
    }
}
