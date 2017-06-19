package com.example.georgekaraolanis.project7_booklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*URL to read from*/
    private static final String QUERY_URL = "  https://www.googleapis.com/books/v1/volumes?q=";

    /*Maximum number of items to get from query. In API documentation it is
      stated that its maximum value is 40, so we will use that.*/
    private static final String MAX_RESULTS_PARAMETER = "&maxResults=40";

    /*AsyncTask for network operations*/
    private AsyncTask bookAsyncTask;

    /*Adapter for listview*/
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Adapter for ListView*/
        ListView bookListView = (ListView) findViewById(R.id.list);
        adapter = new BookAdapter(this,new ArrayList<Book>());
        bookListView.setAdapter(adapter);

        /*Add click listener to button*/
        Button searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Get text from EditText*/
                EditText bookEditText = (EditText) findViewById(R.id.search_field);

                /*Get book name the user entered*/
                String bookName = bookEditText.getText().toString();

                /*Create asyncTask and execute*/
                BookAsyncTask bookAsyncTask = new BookAsyncTask();
                bookAsyncTask.execute(QUERY_URL + bookName + MAX_RESULTS_PARAMETER);
            }
        });
    }

    private class BookAsyncTask extends AsyncTask<String,Void,List<Book>>{

        @Override
        protected List<Book> doInBackground(String... urls){

            /*Check if first url is null or if there are no urls*/
            if (urls.length < 1 || urls[0] == null){
                return null;
            }

            List<Book> books = QueryUtils.fetchBookData(urls[0]);
            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books){

            /*Clear adapter*/
            adapter.clear();

            if(books != null && !books.isEmpty()){
                adapter.addAll(books);
            }
        }


    }

}
