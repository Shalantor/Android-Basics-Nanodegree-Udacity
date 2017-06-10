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
        beaches.add(new Location(R.string.elli_beach_name, R.string.elli_beach_address,
                R.drawable.elli));
        beaches.add(new Location(R.string.pavlos_beach_name, R.string.pavlos_beach_address,
                R.drawable.pavlos));
        beaches.add(new Location(R.string.tsambika_beach_name, R.string.tsambika_beach_address,
                R.drawable.tsambika));
        beaches.add(new Location(R.string.afandou_beach_name, R.string.afandou_beach_address,
                R.drawable.afandou));
        beaches.add(new Location(R.string.prasonisi_beach_name, R.string.prasonisi_beach_address,
                R.drawable.prasonisi));
        beaches.add(new Location(R.string.faliraki_beach_name, R.string.faliraki_beach_address,
                R.drawable.faliraki));
        beaches.add(new Location(R.string.quinn_beach_name, R.string.quinn_beach_address,
                R.drawable.quinn));
        beaches.add(new Location(R.string.kolympia_beach_name, R.string.kolympia_beach_address,
                R.drawable.kolymbia));
        beaches.add(new Location(R.string.pefki_beach_name, R.string.pefki_beach_address,
                R.drawable.pefki));
        beaches.add(new Location(R.string.agathi_beach_name, R.string.agathi_beach_address,
                R.drawable.agathi));


        /*Create a location adapter whose data source is the list of hotels above*/
        LocationAdapter adapter = new LocationAdapter(getActivity(), beaches);

        /*Find ListView object in rootView*/
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        /*Make list use the adapter we create above*/
        listView.setAdapter(adapter);

        return rootView;
    }

}
