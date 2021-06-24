package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.niemiec.reliablealarmclock.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MusicListAdapter extends BaseAdapter {
    private Cursor cursor;
    private Context context;
    private LayoutInflater inflater;

    private MediaPlayer mediaPlayer;
    private boolean playing = false;
    private int playingSoundPosition;
    private ImageButton playingImageButton;

    public MusicListAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        cursor.moveToPosition(i);

        return cursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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

        //TUTAJ SETTEXT DOBRZE ZROBIC

        viewHolder.author.setText(getAuthor());
        viewHolder.title.setText(cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.TITLE)));
        viewHolder.playButton.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
        viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridLayout gl = (GridLayout) view.getParent();
                ListView lv = (ListView) gl.getParent();
                ImageButton imageButton = (ImageButton) view;

                int position = lv.getPositionForView(gl);
                cursor.moveToPosition(position);
                Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));

                if (playing) {
                    playingImageButton.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                    mediaPlayer.stop();
                    cursor.moveToPosition(playingSoundPosition);
                    playing = false;
                    if (playingSoundPosition != position) {
                        imageButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);

                        playingImageButton = imageButton;
                        playingSoundPosition = position;
                        mediaPlayer = MediaPlayer.create(context, uri);
                        mediaPlayer.start();
                        playing = true;
                    }
                } else {
                    imageButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    playingImageButton = imageButton;
                    playingSoundPosition = position;
                    mediaPlayer = MediaPlayer.create(context, uri);
                    mediaPlayer.start();
                    playing = true;
                }


            }
        });

        ListView v = (ListView) parent;

        v.setOnItemClickListener((adapterView, view1, i, l) -> {
            cursor.moveToPosition(i);
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.TITLE));
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();


        });






        return view;
    }

    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    private String getAuthor() {
        String author = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
        if (author.equalsIgnoreCase("<unknown>")) {
            author = context.getResources().getString(R.string.author_unkown);
        }
        return author;
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
