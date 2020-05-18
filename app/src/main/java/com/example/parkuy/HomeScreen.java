package com.example.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    private Button tombolActivity;
    private Button tombolParkNow;
    private Button profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        tombolActivity = (Button) findViewById(R.id.tombolActivity);
        tombolActivity.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v){
            openPageAktivitas();
        }
        });

        tombolParkNow = (Button) findViewById(R.id.tombolParkNow);
        tombolParkNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openParkNow();
            }
        });

        profile = (Button) findViewById(R.id.Profile);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProfile();
            }
        });
    }

    public void openPageAktivitas() {
        Intent intent = new Intent(this, PageAktivitas.class);
        startActivity(intent);
    }

    public void openParkNow() {
        Intent intent = new Intent(this, ParkNow.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
