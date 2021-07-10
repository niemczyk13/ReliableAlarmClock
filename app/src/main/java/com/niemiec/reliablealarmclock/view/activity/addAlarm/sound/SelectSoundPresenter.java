package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.niemiec.reliablealarmclock.data.DefaultValues;
import com.niemiec.reliablealarmclock.view.activity.BasePresenter;

public class SelectSoundPresenter extends BasePresenter<SelectSoundContractMVP.View> implements SelectSoundContractMVP.Presenter {
    private Context context;
    private AlarmSoundData data;

    private MediaPlayer player;
    private int markedPosition = DefaultValues.SOUND_POSITION.value();
    private boolean isPlaying = false;
    private Sound markedSound;
    private int clickPosition;

    public SelectSoundPresenter(Context context, AlarmSoundData data) {
        this.context = context;
        this.data = data;
        this.markedSound = data.get(DefaultValues.SOUND_POSITION.value());
    }

    @Override
    public void itemClick(int clickPosition) {
        this.clickPosition = clickPosition;
        if (isClickedOtherSong()) {
            stopOldAndPlayNewSong();
        } else {
            stopOrPlayTheSameSong();
        }
    }

    private boolean isClickedOtherSong() {
        return markedPosition != clickPosition;
    }

    private void stopOldAndPlayNewSong() {
        stopMusic();
        updateUncheckedSound();
        replaceMarkedSound();
        updateMarkedSound();
        playMusic();
        view.updateListView();
    }

    private void stopMusic() {
        player.stop();
        isPlaying = false;
    }

    private void updateUncheckedSound() {
        markedSound.setChecked(false);
    }

    private void replaceMarkedSound() {
        markedSound = data.get(clickPosition);
        markedPosition = clickPosition;
    }

    private void updateMarkedSound() {
        markedSound.setChecked(true);
    }

    private void playMusic() {
        player = MediaPlayer.create(context, markedSound.getId());
        player.start();
        isPlaying = true;
    }

    private void stopOrPlayTheSameSong() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic();
        }
    }
}
