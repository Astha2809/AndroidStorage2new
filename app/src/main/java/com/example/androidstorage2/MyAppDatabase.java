package com.example.androidstorage2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {EmployeeInformation.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract DataAccessObject dataAccessObject() ;

    private static volatile MyAppDatabase INSTANCE;


    static MyAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyAppDatabase.class, "word_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
