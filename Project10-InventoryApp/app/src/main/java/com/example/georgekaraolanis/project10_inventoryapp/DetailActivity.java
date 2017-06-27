package com.example.georgekaraolanis.project10_inventoryapp;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract;
import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.example.georgekaraolanis.project10_inventoryapp.data.InventoryDbHelper.LOG_TAG;

public class DetailActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    /*Loader id*/
    private static final int INVENTORY_LOADER = 1;

    /*Current item*/
    Uri currentUri;

    /*Views in layout*/
    ImageView itemImageView;
    TextView itemNameTextView;
    EditText itemQuantityEditText;
    TextView itemPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*Get intent data*/
        Intent intent = getIntent();
        currentUri = intent.getData();

        getLoaderManager().initLoader(INVENTORY_LOADER, null, this);

        /*references to views*/
        itemImageView = (ImageView) findViewById(R.id.item_image);
        itemNameTextView = (TextView) findViewById(R.id.item_name);
        itemQuantityEditText= (EditText) findViewById(R.id.item_quantity);
        itemPriceTextView = (TextView) findViewById(R.id.item_price);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all pet attributes, define a projection that contains
        // all columns from the pet table
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_ITEM_NAME,
                InventoryEntry.COLUMN_ITEM_QUANTITY,
                InventoryEntry.COLUMN_ITEM_PRICE,
                InventoryEntry.COLUMN_ITEM_IMAGE };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                currentUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_PRICE);
            int imageColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_IMAGE);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            Integer quantity = cursor.getInt(quantityColumnIndex);
            Float price = cursor.getFloat(priceColumnIndex);
            String imageUriString = cursor.getString(imageColumnIndex);

            // Update the views on the screen with the values from the database
            itemNameTextView.setText(name);
            itemQuantityEditText.setText(quantity.toString());
            itemPriceTextView.setText(price.toString());

            Bitmap bitmap = getBitmapFromUri(Uri.parse(imageUriString),itemImageView);
            itemImageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        itemNameTextView.setText("");
        itemQuantityEditText.setText("");
        itemPriceTextView.setText("");
        itemImageView.setImageBitmap(null);
    }



    public Bitmap getBitmapFromUri(Uri uri, ImageView view) {

        if (uri == null || uri.toString().isEmpty())
            return null;

        /* Get the dimensions of the View*/
        int targetW = view.getWidth();
        int targetH = view.getHeight();

        if (targetW == 0){
            targetW = (int) getResources().getDimension(R.dimen.image_dimension);
        }

        if (targetH == 0){
            targetH = (int) getResources().getDimension(R.dimen.image_dimension);
        }

        InputStream input = null;
        try {
            input =  getContentResolver().openInputStream(uri);

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

            input = getContentResolver().openInputStream(uri);
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
