package com.example.georgekaraolanis.project8_newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

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

        /*Get list of news*/
        List<News> newsList = QueryUtils.getNewsData(urlString);
        return newsList;
    }
}
