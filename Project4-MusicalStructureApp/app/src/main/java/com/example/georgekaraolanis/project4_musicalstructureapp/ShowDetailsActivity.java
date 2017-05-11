package com.example.georgekaraolanis.project4_musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        /*Get Button Views*/
        Button playSongButton = (Button) findViewById(R.id.play_song_button);

        /*Add listener to it*/
        playSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSongIntent = new Intent(ShowDetailsActivity.this,NowPlayingActivity.class);
                startActivity(playSongIntent);
            }
        });

    }
}
