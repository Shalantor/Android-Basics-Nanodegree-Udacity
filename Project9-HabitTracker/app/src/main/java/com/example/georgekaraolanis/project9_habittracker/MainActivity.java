package com.example.georgekaraolanis.project9_habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.georgekaraolanis.project9_habittracker.data.HabitContract;
import com.example.georgekaraolanis.project9_habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    /*Tag for logging*/
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*Insert a habit into the database*/
    private void insertData(String name,String time, int category){

        /*Create helper*/
        HabitDbHelper helper = new HabitDbHelper(this);

        /*Get database in write mode*/
        SQLiteDatabase db = helper.getWritableDatabase();

        /*Get ContentValues item*/
        ContentValues values = new ContentValues();

        /*Put in values*/
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME,name);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TIME,time);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_CATEGORY,category);

        /*Insert into database*/
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME,null,values);

        /*Check result*/
        if (newRowId == -1 ){
            Log.d(LOG_TAG,"Error inserting value into database");
        }
        else{
            Log.d(LOG_TAG,"Value inserted successfully");
        }

    }

    /*Read an entry or entries from database*/
    private Cursor readData(){

        /*Create helper*/
        HabitDbHelper helper = new HabitDbHelper(this);

        /*Create and/or read from database*/
        SQLiteDatabase db = helper.getReadableDatabase();

        /*Define projection. Let's say we want all columns*/
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_TIME,
                HabitContract.HabitEntry.COLUMN_HABIT_CATEGORY
        };

        /*Perform query*/
        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;

    }

}
