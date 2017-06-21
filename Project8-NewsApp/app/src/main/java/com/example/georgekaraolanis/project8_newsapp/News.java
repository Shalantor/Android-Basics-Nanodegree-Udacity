package com.example.georgekaraolanis.project8_newsapp;

public class News {

    /*Title of News*/
    private String title;

    /*News Section*/
    private String section;

    /*Date of publication*/
    private String date;

    /*Author of article*/
    private String author;

    /*Url of website with those news*/
    private String url;

    public News(String title, String section, String date,
                String author, String url){
        this.title = title;
        this.section = section;
        this.author = author;
        this.url = url;

        /*Process date data*/
        date = date.replace("T"," ");
        date = date.replace("Z"," ");
        this.date = date.substring(0,date.lastIndexOf(':'));

    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }
}
