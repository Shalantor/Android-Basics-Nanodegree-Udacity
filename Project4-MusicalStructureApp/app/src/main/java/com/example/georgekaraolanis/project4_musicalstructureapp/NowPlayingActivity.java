package com.example.georgekaraolanis.project4_musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        /*Get Button Views*/
        Button songListButton = (Button) findViewById(R.id.song_list_button);
        Button showDetailsButton = (Button) findViewById(R.id.show_details_button);

        /*Add listeners to each Button*/
        songListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songListIntent = new Intent(NowPlayingActivity.this,ListSongsActivity.class);
                startActivity(songListIntent);
            }
        });

        showDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showDetailsIntent = new Intent(NowPlayingActivity.this,ShowDetailsActivity.class);
                startActivity(showDetailsIntent);
            }
        });
    }
}
