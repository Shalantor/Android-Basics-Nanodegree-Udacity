package com.example.georgekaraolanis.project6_tourguideapp;


public class Location {

    /*Variables of location*/
    private String phoneNumber = null;
    private String address = null;
    private String rating = null;
    private String description = null;
    private int imageResourceId = NO_IMAGE_PROVIDED;

    /* Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /*Constructor, initiate variables for Restaurants*/
    public Location(String phoneNumber,String address,String rating,
                    String description){
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rating = rating;
        this.description = description;;
    }

    /*Constructor, initiate variables For Hotels*/
    public Location(String phoneNumber,String address,String rating){
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rating = rating;
    }

    /*Constructor, initiate variables for Beaches*/
    public Location(String address,int imageResourceId){
        this.address = address;
        this.imageResourceId = imageResourceId;
    }

    /*Constructor, initiate variables for Attractions*/
    public Location(String address,int imageResourceId,String description){
        this.address = address;
        this.imageResourceId = imageResourceId;
        this.description = description;
    }

    /*Getters and setters*/
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

}
