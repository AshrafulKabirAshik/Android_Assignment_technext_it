package com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomModels.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;

    private static String DATABASE_NAME = "ashik_apps_database";

    public synchronized static RoomDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext()
                    , RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract RoomDao roomDao();

}
