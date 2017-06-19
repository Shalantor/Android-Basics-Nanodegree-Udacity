package com.example.georgekaraolanis.project7_booklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*URL to read from*/
    private static final String QUERY_URL = "  https://www.googleapis.com/books/v1/volumes?q=";

    /*Maximum number of items to get from query. In API documentation it is
      stated that its maximum value is 40, so we will use that.*/
    private static final String MAX_RESULTS_PARAMETER = "&maxResults=40";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
