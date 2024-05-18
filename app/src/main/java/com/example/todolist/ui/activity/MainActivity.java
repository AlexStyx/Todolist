package com.example.todolist.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.todolist.R;
import com.example.todolist.logic.models.TasksViewModel;

public class MainActivity extends AppCompatActivity {
    private ImageButton createTaskButton;
    private TasksViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createTaskButton = toolbar.findViewById(R.id.buttonAdd);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(findViewById(R.id.fragment_container))
                        .navigate(R.id.CreateTask);
                createTaskButton.setVisibility(View.GONE);
            }
        });

        viewModel = new ViewModelProvider(this).get(TasksViewModel.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        View container = findViewById(R.id.fragment_container);
        NavController controller = Navigation.findNavController(container);
        createTaskButton.setVisibility(View.VISIBLE);
        return controller.navigateUp() || super.onSupportNavigateUp();
    }

    public TasksViewModel getSharedViewModel() {
        return viewModel;
    }
}
