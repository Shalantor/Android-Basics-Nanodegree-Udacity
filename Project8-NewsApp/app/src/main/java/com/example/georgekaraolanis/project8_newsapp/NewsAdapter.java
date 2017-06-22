package com.example.georgekaraolanis.project8_newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News>{

    /*Constructor*/
    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*check if view is being reused*/
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        /*get currentItem*/
        News currentNewsItem = getItem(position);

        /*Get references to Views*/
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        /*Set text in those TextViews*/
        titleTextView.setText(currentNewsItem.getTitle());
        sectionTextView.setText(currentNewsItem.getSection());
        authorTextView.setText(currentNewsItem.getAuthor());
        dateTextView.setText(currentNewsItem.getDate());

        return listItemView;
    }

}
