package com.example.georgekaraolanis.project10_inventoryapp;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract;
import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract.InventoryEntry;

public class CatalogActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    /* Identifier for=data loader */
    private static final int INVENTORY_LOADER = 0;

    /* Adapter for the ListView */
    ItemCursorAdapter adapter;

    /*Path to image*/
    String imagePath;

    /*View for dialog*/
    View dialogView;

    /*listView*/
    ListView listView;

    /*Data of cursor*/
    Cursor cursorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        /*Permissions*/
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        /*Setup fab button to open detail activity*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.detail_view_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CatalogActivity.this);
                builder.setTitle("Enter a new item");
                dialogView = getLayoutInflater().inflate(R.layout.add_item_prompt, null);
                builder.setView(dialogView);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*EditText fields*/
                        EditText nameEditText = (EditText) dialogView.findViewById(R.id.name_input);
                        EditText quantityEditText = (EditText) dialogView.findViewById(R.id.quantity_input);
                        EditText priceEditText = (EditText) dialogView.findViewById(R.id.price_input);

                        /*Content values*/
                        ContentValues values = new ContentValues();
                        values.put(InventoryEntry.COLUMN_ITEM_NAME,nameEditText.getText().toString());
                        values.put(InventoryEntry.COLUMN_ITEM_QUANTITY,quantityEditText.getText().toString());
                        values.put(InventoryEntry.COLUMN_ITEM_PRICE,priceEditText.getText().toString());
                        values.put(InventoryEntry.COLUMN_ITEM_IMAGE,imagePath);

                        Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI,values);
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });

                /* Create the AlertDialog */
                AlertDialog alertDialog = builder.create();

                /*Add listener to choose button*/
                Button chooseButton = (Button) dialogView.findViewById(R.id.choose_button);
                chooseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select picture"),1);
                    }
                });

                alertDialog.show();
            }
        });

        /* Find the ListView*/
        listView = (ListView) findViewById(R.id.list);

        /*Adapter setup*/
        adapter = new ItemCursorAdapter(this, null);
        listView.setAdapter(adapter);

        /* Find and set empty view on the ListView*/
        View emptyView = findViewById(R.id.empty_text_view);
        listView.setEmptyView(emptyView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, DetailActivity.class);

                Uri currentUri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, id);

                /*set data and start activity*/
                intent.setData(currentUri);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        /*projection of data we want*/
        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_ITEM_NAME,
                InventoryContract.InventoryEntry.COLUMN_ITEM_IMAGE,
                InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE,
                InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY,
        };

        return new CursorLoader(this, InventoryContract.InventoryEntry.CONTENT_URI,
                projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            imagePath = data.getData().toString();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Kick off the loader
                    getLoaderManager().initLoader(INVENTORY_LOADER, null, this);
                } else {
                }
            }
        }
    }
}
