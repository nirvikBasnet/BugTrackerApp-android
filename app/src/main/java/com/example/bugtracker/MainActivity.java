package com.example.bugtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_BUG_REQUEST = 1;

    private BugViewModel bugViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBug = findViewById(R.id.button_add_bug);
        buttonAddBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBugActivity.class);
                startActivityForResult(intent,ADD_BUG_REQUEST);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_BUG_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddBugActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddBugActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddBugActivity.EXTRA_PRIORITY, 1);

            Bug bug = new Bug(title,description,priority);
            bugViewModel.insert(bug);

            Toast.makeText(this,"Bug Reported.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Bug not reported!",Toast.LENGTH_SHORT).show();
        }
    }
}