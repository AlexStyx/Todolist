package com.example.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Objects;

public class InitialScreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.initial_screen, container, false);

        Button todaysButton = view.findViewById(R.id.today_button);
        Button inboxButton = view.findViewById(R.id.inbox_button);

        todaysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", todaysButton.getText().toString());
                Navigation.findNavController(view).navigate(R.id.initial_to_today_action, bundle);
            }
        });

        inboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", inboxButton.getText().toString());
                Navigation.findNavController(view).navigate(R.id.initial_to_today_action, bundle);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar bar = Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar());
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setTitle("Todolist");
    }
}