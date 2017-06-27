package com.example.georgekaraolanis.project10_inventoryapp;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        /*Click listeners to plus minus buttons*/
        Button minusButton = (Button) findViewById(R.id.minus_button);
        Button plusButton = (Button) findViewById(R.id.plus_button);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity = Integer.parseInt (itemQuantityEditText.getText().toString());

                /*Values items*/
                ContentValues values = new ContentValues();
                values.put(InventoryEntry.COLUMN_ITEM_QUANTITY,(quantity-1) + "");

                int rowsAffected = 0;
                try {
                    rowsAffected = getContentResolver().update(currentUri, values, null, null);
                }
                catch(IllegalArgumentException ex){
                    Toast.makeText(DetailActivity.this, ex.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity = Integer.parseInt (itemQuantityEditText.getText().toString());

                /*Values items*/
                ContentValues values = new ContentValues();
                values.put(InventoryEntry.COLUMN_ITEM_QUANTITY,(quantity+1) + "");

                int rowsAffected = 0;
                try {
                    rowsAffected = getContentResolver().update(currentUri, values, null, null);
                }
                catch(IllegalArgumentException ex){
                    Toast.makeText(DetailActivity.this, ex.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*Listener for delete button*/
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog.Builder and set the message, and click listeners
                // for the postivie and negative buttons on the dialog.
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setMessage("Delete item?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked the "Delete" button, so delete the pet.
                        deleteItem();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked the "Cancel" button, so dismiss the dialog
                        // and continue editing the pet.
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });

                // Create and show the AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        /*Listener for order button*/
        Button orderButton = (Button) findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Get values from textviews */
                String name = itemNameTextView.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order " + name);
                intent.putExtra(Intent.EXTRA_TEXT, "Hello we need more of this item");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

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

            Bitmap bitmap = Utils.getBitmapFromUri(Uri.parse(imageUriString),itemImageView,this);
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

    private void deleteItem() {
        // Only perform the delete if this is an existing pet.
        if (currentUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(currentUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, "Couldn't delete Item",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this,"Item deleted",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}
