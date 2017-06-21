package com.example.georgekaraolanis.project8_newsapp;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>>{

    /*URL to query*/
    private String urlString;

    public NewsLoader(Context context, String url) {
        super(context);
        urlString = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (urlString == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<News> newsList = QueryUtils.getNewsData(urlString);
        return newsList;
    }
}
