package com.example.georgekaraolanis.project4_musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);

        /*Get Button Views*/
        Button songListButton = (Button) findViewById(R.id.song_list_button);
        Button playSongButton = (Button) findViewById(R.id.play_song_button);
        Button createPlaylistButton = (Button) findViewById(R.id.create_playlist_button);

        /*Add listeners to each Button*/
        songListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songListIntent = new Intent(SearchSongActivity.this,ListSongsActivity.class);
                startActivity(songListIntent);
            }
        });

        playSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSongIntent = new Intent(SearchSongActivity.this,NowPlayingActivity.class);
                startActivity(playSongIntent);
            }
        });

        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createPlayListIntent = new Intent(SearchSongActivity.this,CreatePlaylistActivity.class);
                startActivity(createPlayListIntent);
            }
        });
    }
}
