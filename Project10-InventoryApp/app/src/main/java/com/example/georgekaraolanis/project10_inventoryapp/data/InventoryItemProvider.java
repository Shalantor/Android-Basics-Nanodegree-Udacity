package com.example.georgekaraolanis.project10_inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

public class InventoryItemProvider extends ContentProvider{

    /*tag for logging*/
    public static final String LOG_TAG = InventoryItemProvider.class.getSimpleName();

    /*URI matcher code for list of items */
    private static final int ITEMS = 100;

    /*URI matcher code for one item */
    private static final int ITEM_ID = 101;

    /*URI matcher*/
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /*Static initializer for what uris the matcher should recognize as accepted*/
    static {
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, ITEMS);
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", ITEM_ID);
    }

    /*The db helper object*/
    private InventoryDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs, String sortOrder) {

        /*Get readable database*/
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        /*Cursor to return*/
        Cursor cursor;

        /*Get the match from the uri matcher*/
        int match = uriMatcher.match(uri);

        /*Check the match*/
        switch (match) {
            case ITEMS:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case ITEM_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        /*Set notification uri on cursor. If the uri changes, we need to change cursor*/
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        /*Return the cursor*/
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri,ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs) {
        return 0;
    }
}
