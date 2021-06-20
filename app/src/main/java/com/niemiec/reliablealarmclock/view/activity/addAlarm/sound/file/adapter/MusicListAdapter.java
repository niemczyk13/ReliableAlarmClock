package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.content.Context;
import android.database.Cursor;
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
import com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.MySoundsActivity;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MusicListAdapter extends BaseAdapter {
    private Cursor cursor;
    private Context context;
    private LayoutInflater inflater;

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
        return null;
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

        GridLayout vv = (GridLayout) parent.getChildAt(0);
        System.out.println("Child cound: " + parent.getChildCount());

        GridLayout v = view.findViewById(R.id.sounds_list_view);
        if (vv != null) {
            vv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "TEKST22222!", Toast.LENGTH_SHORT).show();
                    System.out.println("TEKST22222!");
                }
            });
        }

        return view;
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
