package com.example.georgekaraolanis.project4_musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreatePlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        /*Get Button Views*/
        Button songListButton = (Button) findViewById(R.id.song_list_button);
        Button searchSongButton = (Button) findViewById(R.id.search_song_button);
        Button playSongButton = (Button) findViewById(R.id.play_song_button);

        /*Add Listeners to each button*/
        songListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songListIntent = new Intent(CreatePlaylistActivity.this,ListSongsActivity.class);
                startActivity(songListIntent);
            }
        });

        searchSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchSongIntent = new Intent(CreatePlaylistActivity.this,SearchSongActivity.class);
                startActivity(searchSongIntent);
            }
        });

        playSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSongIntent = new Intent(CreatePlaylistActivity.this,NowPlayingActivity.class);
                startActivity(playSongIntent);
            }
        });


    }
}
