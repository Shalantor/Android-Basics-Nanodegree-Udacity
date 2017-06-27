package com.example.georgekaraolanis.project10_inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

public class InventoryDbHelper extends SQLiteOpenHelper{

    /*Tag for logging*/
    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    /*Name of the database file */
    private static final String DATABASE_NAME = "inventory.db";

    /*Version of database*/
    private static final int DATABASE_VERSION = 1;

    /*Constructor*/
    public InventoryDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_ITEM_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryEntry.COLUMN_ITEM_PRICE + " REAL NOT NULL, "
                + InventoryEntry.COLUMN_ITEM_IMAGE + " TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*It is the first database version, so leave this method empty*/
    }
}
