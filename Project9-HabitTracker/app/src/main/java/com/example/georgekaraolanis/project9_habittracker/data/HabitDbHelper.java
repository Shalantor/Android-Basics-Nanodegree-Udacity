package com.example.georgekaraolanis.project9_habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.georgekaraolanis.project9_habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper{

    /*Database file name*/
    public static final String DATABASE_NAME = "habits.db";

    /*Database version*/
    public static final int DATABASE_VERSION = 1;

    /*Constructor*/
    public HabitDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*SQL statement to create table*/
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL"
                + HabitEntry.COLUMN_HABIT_CATEGORY + " INTEGER DEFAULT 4"
                + HabitEntry.COLUMN_HABIT_TIME + " TEXT);";

        /*Execute the statement*/
        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Leave empty, there is no newer version of this database*/
    }
}
