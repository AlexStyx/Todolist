package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InboxListAdapter extends RecyclerView.Adapter<InboxListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Task> tasks;

    private OnClickTaskListener clickListener;
    InboxListAdapter(Context context, List<Task> tasks) {
        this.inflater = LayoutInflater.from(context);
        this.tasks = tasks;
    }

    public void setOnClickTaskListener(OnClickTaskListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_cell, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textView.setText(task.title);
        if (task.isDone){
            holder.imageView.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            holder.imageView.setImageResource(android.R.drawable.checkbox_off_background);
        }
        holder.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final ImageView imageView;
        private OnClickTaskListener listener = null;
        public void setOnClickListener(OnClickTaskListener listener) {
            this.listener = listener;
        }
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.checkmark);
            textView = view.findViewById(R.id.title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnClickTaskListener {
        void onItemClick(int position);
    }
}
