package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodayList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today, container, false);
        ListView todaysList = view.findViewById(R.id.today_tasks_list);

        List<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < 200; i++) {
            Task task = new Task();
            task.isDone = i % 2 == 0;
            task.title = "Task_" + i;
            tasks.add(task);
        }

        TodayTasksListAdapter adapter = new TodayTasksListAdapter(getContext(), R.layout.task_cell, tasks);

        todaysList.setAdapter(adapter);

        todaysList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String clickedItem = tasks.get(position).title;
                Toast.makeText(getActivity(), "Нажали на элемент: " + clickedItem, Toast.LENGTH_SHORT).show();
                Log.d("ListView", "Нажали на элемент: " + clickedItem);
            }
        });

        ActionBar toolbar = Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar());
        toolbar.setDisplayHomeAsUpEnabled(true);
        String title = null;
        if (getArguments() != null) {
            title = getArguments().getString("title");
            toolbar.setTitle(title);
        }
        return view;
    }
}