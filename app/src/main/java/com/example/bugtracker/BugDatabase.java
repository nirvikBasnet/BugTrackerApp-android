package com.example.bugtracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Bug.class, version = 1)
public abstract class BugDatabase extends RoomDatabase {

    private static BugDatabase instance;
    
    public abstract BugDao bugDao();

    public static synchronized BugDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BugDatabase.class, "bug_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }


}
