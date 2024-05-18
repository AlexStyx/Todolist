package com.example.todolist.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.todolist.R;
import com.example.todolist.logic.models.TasksViewModel;
import com.example.todolist.ui.activity.MainActivity;
import com.example.todolist.ui.adapters.TodayTasksListAdapter;
import com.example.todolist.logic.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodayList extends Fragment {

    private TasksViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.today, container, false);
        ListView todaysList = view.findViewById(R.id.today_tasks_list);

        viewModel = ((MainActivity)requireActivity()).getSharedViewModel();

        final Observer<ArrayList<Task>> listObserver = newList -> {
            todaysList.setAdapter(new TodayTasksListAdapter(requireContext(), R.layout.task_cell, newList));
        };

        viewModel.getTodayTasks().observe(getViewLifecycleOwner(), listObserver);

        todaysList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

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