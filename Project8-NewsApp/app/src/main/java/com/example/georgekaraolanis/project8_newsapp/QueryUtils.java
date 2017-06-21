package com.example.georgekaraolanis.project8_newsapp;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    /* Tag for log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /*Empty constructor*/
    private QueryUtils() {
    }

    public static List<News> getNewsData(String urlString){

        /*Create url*/
        URL url = createUrl(urlString);

        /*Perform HTTP request to the URL and receive a JSON response back*/
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        /*Get relevant data from JSONResponse */
        List<News> newsList = getJsonData(jsonResponse);

        return null;
    }

    /*Create URL object*/
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException ex) {
            Log.e(LOG_TAG, "Problem creating the URL ", ex);
        }
        return url;
    }


    /*Make http request to given url*/
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Problem getting JSONResponse for News.", ex);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /*Read from InputStream of url*/
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /*Extract data from JSON Object*/
    private static List<News> getJsonData(String jsonNewsString){

        /*Check if JSON object is empty*/
        if (TextUtils.isEmpty(jsonNewsString)){
            return null;
        }

        /*Create list of news objects*/
        List<News> newsList = new ArrayList<News>();

        try{
            JSONObject jsonNews = new JSONObject(jsonNewsString);

            /*Get response*/
            JSONObject response = jsonNews.getJSONObject("response");

            /*Get results array*/
            JSONArray results = response.getJSONArray("results");

            /*Get info for each result*/
            for (int i =0; i < results.length(); i++){

                /*Get JSON object at that position*/
                JSONObject newsItem = results.getJSONObject(i);

                /*Get relevant data*/
                String title = newsItem.getString("webTitle");
                String section = newsItem.getString("sectionName");
                String date = newsItem.getString("webPublicationDate");
                String urlString = newsItem.getString("webUrl");

                /*Get tags*/
                JSONArray tags = newsItem.getJSONArray("tags");

                /*Get author from tags. Some news articles don't have
                * authors or contributors */
                JSONObject authorJsonObject = tags.optJSONObject(0);
                String author;
                if (authorJsonObject == null){
                    author = "";
                }
                else{
                    author = authorJsonObject.getString("webTitle");
                }

                /*Create News item*/
                News newsObject = new News(title,section,date,author,urlString);

                /*Add to list*/
                newsList.add(newsObject);

            }

        }catch(JSONException ex){
            Log.e(LOG_TAG, "Problem parsing the news JSON results", ex);
        }

        return newsList;
    }

}
