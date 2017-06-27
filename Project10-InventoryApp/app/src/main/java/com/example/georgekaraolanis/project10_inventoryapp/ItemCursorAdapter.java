package com.example.georgekaraolanis.project10_inventoryapp;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ItemCursorAdapter extends CursorAdapter{

    private Context context;

    /*tag for logging*/
    private static final String LOG_TAG = ItemCursorAdapter.class.getSimpleName();

    /*constructor*/
    public ItemCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context,Cursor cursor) {
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
        String itemImage = cursor.getString(itemImageColumn);

        /*update views*/
        itemNameTextView.setText(itemName);
        itemQuantityTextView.setText(itemQuantity);
        itemPriceTextView.setText(itemPrice);

        /*ImageView*/
        itemImageView.setImageBitmap(getBitmapFromUri(Uri.parse(itemImage),itemImageView));

        /*Get button and add a click listener*/
        Button saleButton = (Button) view.findViewById(R.id.sale_button);

        /*Get id of item*/
        int itemIdColumn = cursor.getColumnIndex(InventoryEntry._ID);
        final long id = cursor.getLong(itemIdColumn);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Get quantity*/
                TextView itemQuantityTextView = (TextView) view.findViewById(R.id.quantity);
                int quantity = Integer.parseInt( itemQuantityTextView.getText().toString() );

                /*Values items*/
                ContentValues values = new ContentValues();
                values.put(InventoryEntry.COLUMN_ITEM_QUANTITY,quantity-1 + "");

                Uri itemUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);

                int rowsAffected = context.getContentResolver().update(itemUri,values,null,null);

                Log.d("QUANTITY",(quantity - 1) +"");

                // Show a toast message depending on whether or not the update was successful.
                if (rowsAffected == 0) {
                    // If no rows were affected, then there was an error with the update.
                    Toast.makeText(context, "Error updating item",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String quantityString = (quantity - 1) + "";
                    itemQuantityTextView.setText(quantityString);
                }

            }
        });
    }

    public Bitmap getBitmapFromUri(Uri uri,ImageView view) {

        if (uri == null || uri.toString().isEmpty())
            return null;

        /* Get the dimensions of the View*/
        int targetW = view.getWidth();
        int targetH = view.getHeight();

        if (targetW == 0){
            targetW = (int) context.getResources().getDimension(R.dimen.image_dimension);
        }

        if (targetH == 0){
            targetH = (int) context.getResources().getDimension(R.dimen.image_dimension);
        }

        InputStream input = null;
        try {
            input =  context.getContentResolver().openInputStream(uri);

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            //bmOptions.inPurgeable = true;

            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();
            return bitmap;

        } catch (FileNotFoundException fne) {
            Log.e(LOG_TAG, "Failed to load image.", fne);
            return null;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException ex) {

            }
        }
    }
}
