package com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomDao {
    @Insert(onConflict = REPLACE)
    void insert(RoomModels roomModels);

    @Query("SELECT * FROM ashik_database_table")
    List<RoomModels> getAll();
}
