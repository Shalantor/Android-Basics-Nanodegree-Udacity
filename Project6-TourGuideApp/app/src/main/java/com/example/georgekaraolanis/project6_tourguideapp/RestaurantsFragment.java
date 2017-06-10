package com.example.georgekaraolanis.project6_tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        /*Required empty public constructor*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list,container,false);

        /*TODO:Create list of restaurants, add items later*/
        final ArrayList<Location> restaurants = new ArrayList<>();
        restaurants.add( new Location(R.string.mavrikos_restaurant_name,R.string.mavrikos_restaurant_description,
                R.string.mavrikos_restaurant_address,R.string.mavrikos_restaurant_phone,
                R.string.mavrikos_restaurant_rating) );
        restaurants.add( new Location(R.string.tanam_restaurant_name,R.string.tanam_restaurant_description,
                R.string.tanam_restaurant_address,R.string.tanam_restaurant_phone,
                R.string.tanam_restaurant_rating) );
        restaurants.add( new Location(R.string.capricci_restaurant_name,R.string.capricci_restaurant_description,
                R.string.capricci_restaurant_address,R.string.capricci_restaurant_phone,
                R.string.capricci_restaurant_rating) );
        restaurants.add( new Location(R.string.sofia_restaurant_name,R.string.sofia_restaurant_description,
                R.string.sofia_restaurant_address,R.string.sofia_restaurant_phone,
                R.string.sofia_restaurant_rating) );
        restaurants.add( new Location(R.string.marco_restaurant_name,R.string.marco_restaurant_description,
                R.string.marco_restaurant_address,R.string.marco_restaurant_phone,
                R.string.marco_restaurant_rating) );
        restaurants.add( new Location(R.string.valentina_restaurant_name,R.string.valentina_restaurant_description,
                R.string.valentina_restaurant_address,R.string.valentina_restaurant_phone,
                R.string.valentina_restaurant_rating) );
        restaurants.add( new Location(R.string.konstantin_restaurant_name,R.string.konstantin_restaurant_description,
                R.string.konstantin_restaurant_address,R.string.konstantin_restaurant_phone,
                R.string.konstantin_restaurant_rating) );
        restaurants.add( new Location(R.string.pegasus_restaurant_name,R.string.pegasus_restaurant_description,
                R.string.pegasus_restaurant_address,R.string.pegasus_restaurant_phone,
                R.string.pegasus_restaurant_rating) );
        restaurants.add( new Location(R.string.manolis_restaurant_name,R.string.manolis_restaurant_description,
                R.string.manolis_restaurant_address,R.string.manolis_restaurant_phone,
                R.string.manolis_restaurant_rating) );
        restaurants.add( new Location(R.string.tsambikos_restaurant_name,R.string.tsambikos_restaurant_description,
                R.string.tsambikos_restaurant_address,R.string.tsambikos_restaurant_phone,
                R.string.tsambikos_restaurant_rating ) );

        /*Create a location adapter whose data source is the list of restaurants above*/
        LocationAdapter adapter = new LocationAdapter(getActivity(), restaurants);

        /*Find ListView object in rootView*/
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        /*Make list use the adapter we create above*/
        listView.setAdapter(adapter);

        return rootView;
    }

}
