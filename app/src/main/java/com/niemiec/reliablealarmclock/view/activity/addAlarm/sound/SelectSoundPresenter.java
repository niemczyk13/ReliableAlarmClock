package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.niemiec.reliablealarmclock.data.DefaultValues;
import com.niemiec.reliablealarmclock.view.activity.BasePresenter;

public class SelectSoundPresenter extends BasePresenter<SelectSoundContractMVP.View> implements SelectSoundContractMVP.Presenter {
    private Context context;
    private AlarmSoundData data = AlarmSoundData.getInstance();

    private MediaPlayer player;
    private int markedPosition;
    private boolean isPlaying = false;
    private Sound markedSound;
    private int clickPosition;

    public SelectSoundPresenter(Context context) {
        this.context = context;

        this.markedPosition = data.getMarkedPosition();
        this.markedSound = data.get(markedPosition);
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

    @Override
    public void okButtonClick() {
        stopMusic();
        String id = Integer.toString(markedSound.getId());
        Intent intent = createIntentAndAddArgument(id);
        view.setResultAndFinish(intent);
    }

    private Intent createIntentAndAddArgument(String id) {
        Intent intent = new Intent();
        intent.putExtra("uri", id);
        return intent;
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
        if (isPlaying) {
            player.stop();
            isPlaying = false;
        }
    }

    private void updateUncheckedSound() {
        markedSound.setChecked(false);
    }

    private void replaceMarkedSound() {
        markedSound = data.get(clickPosition);
        markedPosition = clickPosition;
        data.setMarkedPosition(markedPosition);
    }

    private void updateMarkedSound() {
        markedSound.setChecked(true);
    }

    private void playMusic() {
        if (!isPlaying) {
            player = MediaPlayer.create(context, markedSound.getId());
            player.start();
            isPlaying = true;
        }
    }

    private void stopOrPlayTheSameSong() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic();
        }
    }
}
