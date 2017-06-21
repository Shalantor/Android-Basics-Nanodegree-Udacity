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
        this.date =date;
        this.author = author;
        this.url = url;
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
