package com.example.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class PageAktivitas extends AppCompatActivity {
    private Button tombolback;
    private Button tombolakhiriparkir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        tombolback = (Button) findViewById(R.id.tombolback);
        tombolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });

        tombolakhiriparkir = (Button) findViewById(R.id.tombolakhiriparkir);
        tombolakhiriparkir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                modal_bottom_akhiriparkir bottom_akhiriparkir = new modal_bottom_akhiriparkir();
                bottom_akhiriparkir.show(getSupportFragmentManager(), "modalMenu");
            }
        });
    }

    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
