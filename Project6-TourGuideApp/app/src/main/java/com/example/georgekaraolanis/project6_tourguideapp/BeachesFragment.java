package com.example.georgekaraolanis.project6_tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BeachesFragment extends Fragment {


    public BeachesFragment() {
        /*Required empty public constructor*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list,container,false);

        /*TODO:Create list of beaches, add items later*/
        final ArrayList<Location> beaches = new ArrayList<>();

        /*Create a location adapter whose data source is the list of hotels above*/
        LocationAdapter adapter = new LocationAdapter(getActivity(), beaches);

        /*Find ListView object in rootView*/
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        /*Make list use the adapter we create above*/
        listView.setAdapter(adapter);

        return rootView;
    }

}
