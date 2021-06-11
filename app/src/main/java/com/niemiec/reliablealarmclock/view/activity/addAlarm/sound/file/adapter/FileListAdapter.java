package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.niemiec.reliablealarmclock.R;

import java.io.File;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.MediaColumns.TITLE;
import static android.provider.MediaStore.Video.VideoColumns.ARTIST;

public class FileListAdapter extends BaseAdapter {
    private List<String> item;
    private List<String> path;
    Context context;
    Boolean isRoot;

    public FileListAdapter(Context context, List<String> item, List<String> path, Boolean isRoot) {
        this.item = item;
        this.path = path;
        this.context = context;
        this.isRoot = isRoot;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.file_list_row, null);
            viewHolder = new ViewHolder();

            viewHolder.icon = view.findViewById(R.id.icon_image_view);
            viewHolder.fileName = view.findViewById(R.id.file_name_text_view);
     //       viewHolder.play = view.findViewById(R.id.play_image_button);

            view.setTag(viewHolder);


        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.icon.setImageResource(setFileImageType(new File(path.get(position))));
        viewHolder.fileName.setText(item.get(position));
   //     viewHolder.play.setImageResource(setPlayImage(new File(path.get(position))));

        return view;
    }

    private int setPlayImage(File file) {
        //patrzymy czy plik i dajem znak play
        if (!file.isDirectory()) {
            return R.drawable.ic_baseline_play_circle_outline_24;
        } else {
            return 0;
        }
    }

    private int setFileImageType(File file) {
        int lastIndex = file.getAbsolutePath().lastIndexOf(".");
        String filepath = file.getAbsolutePath();

        if (file.isDirectory()) {
            return R.drawable.ic_baseline_folder_24;
        } else {
            return R.drawable.ic_baseline_music_note_24;
        }
    }

    class ViewHolder {
        ImageView icon;
        TextView fileName;
     //   ImageButton play;
    }
}
