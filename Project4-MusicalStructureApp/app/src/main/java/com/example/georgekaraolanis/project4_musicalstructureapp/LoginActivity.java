package com.example.georgekaraolanis.project4_musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Get Button Views*/
        Button songListButton = (Button) findViewById(R.id.song_list_button);

        /*Add listener*/
        songListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songListIntent = new Intent(LoginActivity.this,ListSongsActivity.class);
                startActivity(songListIntent);
            }
        });

    }
}
