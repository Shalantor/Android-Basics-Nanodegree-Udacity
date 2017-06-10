package com.example.georgekaraolanis.project6_tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AttractionsFragment extends Fragment {


    public AttractionsFragment() {
        /*Required empty public constructor*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list,container,false);

        /*TODO:Create list of attractions, add items later*/
        final ArrayList<Location> attractions = new ArrayList<>();
        attractions.add(new Location(R.string.palace_attraction_name,R.drawable.palace));
        attractions.add(new Location(R.string.acropolis_attraction_name,R.drawable.acropolis));
        attractions.add(new Location(R.string.monastery_attraction_name,R.drawable.filerimos));
        attractions.add(new Location(R.string.valley_attraction_name,R.drawable.valley));
        attractions.add(new Location(R.string.toy_museum_attraction_name,R.drawable.toy_museum));
        attractions.add(new Location(R.string.castle_attraction_name,R.drawable.castle_kritinia));
        attractions.add(new Location(R.string.hammam_attraction_name,R.drawable.hammam));
        attractions.add(new Location(R.string.bee_attraction_name,R.drawable.bee));
        attractions.add(new Location(R.string.springs_attraction_name,R.drawable.springs));
        attractions.add(new Location(R.string.aquarium_attraction_name,R.drawable.aquarium));





        /*Create a location adapter whose data source is the list of hotels above*/
        LocationAdapter adapter = new LocationAdapter(getActivity(), attractions);

        /*Find ListView object in rootView*/
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        /*Make list use the adapter we create above*/
        listView.setAdapter(adapter);

        return rootView;
    }

}
