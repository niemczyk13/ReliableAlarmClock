package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.niemiec.reliablealarmclock.R;

import androidx.appcompat.app.AppCompatActivity;

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
            view = inflater.inflate(R.layout.file_list_row, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.icon = view.findViewById(R.id.icon_image_view);
            viewHolder.fileName = view.findViewById(R.id.file_name_text_view);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.icon.setImageResource(R.drawable.ic_baseline_music_note_24);
        viewHolder.fileName.setText(cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)));
        //System.out.println(cursor.getColumnName(2));

        return view;
    }

    class ViewHolder {
        ImageView icon;
        TextView fileName;
        //   ImageButton play;
    }
}
