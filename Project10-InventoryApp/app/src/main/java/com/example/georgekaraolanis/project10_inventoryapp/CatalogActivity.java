package com.example.georgekaraolanis.project10_inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.georgekaraolanis.project10_inventoryapp.data.InventoryContract;

public class CatalogActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    /* Identifier for=data loader */
    private static final int INVENTORY_LOADER = 0;

    /* Adapter for the ListView */
    ItemCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        /*Setup fab button to open detail activity*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.detail_view_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CatalogActivity.this);
                builder.setTitle("Enter a new item");
                View dialogView = getLayoutInflater().inflate(R.layout.add_item_prompt, null);
                builder.setView(dialogView);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
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
        ListView listView = (ListView) findViewById(R.id.list);

        /* Find and set empty view on the ListView*/
        View emptyView = findViewById(R.id.empty_text_view);
        listView.setEmptyView(emptyView);

        /*Adapter setup*/
        adapter = new ItemCursorAdapter(this, null);
        listView.setAdapter(adapter);

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
        // Kick off the loader
        getLoaderManager().initLoader(INVENTORY_LOADER, null, this);
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
}
