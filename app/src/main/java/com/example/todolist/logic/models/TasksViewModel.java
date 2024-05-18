package com.example.todolist.logic.models;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TasksViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Task>> todayTasks;
    private MutableLiveData<ArrayList<Task>> inbox;

    public TasksViewModel() {
        todayTasks = new MutableLiveData<ArrayList<Task>>();
        inbox = new MutableLiveData<ArrayList<Task>>();
    }

    public MutableLiveData<ArrayList<Task>> getTodayTasks() {
        return todayTasks;
    }

    public MutableLiveData<ArrayList<Task>> getInbox() {
        return inbox;
    }
}
