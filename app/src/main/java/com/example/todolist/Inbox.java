package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Inbox extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inbox, container, false);
        RecyclerView list = view.findViewById(R.id.inbox_list);

        List<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < 200; i++) {
            Task task = new Task();
            task.isDone = i % 2 == 0;
            task.title = "Task_" + i;
            tasks.add(task);
        }

        InboxListAdapter adapter = new InboxListAdapter(getContext(), tasks);
        adapter.setOnClickTaskListener(new InboxListAdapter.OnClickTaskListener() {
            @Override
            public void onItemClick(int position) {
                // Обработка нажатия на элемент списка
                String clickedItem = tasks.get(position).title;
                Toast.makeText(getActivity(), "Нажали на элемент: " + clickedItem, Toast.LENGTH_SHORT).show();
                Log.d("Recycler", "Нажали на элемент: " + clickedItem);
            }
        });
        list.setAdapter(adapter);
        return view;
    }
}