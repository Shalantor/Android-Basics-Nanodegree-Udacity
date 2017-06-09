package com.example.georgekaraolanis.project6_tourguideapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CategoryAdapter extends FragmentPagerAdapter {

    /*Context of the tour guide app*/
    private Context context;

    /*Constructor*/
    public CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    /*return the Fragment that should be displayed*/
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RestaurantsFragment();
        } else if (position == 1) {
            return new HotelsFragment();
        } else if (position == 2) {
            return new BeachesFragment();
        } else {
            return new AttractionsFragment();
        }
    }

    /*Get total numbers of pages*/
    @Override
    public int getCount(){
        return 4;
    }

    /*Titles for tabs*/
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_restaurants);
        } else if (position == 1) {
            return context.getString(R.string.category_hotels);
        } else if (position == 2) {
            return context.getString(R.string.category_beaches);
        } else {
            return context.getString(R.string.category_attractions);
        }
    }



}
