package com.example.georgekaraolanis.project10_inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

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
        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case ITEM_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Override
    public Uri insert(Uri uri,ContentValues values) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return insertItem(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /*Insert an item into database*/
    private Uri insertItem(Uri uri, ContentValues values){

        /*Check that the item name is not null*/
        String name = values.getAsString(InventoryEntry.COLUMN_ITEM_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Item requires a name");
        }

        /*Check that image path is not null*/
        String imagePath = values.getAsString(InventoryEntry.COLUMN_ITEM_IMAGE);
        if (imagePath == null) {
            throw new IllegalArgumentException("Item requires an image");
        }

        /*Check that quantity is greater than or equal to zero*/
        Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_ITEM_QUANTITY);
        if ((quantity != null && quantity < 0) || quantity == null) {
            throw new IllegalArgumentException("Item requires a valid quantity");
        }

        /*Check that price is greater than or equal to zero*/
        Float price = values.getAsFloat(InventoryEntry.COLUMN_ITEM_PRICE);
        if ((price != null && price < 0) || price == null) {
            throw new IllegalArgumentException("Item requires a valid price");
        }

        /*Get writeable database*/
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /*Insert new item with the given values*/
        long id = database.insert(InventoryEntry.TABLE_NAME,null,values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        /*Notify listeners that we have new data*/
        getContext().getContentResolver().notifyChange(uri, null);

        /*Return uri with the new id*/
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {

        /*Get writeable database*/
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /*Number of deleted rows*/
        int rowsDeleted;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ITEM_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Can not delete for " + uri);
        }

        /*Check how many rows got deleted*/
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        /*Return number of deleted rows*/
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return updateItem(uri, values, selection, selectionArgs);
            case ITEM_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateItem(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /*Update an item*/
    private int updateItem(Uri uri, ContentValues values, String selection, String[] selectionArgs){

        /*Check that name is not null*/
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_NAME)){
            String name = values.getAsString(InventoryEntry.COLUMN_ITEM_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Item requires a name");
            }
        }

        /*Check image path*/
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_IMAGE)){
            String imagePath = values.getAsString(InventoryEntry.COLUMN_ITEM_IMAGE);
            if (imagePath == null) {
                throw new IllegalArgumentException("Item requires an image");
            }
        }

        /*Check item quantity*/
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_QUANTITY)){
            Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_ITEM_QUANTITY);
            if ((quantity != null && quantity < 0) || quantity == null) {
                throw new IllegalArgumentException("Item requires a valid quantity");
            }
        }

        /*Check item price*/
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_PRICE)){
            Float price = values.getAsFloat(InventoryEntry.COLUMN_ITEM_PRICE);
            if ((price != null && price < 0) || price == null ) {
                throw new IllegalArgumentException("Item requires a valid price");
            }
        }

        /*If there are no values to update, then don't try to update the database*/
        if (values.size() == 0) {
            return 0;
        }

        /*Else get writeable database*/
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /*Perform update*/
        int rowsUpdated = database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);

        /*Check if necessary to notify listeners*/
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        /*Return numbers of updated rows*/
        return rowsUpdated;
    }

}
