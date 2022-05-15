package com.example.bugtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BugViewModel bugViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        BugAdapter adapter = new BugAdapter();
        recyclerView.setAdapter(adapter);

        bugViewModel =new ViewModelProvider(this).get(BugViewModel.class);
        bugViewModel.getAllBugs().observe(this, new Observer<List<Bug>>() {
            @Override
            public void onChanged(List<Bug> bugs) {
                //update recyclerView
                adapter.setBugs(bugs);
            }
        });
    }
}