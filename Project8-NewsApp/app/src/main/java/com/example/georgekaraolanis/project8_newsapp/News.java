package com.example.georgekaraolanis.project8_newsapp;

public class News {

    /*Title of News*/
    private String title;

    /*News Section*/
    private String section;

    /*Date of publication*/
    private long timeInMilliseconds;

    /*Author of article*/
    private String article;

    /*Url of website with those news*/
    private String url;

    public News(String title, String section, long timeInMilliseconds,
                String article, String url){
        this.title = title;
        this.section = section;
        this.timeInMilliseconds = timeInMilliseconds;
        this.article = article;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getArticle() {
        return article;
    }

    public String getUrl() {
        return url;
    }
}
