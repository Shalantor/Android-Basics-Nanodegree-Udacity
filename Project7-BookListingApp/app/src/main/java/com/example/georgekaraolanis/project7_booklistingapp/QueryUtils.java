package com.example.georgekaraolanis.project7_booklistingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    /*Tag for logging*/
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /* Empty constructor */
    private QueryUtils() {
    }

    /*Get data about books in query*/
    public static List<Book> fetchBookData(String urlString){

        /*Create the url object*/
        URL url = createUrl(urlString);

        /*Make an Http Request and get a JSON response*/
        String JSONResponse = null;
        JSONResponse = makeHttpRequest(url);

        /*Extract relevant data from JSONResponse and create a list of Book objects*/
        List<Book> books = processJSONData(JSONResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    /*Create the url object*/
    private static URL createUrl(String urlString){
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            Log.e(LOG_TAG, "Malformed URL ", ex);
        }
        return url;
    }

    /*Make the Http request*/
    private static String makeHttpRequest(URL url){
        String JSONResponse = "";

        /*Check if url is null, if so return immediately*/
        if (url == null){
            return JSONResponse;
        }

        /*Make the connection and get an inpustream to read from*/
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                JSONResponse = readData(inputStream);
            } else {
                Log.e(LOG_TAG, "Response from server: " + urlConnection.getResponseCode());
            }
        } catch (IOException ex) {
            Log.e(LOG_TAG, "IOException when retrieving data ", ex);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch(IOException ex){
                    Log.e(LOG_TAG, "IOException when closing input stream ", ex);
                }
            }
        }
        return JSONResponse;
    }

    /*Read data from input stream*/
    private static String readData(InputStream inputStream) throws IOException {

        /*String to be returned*/
        StringBuilder output = new StringBuilder();

        /*Read from input stream and append data to StringBuilder*/
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

    /*Get data from JSONResponse*/
    private static List<Book> processJSONData(String bookJSON){

        /*If the JSON object is empty or null return immediately*/
        if (TextUtils.isEmpty(bookJSON)){
            return null;
        }

        /*Create a List to store the Book data*/
        List<Book> books = new ArrayList<>();

        /*Get data from JSON Object*/
        try{

            /*Create JSON Object*/
            JSONObject response = new JSONObject(bookJSON);

            /*Get array of books*/
            JSONArray bookArray = response.getJSONArray("items");

            /*Loop over array and get data*/
            for(int i = 0; i < bookArray.length(); i++){

                /*Get volume info*/
                JSONObject volumeInfo = bookArray.getJSONObject(i).getJSONObject("volumeInfo");

                /*Get title*/
                String title = volumeInfo.getString("title");

                /*Get List of authors */
                JSONArray authorsJSON = volumeInfo.getJSONArray("authors");
                ArrayList<String> authors = new ArrayList<>();

                for(int j = 0; j < authorsJSON.length(); j++){
                    authors.add(authorsJSON.getString(i));
                }

                /*Get image bitmap*/
                String thumbnailUrl = volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");
                Bitmap thumbnail = getThumbnail(thumbnailUrl);

                /*Add to list*/
                books.add(new Book(authors,title,thumbnail));

            }

        }
        catch(JSONException ex){
            Log.e(LOG_TAG, "IOException when retrieving data from JSON Object ", ex);
        }
        return books;
    }

    /*Get image from url*/
    private static Bitmap getThumbnail(String urlString){

        /*Create url*/
        URL url = createUrl(urlString);
        Bitmap bitmap = null;

        /*Open connection and read image*/
        try {
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch(IOException ex){
            Log.e(LOG_TAG, "IOException when retrieving Image ", ex);
        }

        return bitmap;
    }
}
