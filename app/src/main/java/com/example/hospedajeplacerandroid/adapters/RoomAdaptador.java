package com.example.hospedajeplacerandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hospedajeplacerandroid.R;
import com.example.hospedajeplacerandroid.models.Room;
import java.util.List;


public class RoomAdaptador  extends ArrayAdapter<Room> {
    Context context;
    private class ViewHolder {
        TextView number_room;
        TextView type;

        private ViewHolder() {
        }
    }
    public RoomAdaptador(Context context, List<Room> items) {
        super(context, 0, items);
        this.context = context;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Room rowItem = (Room) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_room, null);
            holder = new ViewHolder();
            holder.number_room = (TextView) convertView.findViewById(R.id.number_room);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.number_room.setText(rowItem.number_room);
        holder.type.setText(rowItem.type);
        return convertView;
    }
}
