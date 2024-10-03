package com.eldroid.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<TodoData> {


    public ListAdapter(@NonNull Context context, List<TodoData> listData) {
        super(context, R.layout.list, listData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list,parent, false);

        }
        //getPosition
        TodoData todoData = getItem(position);

        TextView description = convertView.findViewById(R.id.desc);
        ImageView image = convertView.findViewById(R.id.Img);
        CheckBox box = convertView.findViewById(R.id.checkbox);

        //Populate the data into the template view using the object data

        if (todoData != null){
            description.setText(todoData.getDescription());
            image.setImageResource(todoData.getImg());

        }
        return convertView;
    }
}
