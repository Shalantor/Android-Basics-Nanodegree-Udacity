package com.example.georgekaraolanis.project7_booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    /*URL to read from*/
    private static final String QUERY_URL = "  https://www.googleapis.com/books/v1/volumes?q=";

    /*Maximum number of items to get from query. In API documentation it is
      stated that its maximum value is 40, so we will use that.*/
    private static final String MAX_RESULTS_PARAMETER = "&maxResults=20";

    /*Adapter for ListView*/
    private BookAdapter adapter;

    /*Current book list*/
    ArrayList<Book> currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Adapter for ListView*/
        ListView bookListView = (ListView) findViewById(R.id.list);
        adapter = new BookAdapter(this,new ArrayList<Book>());
        bookListView.setAdapter(adapter);

        /*Hide keyboard, as it pops up sometimes on screen rotation*/
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*Check if recreated*/
        if (savedInstanceState != null) {
            currentList = savedInstanceState.getParcelableArrayList("key");
            adapter.addAll(currentList);
        }

        /*Empty TextView for ListView*/
        final TextView emptyTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyTextView);

        /*Hide loading indicator and show no data message to user*/
        final ProgressBar loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(GONE);
        emptyTextView.setText(R.string.no_data_message);

        /*Add click listener to button*/
        Button searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText bookEditText = (EditText) findViewById(R.id.search_field);

                /*Hide keyboard*/
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(bookEditText.getWindowToken(), 0);

                /*Get a reference to the ConnectivityManager to check state of network connectivity*/
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                /* Get details on the currently active default data network*/
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                /*If there is a connection proceed normally*/
                if (networkInfo != null && networkInfo.isConnected()) {

                    /*Hide emptyTextView*/
                    emptyTextView.setText("");

                    /*cleat adapter*/
                    adapter.clear();

                    /*Show loading indicator*/
                    loadingIndicator.setVisibility(View.VISIBLE);

                    /*Get book name the user entered and trim leading and trailing whitespace*/
                    String bookName = bookEditText.getText().toString().trim();

                    /*Replace whitespace between words with +, like it is specified in Google book API*/
                    bookName = bookName.replace(" ", "+");

                    /*set empty string to EditText*/
                    bookEditText.setText("");

                    /*Create asyncTask and execute*/
                    BookAsyncTask bookAsyncTask = new BookAsyncTask();
                    bookAsyncTask.execute(QUERY_URL + bookName + MAX_RESULTS_PARAMETER);
                }
                else{
                    emptyTextView.setText(R.string.no_connection_message);
                }
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

            /*Hide loading Indicator*/
            ProgressBar loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(GONE);

            /*get TextView and check books*/
            TextView emptyTextView = (TextView) findViewById(R.id.empty_view);

            /*Case where we got a bad/no server response*/
            if(books == null){
                emptyTextView.setText(R.string.bad_server_response);
            }
            else if(books.isEmpty()){
                emptyTextView.setText(R.string.no_books_found);
            }
            else{
                currentList = (ArrayList<Book>) books;
                adapter.addAll(books);
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", currentList);
        super.onSaveInstanceState(outState);
    }

}
