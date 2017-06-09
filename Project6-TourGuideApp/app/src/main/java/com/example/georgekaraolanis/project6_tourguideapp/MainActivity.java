package com.example.georgekaraolanis.project6_tourguideapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Show the main activity's xml file*/
        setContentView(R.layout.activity_main);

        /*Get the viewPager*/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        /*Create an adapter that knows which fragment should be shown on each page*/
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        /*Set the adapter onto the view pager*/
        viewPager.setAdapter(adapter);

        /*Get the tab layout*/
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        /*Connect TabLayout with view pager*/
        tabLayout.setupWithViewPager(viewPager);
    }
}
