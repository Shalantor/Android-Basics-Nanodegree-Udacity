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
import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

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

                try {
                    int rowsAffected = getContentResolver().update(currentUri, values, null, null);
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

                try {
                    int rowsAffected = getContentResolver().update(currentUri, values, null, null);
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

                /*Alert dialog*/
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setMessage("Delete item?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*Delete item*/
                        deleteItem();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*Cancel deletion*/
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });

                /*Show dialog*/
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

                /*send intent for email*/
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

        /*Projection*/
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_ITEM_NAME,
                InventoryEntry.COLUMN_ITEM_QUANTITY,
                InventoryEntry.COLUMN_ITEM_PRICE,
                InventoryEntry.COLUMN_ITEM_IMAGE };

        return new CursorLoader(this, currentUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        /*If cursor null or no items return*/
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            /*get columns */
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_PRICE);
            int imageColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_IMAGE);

            /*get values*/
            String name = cursor.getString(nameColumnIndex);
            Integer quantity = cursor.getInt(quantityColumnIndex);
            Float price = cursor.getFloat(priceColumnIndex);
            String imageUriString = cursor.getString(imageColumnIndex);

            /* Update the views on the screen with the values from the database*/
            itemNameTextView.setText(name);
            itemQuantityEditText.setText(quantity.toString());
            itemPriceTextView.setText(price.toString());

            Bitmap bitmap = Utils.getBitmapFromUri(Uri.parse(imageUriString),itemImageView,this);
            itemImageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        /* clear data*/
        itemNameTextView.setText("");
        itemQuantityEditText.setText("");
        itemPriceTextView.setText("");
        itemImageView.setImageBitmap(null);
    }

    /*Delete an item from database*/
    private void deleteItem() {
        if (currentUri != null) {
            /*Delete*/
            int rowsDeleted = getContentResolver().delete(currentUri, null, null);

            /*Check result*/
            if (rowsDeleted == 0) {
                Toast.makeText(this, "Couldn't delete Item",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Item deleted",
                        Toast.LENGTH_SHORT).show();
            }
        }

        /*Close activity*/
        finish();
    }
}
