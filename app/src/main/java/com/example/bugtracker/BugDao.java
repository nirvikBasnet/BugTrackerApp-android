package com.example.bugtracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BugDao {
    @Insert
    void insert(Bug bug);
    @Update
    void update(Bug bug);
    @Delete
    void delete(Bug bug);
    @Query("DELETE FROM bug_table")
    void deleteAllNotes();
    @Query("SELECT * FROM bug_table ORDER BY priority DESC")
    LiveData<List<Bug>> getAllNotes();
}
