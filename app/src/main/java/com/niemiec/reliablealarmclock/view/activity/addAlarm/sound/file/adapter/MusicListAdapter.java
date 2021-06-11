package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

public class MusicListAdapter extends BaseAdapter {
    private CursorLoader cursor;
    private AppCompatActivity activity;

    public MusicListAdapter(AppCompatActivity activity) {
        this.activity = activity;
        //cursor = new CursorLoader(activity, null, );
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
