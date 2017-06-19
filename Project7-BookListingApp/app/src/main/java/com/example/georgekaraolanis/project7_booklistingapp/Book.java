package com.example.georgekaraolanis.project7_booklistingapp;

import android.graphics.Bitmap;

import java.util.ArrayList;

/*Book class to represent a list item*/
public class Book {

    /*A book can have more than 1 authors*/
    private ArrayList<String> authors;

    /*Book title*/
    private String title;

    /*Book thumbnail*/
    private Bitmap thumbnail;

    /*Constructor*/
    public Book(ArrayList<String> authors, String title, Bitmap thumbnail){
        this.authors = authors;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }
}

