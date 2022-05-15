package com.example.bugtracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BugRepository {
    private BugDao bugDao;
    private LiveData<List<Bug>> allBugs;

    public BugRepository(Application application){
        BugDatabase database = BugDatabase.getInstance(application);
        bugDao = database.bugDao();
        allBugs = bugDao.getAllNotes();
    }

    public void insert(Bug bug){
        new InsertBugAsyncTask(bugDao).execute(bug);

    }
    public void update(Bug bug){
        new UpdateBugAsyncTask(bugDao).execute(bug);

    }
    public void delete(Bug bug){
        new DeleteBugAsyncTask(bugDao).execute(bug);

    } public void deleteAllBug(Bug bug){
        new DeleteAllBugAsyncTask(bugDao).execute();

    }
    public LiveData<List<Bug>> getAllBugs(){
        return allBugs;
    }

    private static class InsertBugAsyncTask extends AsyncTask<Bug,Void,Void>{
        private BugDao bugDao;

        private InsertBugAsyncTask(BugDao bugDao){
            this.bugDao = bugDao;
        }

        @Override
        protected Void doInBackground(Bug... bugs) {
            bugDao.insert(bugs[0]);
            return null;
        }
    }

    private static class UpdateBugAsyncTask extends AsyncTask<Bug,Void,Void>{
        private BugDao bugDao;

        private UpdateBugAsyncTask(BugDao bugDao){
            this.bugDao = bugDao;
        }

        @Override
        protected Void doInBackground(Bug... bugs) {
            bugDao.update(bugs[0]);
            return null;
        }
    }

    private static class DeleteBugAsyncTask extends AsyncTask<Bug,Void,Void>{
        private BugDao bugDao;

        private DeleteBugAsyncTask(BugDao bugDao){
            this.bugDao = bugDao;
        }

        @Override
        protected Void doInBackground(Bug... bugs) {
            bugDao.delete(bugs[0]);
            return null;
        }
    }

    private static class DeleteAllBugAsyncTask extends AsyncTask<Void,Void,Void>{
        private BugDao bugDao;

        private DeleteAllBugAsyncTask(BugDao bugDao){
            this.bugDao = bugDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bugDao.deleteAllNotes();
            return null;
        }
    }


}
