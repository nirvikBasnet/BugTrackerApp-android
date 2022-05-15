package com.example.bugtracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Bug.class, version = 1)
public abstract class BugDatabase extends RoomDatabase {

    private static BugDatabase instance;
    
    public abstract BugDao bugDao();

    public static synchronized BugDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BugDatabase.class, "bug_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private BugDao bugDao;

        private PopulateDbAsyncTask(BugDatabase db){
            bugDao = db.bugDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bugDao.insert(new Bug("Bug 1","This is Bug 1",1));
            bugDao.insert(new Bug("Bug 2","This is Bug 2",2));
            bugDao.insert(new Bug("Bug 3","This is Bug 3",3));
            return null;
        }
    }


}
