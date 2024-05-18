package com.example.todolist.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolist.R;
import com.example.todolist.logic.models.Task;

import java.util.List;

public class TodayTasksListAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int layout;
    private List<Task> items;

    public TodayTasksListAdapter(Context context, int resource,
                                 List<Task> items) {
        super(context, resource, items);
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);
        TextView textView = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.checkmark);
        Task task = items.get(position);
        textView.setText(task.title);
        if (task.isDone){
            imageView.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            imageView.setImageResource(android.R.drawable.checkbox_off_background);
        }
        return view;
    }
}
