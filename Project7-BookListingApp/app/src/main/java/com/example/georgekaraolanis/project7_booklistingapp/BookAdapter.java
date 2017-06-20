package com.example.georgekaraolanis.project7_booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BookAdapter extends ArrayAdapter<Book> {

    /*Constructor*/
    public BookAdapter(Context context,List<Book> books){
        super(context,0,books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        /*Get view and check if it is being reused*/
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
            R.layout.list_item,parent,false);
        }

        /*Get book at specified position*/
        Book currentBook = getItem(position);

        /*Get references to views*/
        ImageView thumbnail = (ImageView) listItemView.findViewById(R.id.thumbnail);
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        TextView authorsTextView = (TextView) listItemView.findViewById(R.id.authors);

        /*Set data in those views*/
        thumbnail.setImageBitmap(currentBook.getThumbnail());
        titleTextView.setText(currentBook.getTitle());

        /*Because a book can have multiple authors, they will be separated with commas*/
        ArrayList<String> authors = currentBook.getAuthors();
        int numAuthors = authors.size();

        /*If there is one author display his name, else process their names*/
        if(numAuthors == 1){
            authorsTextView.setText(authors.get(0));
        }
        else{

            /*First author name is used to initialize StringBuilder*/
            StringBuilder authorsText = new StringBuilder(authors.get(0));

            /*Start from second author*/
            for(int i = 1; i < numAuthors; i++){
                authorsText.append(", " + authors.get(i));
            }

            /*When finished set text in TextView*/
            authorsTextView.setText(authorsText.toString());
        }

        return listItemView;
    }
}
