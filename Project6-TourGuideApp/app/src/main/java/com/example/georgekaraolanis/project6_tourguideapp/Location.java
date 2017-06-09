package com.example.georgekaraolanis.project6_tourguideapp;


public class Location {

    /*Variables of location*/
    private int nameId = NO_SOURCE_PROVIDED;
    private int phoneNumberId = NO_SOURCE_PROVIDED;
    private int addressId = NO_SOURCE_PROVIDED;
    private int descriptionId = NO_SOURCE_PROVIDED;
    private int ratingId = NO_SOURCE_PROVIDED;
    private int imageResourceId = NO_SOURCE_PROVIDED;
    private String locationType;

    /* Constant value that represents no resource was provided for this word */
    private static final int NO_SOURCE_PROVIDED = -1;

    /*Constant for string values used in the type variable*/
    public static final String RESTAURANT = "restaurant";
    public static final String HOTEL = "hotel";
    public static final String BEACH = "beach";
    public static final String ATTRACTION = "attraction";

    /*Constructor, initiate variables for Restaurants*/
    public Location(int nameId,int phoneNumberId,int addressId,
                    int descriptionId,int ratingId){
        this.nameId = nameId;
        this.descriptionId = descriptionId;
        this.addressId = addressId;
        this.phoneNumberId = phoneNumberId;
        this.ratingId = ratingId;
        locationType = RESTAURANT;
    }

    /*Constructor, initiate variables for Hotels*/
    public Location(int nameId,int phoneNumberId,int addressId, int ratingId){
        this.nameId = nameId;
        this.phoneNumberId = phoneNumberId;
        this.addressId = addressId;
        this.ratingId = ratingId;
        locationType = HOTEL;
    }

    /*Constructor, initiate variables for Beaches*/
    public Location(int nameId,int addressId, int imageResourceId){
        this.nameId = nameId;
        this.addressId = addressId;
        this.imageResourceId = imageResourceId;
        locationType = BEACH;
    }

    /*Constructor, initiate variables for attractions*/
    public Location(int nameId, int imageResourceId){
        this.nameId = nameId;
        this.imageResourceId = imageResourceId;
        locationType = ATTRACTION;
    }

    public int getNameId() {
        return nameId;
    }

    public int getPhoneNumberId() {
        return phoneNumberId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getLocationType() {
        return locationType;
    }
}
