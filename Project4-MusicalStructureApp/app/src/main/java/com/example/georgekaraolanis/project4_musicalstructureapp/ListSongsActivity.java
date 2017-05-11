package com.example.georgekaraolanis.project4_musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        /*Get Button Views*/
        Button createPlaylistButton = (Button) findViewById(R.id.create_playlist_button);
        Button searchSongButton = (Button) findViewById(R.id.search_song_button);
        Button playSongButton = (Button) findViewById(R.id.play_song_button);

        /*Add listeners to each Button*/
        searchSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchSongIntent = new Intent(ListSongsActivity.this,SearchSongActivity.class);
                startActivity(searchSongIntent);
            }
        });

        playSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSongIntent = new Intent(ListSongsActivity.this,NowPlayingActivity.class);
                startActivity(playSongIntent);
            }
        });

        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createPlaylistIntent = new Intent(ListSongsActivity.this,CreatePlaylistActivity.class);
                startActivity(createPlaylistIntent);
            }
        });

    }

}
