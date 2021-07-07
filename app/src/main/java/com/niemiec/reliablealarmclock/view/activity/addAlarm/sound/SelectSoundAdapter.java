package com.niemiec.reliablealarmclock.view.activity.addAlarm.sound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.niemiec.reliablealarmclock.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SelectSoundAdapter extends BaseAdapter {
    private Context context;
    private AlarmSoundData data;
    private LayoutInflater inflater;

    public SelectSoundAdapter(SelectSoundActivity selectSoundActivity, AlarmSoundData data) {
        this.context = selectSoundActivity;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
          return data.getSize();
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
        View view = convertView;
        ViewHolder viewHolder = null;

        Sound sound = data.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.sound_list_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        setValueInViewHolder(viewHolder, sound);

        return view;
    }

    private void setValueInViewHolder(ViewHolder viewHolder, Sound sound) {
        viewHolder.check.setImageResource(getImageResource(sound.isChecked()));
        viewHolder.name.setText(sound.getName());
    }

    private int getImageResource(boolean checked) {
        if (checked) {
            return R.drawable.ic_baseline_check_box_24;
        } else {
            return R.drawable.ic_baseline_check_box_outline_blank_24;
        }
    }

    class ViewHolder {
        ImageButton check;
        TextView name;

        public ViewHolder(View view) {
            check = view.findViewById(R.id.check_image_button);
            name = view.findViewById(R.id.name_text_view);
        }
    }


}
