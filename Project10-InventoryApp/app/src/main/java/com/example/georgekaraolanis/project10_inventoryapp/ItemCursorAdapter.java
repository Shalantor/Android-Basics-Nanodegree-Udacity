package com.example.georgekaraolanis.project10_inventoryapp;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

public class ItemCursorAdapter extends CursorAdapter{

    /*constructor*/
    public ItemCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        /*Get the TextViews and the ImageView*/
        TextView itemNameTextView = (TextView) view.findViewById(R.id.name);
        TextView itemQuantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView itemPriceTextView = (TextView) view.findViewById(R.id.price);
        ImageView itemImageView = (ImageView) view.findViewById(R.id.thumbnail);

        /*Get the columns with the data*/
        int itemNameColumn = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_NAME);
        int itemQuantityColumn = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_QUANTITY);
        int itemPriceColumn = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_PRICE);
        int itemImageColumn = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_IMAGE);

        /*Read the attributes*/
        String itemName = cursor.getString(itemNameColumn);
        String itemQuantity = String.valueOf(cursor.getInt(itemQuantityColumn));
        String itemPrice = String.valueOf(cursor.getFloat(itemPriceColumn));
        int itemImageId = cursor.getInt(itemImageColumn);

        /*update views*/
        itemNameTextView.setText(itemName);
        itemQuantityTextView.setText(itemQuantity);
        itemPriceTextView.setText(itemPrice);
        itemImageView.setImageResource(itemImageId);

    }
}
