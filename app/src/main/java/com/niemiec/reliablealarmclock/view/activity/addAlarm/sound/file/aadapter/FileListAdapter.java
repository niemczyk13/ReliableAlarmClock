package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound.file.aadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.niemiec.reliablealarmclock.R;

import java.io.File;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

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

            view.setTag(viewHolder);


        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.fileName.setText(item.get(position));
        viewHolder.icon.setImageResource(setFileImageType(new File(path.get(position))));

        return view;
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
    }
}
