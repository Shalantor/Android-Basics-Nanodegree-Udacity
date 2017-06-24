package com.example.georgekaraolanis.project10_inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {

    /*Empty constructor*/
    private InventoryContract(){}

    /*Name for content provider*/
    public static final String CONTENT_AUTHORITY = "com.example.android.georgekaraolanis.project10_inventoryapp";

    /*The base for the uri apps will use to connect to content provider*/
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /*The path appended to base uri*/
    public static final String PATH_INVENTORY = "inventory";

    /*Constant values for database*/
    public static final class InventoryEntry implements BaseColumns{

        /*The content URI to access the inventory data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        /*MIME type of content for list of items in inventory*/
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /*MIME type of content for one item in inventory*/
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /*Name of database table for inventory */
        public final static String TABLE_NAME = "inventory";

        /*ID unique number for items in table*/
        public final static String _ID = BaseColumns._ID;

        /*name of item, of type TEXT*/
        public final static String COLUMN_ITEM_NAME = "name";

        /*quantity of item, of type INTEGER*/
        public final static String COLUMN_ITEM_QUANTITY = "quantity";

        /*price of item, of type REAL*/
        public final static String COLUMN_ITEM_PRICE = "price";

        /*path of item image*/
        public final static String COLUMN_ITEM_IMAGE = "image_path";
        
    }

}
