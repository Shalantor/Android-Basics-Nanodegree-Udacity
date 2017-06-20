package com.example.georgekaraolanis.project7_booklistingapp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*Book class to represent a list item*/
public class Book implements Parcelable{

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

    /*Constructor for parcelable */
    private Book(Parcel in){
        authors = (ArrayList<String>) in.readSerializable();
        title = in.readString();
        thumbnail = Bitmap.CREATOR.createFromParcel(in);
    }

    /*Implement methods of Parcelable*/
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(authors);
        out.writeString(title);

        /*Thumbnail is null when OnStop is invoked, check that first*/
        if (thumbnail != null) {
            thumbnail.writeToParcel(out, 0);
        }
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


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

