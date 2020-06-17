package com.example.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    private Button tombolActivity, tombolFindPark;
    private Button tombolProfil, tombolParkNow;
    private TextView tv_username;
    private SharedPreferences userPref;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tombolActivity = findViewById(R.id.tombolActivity);
        tombolFindPark = findViewById(R.id.tombolFindPark);
        tombolProfil = findViewById(R.id.Profile);
        tv_username = findViewById(R.id.username);
        tombolParkNow = findViewById(R.id.tombolParkNow);

        tombolActivity.setOnClickListener(this);
        tombolFindPark.setOnClickListener(this);
        tombolProfil.setOnClickListener(this);
        tombolParkNow.setOnClickListener(this);


        userPref = getSharedPreferences("user", MODE_PRIVATE);
        username = userPref.getString("username", " ");

        tv_username.setText(username);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tombolActivity:
                Intent moveActivity = new Intent(HomeScreen.this, PageAktivitas.class);
                startActivity(moveActivity);
                finish();
                break;

            case R.id.tombolParkNow:
                startActivity(new Intent(HomeScreen.this, QrCodeScannerActivity.class));
                finish();
                break;

            case R.id.tombolFindPark:
                Intent moveFindPark = new Intent(HomeScreen.this, MapCariParkir.class);
                startActivity(moveFindPark);
                finish();
                break;

            case R.id.Profile:
                Intent moveProfile = new Intent(HomeScreen.this, Profile.class);
                startActivity(moveProfile);
                finish();
                break;
    }
    }
}