package com.example.todolist;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodayList extends Fragment {

    private static final String CHANNEL_ID = "todo_app_channel";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private NotificationManagerCompat manager;
    Intent serviceIntent;

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
                onSelectItem();
            }
        });

        ActionBar toolbar = Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar());
        toolbar.setDisplayHomeAsUpEnabled(true);
        String title = null;
        if (getArguments() != null) {
            title = getArguments().getString("title");
            toolbar.setTitle(title);
        }

        setupNotificationManager();

        serviceIntent = new Intent(requireContext(), TasksService.class);
        return view;
    }

    private void setupNotificationManager() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "main_channel", NotificationManager.IMPORTANCE_DEFAULT);
        manager = NotificationManagerCompat.from(requireActivity());
        manager.createNotificationChannel(channel);
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
                .setContentTitle("Задача добавлена")
                .setContentText("Ты молодец")
                .setSmallIcon(android.R.drawable.checkbox_on_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
            manager.notify(
                    1337,
                    builder.build()
            );
        } else {
            requestPermissions();
        }
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{
                        Manifest.permission.POST_NOTIFICATIONS
                },
                PERMISSION_REQUEST_CODE);
    }


    private void onSelectItem() {
        showNotification();
        requireContext().startService(serviceIntent);
    }
}