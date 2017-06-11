package com.example.georgekaraolanis.project6_tourguideapp;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class LocationAdapter extends ArrayAdapter<Location> {

    /*Constructor, call constructor of super class*/
    public LocationAdapter(Context context, ArrayList<Location> locations){
        super(context,0,locations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        /*First check if a view is reused, else inflate it's layout*/
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        /*Get the location object located at this position*/
        Location currentLocation = getItem(position);

        /*Get TextViews and ImageView*/
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description);
        TextView addressTextView = (TextView) listItemView.findViewById(R.id.address);
        TextView phoneNumberTextView = (TextView) listItemView.findViewById(R.id.phone_number);
        TextView ratingTextView = (TextView) listItemView.findViewById(R.id.rating);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        /*Now get its type and based on that, show or hide some Views*/
        String type = currentLocation.getLocationType();

        /*All location items do have a name*/
        nameTextView.setText(currentLocation.getNameId());

        /*Check type*/
        if( type.equals(Location.RESTAURANT) ){
            /*Show corresponding text in TextViews*/
            descriptionTextView.setText(currentLocation.getDescriptionId());
            addressTextView.setText(currentLocation.getAddressId());
            phoneNumberTextView.setText(currentLocation.getPhoneNumberId());
            ratingTextView.setText(currentLocation.getRatingId());

            /*Hide ImageView*/
            imageView.setVisibility(View.GONE);
        }
        else if(type.equals(Location.HOTEL)){
            /*Show corresponding text in TextViews*/
            addressTextView.setText(currentLocation.getAddressId());
            phoneNumberTextView.setText(currentLocation.getPhoneNumberId());
            ratingTextView.setText(currentLocation.getRatingId());

            /*Hide ImageView and unused TextView*/
            descriptionTextView.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
        }
        else if(type.equals(Location.BEACH)){
            /*Show corresponding text in TextViews*/
            addressTextView.setText(currentLocation.getAddressId());

            /*Hide unused TextViews*/
            descriptionTextView.setVisibility(View.GONE);
            phoneNumberTextView.setVisibility(View.GONE);
            ratingTextView.setVisibility(View.GONE);

            /*Show image of beach*/
            imageView.setImageResource(currentLocation.getImageResourceId());
            /*Make sure the view is visible*/
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            /*Show image of attraction*/
            imageView.setImageResource(currentLocation.getImageResourceId());
            /*Make sure the view is visible*/
            imageView.setVisibility(View.VISIBLE);

            /*Hide unused TextViews*/
            addressTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
            phoneNumberTextView.setVisibility(View.GONE);
            ratingTextView.setVisibility(View.GONE);
        }

        return listItemView;

    }

}
