package com.example.georgekaraolanis.project8_newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    /*Log tag*/
    private static final String LOG_TAG = NewsActivity.class.getName();

    /*URL to get data from*/
    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?q=technology&api-key=test&show-tags=contributor&page-size=20";

    /*Loader id*/
    private static final int NEWS_LOADER_ID = 1;

    /* Adapter for the list of earthquakes */
    private NewsAdapter adapter;

    /* TextView that is displayed when the list is empty */
    private TextView emptyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        /*Get reference to list*/
        ListView newsListView = (ListView) findViewById(R.id.list);

        /*Set empty TextView*/
        emptyTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(emptyTextView);

        /*ArrayAdapter and set it to ListView*/
        adapter = new NewsAdapter(this,new ArrayList<News>());
        newsListView.setAdapter(adapter);

        /*Add click listener to items in ListView to open the browser*/
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Get item*/
                News newsItem = adapter.getItem(position);

                /*Make string to URI*/
                Uri newsUri = Uri.parse(newsItem.getUrl());

                /*Create intent and send*/
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);

            }
        });

        /*Check network state*/
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            /* Get a reference to the LoaderManager, in order to interact with loaders.*/
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            /*If no connection display error*/
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            /*Show error*/
            emptyTextView.setText("No net");
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        /* Create a new loader for the given URL*/
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        /*Hide loading indicator*/
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        /* Display no news found message*/
        emptyTextView.setText("Now news");

        /*Clear adapter*/
        adapter.clear();

        /*Update ListView*/
        if (newsList != null && !newsList.isEmpty()) {
            adapter.addAll(newsList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        /*Clear existing data because of reset*/
        adapter.clear();
    }

}
