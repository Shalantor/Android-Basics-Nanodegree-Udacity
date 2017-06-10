package com.example.georgekaraolanis.project6_tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HotelsFragment extends Fragment {


    public HotelsFragment() {
        /* Required empty public constructor*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list,container,false);

        /*TODO:Create list of hotels, add items later*/
        final ArrayList<Location> hotels = new ArrayList<>();
        hotels.add(new Location(R.string.plaza_hotel_name, R.string.plaza_hotel_address,
                R.string.plaza_hotel_phone, R.string.plaza_hotel_rating));
        hotels.add(new Location(R.string.mitsis_hotel_name, R.string.mitsis_hotel_address,
                R.string.mitsis_hotel_phone, R.string.mitsis_hotel_rating));
        hotels.add(new Location(R.string.aqua_hotel_name, R.string.aqua_hotel_address,
                R.string.aqua_hotel_phone, R.string.aqua_hotel_rating));
        hotels.add(new Location(R.string.princess_hotel_name, R.string.princess_hotel_address,
                R.string.princess_hotel_phone, R.string.princess_hotel_rating));
        hotels.add(new Location(R.string.atrium_hotel_name, R.string.atrium_hotel_address,
                R.string.atrium_hotel_phone, R.string.atrium_hotel_rating));
        hotels.add(new Location(R.string.mare_hotel_name, R.string.mare_hotel_address,
                R.string.mare_hotel_phone, R.string.mare_hotel_rating));
        hotels.add(new Location(R.string.elysium_hotel_name, R.string.elysium_hotel_address,
                R.string.elysium_hotel_phone, R.string.elysium_hotel_rating));
        hotels.add(new Location(R.string.porta_hotel_name, R.string.porta_hotel_address,
                R.string.porta_hotel_phone, R.string.porta_hotel_rating));
        hotels.add(new Location(R.string.horizon_hotel_name, R.string.horizon_hotel_address,
                R.string.horizon_hotel_phone, R.string.horizon_hotel_rating));
        hotels.add(new Location(R.string.amathus_hotel_name, R.string.amathus_hotel_address,
                R.string.amathus_hotel_phone, R.string.amathus_hotel_rating));

        /*Create a location adapter whose data source is the list of hotels above*/
        LocationAdapter adapter = new LocationAdapter(getActivity(), hotels);

        /*Find ListView object in rootView*/
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        /*Make list use the adapter we create above*/
        listView.setAdapter(adapter);

        return rootView;
    }

}
