package com.example.bugtracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BugViewModel extends AndroidViewModel {
    private BugRepository repository;
    private LiveData<List<Bug>> allBugs;
    public BugViewModel(@NonNull Application application) {
        super(application);
        repository = new BugRepository(application);
        allBugs = repository.getAllBugs();
    }

    public void insert(Bug bug){
        repository.insert(bug);
    }
    public void update(Bug bug){
        repository.update(bug);
    }
    public void delete(Bug bug){
        repository.delete(bug);
    }
    public void deleteAllBugs(){
        repository.deleteAllBug();
    }

    public LiveData<List<Bug>> getAllBugs() {
        return allBugs;
    }
}
