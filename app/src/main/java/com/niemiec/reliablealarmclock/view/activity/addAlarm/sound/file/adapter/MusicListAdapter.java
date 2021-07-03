package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.niemiec.reliablealarmclock.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class MusicListAdapter extends BaseAdapter implements Filterable {
    private Cursor cursor;
    private Context context;
    private LayoutInflater inflater;
    private PlayButtonManager playButtonManager;

    public MusicListAdapter(Context context, Cursor cursor, PlayButtonManager playButtonManager) {
        this.context = context;
        this.cursor = cursor;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.playButtonManager = playButtonManager;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) { return null; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;

        cursor.moveToPosition(position);

        if (view == null) {
            view = inflater.inflate(R.layout.music_list_row, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        setValuesInViewHolder(viewHolder, position);

        return view;
    }

    private void setValuesInViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.author.setText(getAuthor());
        viewHolder.title.setText(cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.TITLE)));
        setImageResource(viewHolder, position);
        viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButtonManager.onClick(view);
            }
        });
    }

    private void setImageResource(ViewHolder viewHolder, int position) {
        if (theSongIsNotPlaying()) {
            viewHolder.playButton.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
        } else {
            viewHolder.playButton.setImageResource(R.drawable.ic_baseline_stop_circle_24);
            playButtonManager.setImageButton(viewHolder.playButton);
            playButtonManager.setPlayingSoundPosition(position);
        }
    }

    private boolean theSongIsNotPlaying() {
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BaseColumns._ID)));
        return playButtonManager.getSoundId() != id;
    }

    private String getAuthor() {
        String author = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
        if (author.equalsIgnoreCase("<unknown>")) {
            author = context.getResources().getString(R.string.author_unkown);
        }
        return author;
    }

    public void stopMusic() {
        playButtonManager.stopMusic();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class ViewHolder {
        TextView title;
        TextView author;
        ImageButton playButton;

        public ViewHolder(View view) {
            title = view.findViewById(R.id.title_text_view);
            author = view.findViewById(R.id.author_text_view);
            playButton = view.findViewById(R.id.play_image_button);
        }
    }
}
