package com.example.georgekaraolanis.project8_newsapp;

public class News {

    /*Title of News*/
    private String title;

    /*News Section*/
    private String section;

    /*Date of publication*/
    private String date;

    /*Author of article*/
    private String article;

    /*Url of website with those news*/
    private String url;

    public News(String title, String section, String date,
                String article, String url){
        this.title = title;
        this.section = section;
        this.date =date;
        this.article = article;
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

    public String getArticle() {
        return article;
    }

    public String getUrl() {
        return url;
    }
}
