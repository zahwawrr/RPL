package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ParkNow extends AppCompatActivity {
    private Button manualParkNow;
    private Button tombolback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parknow);

        tombolback = (Button) findViewById(R.id.tombolback);
        tombolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });

        manualParkNow = (Button) findViewById(R.id.manualParkNow);
        manualParkNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupmanual bottom_popupmanual = new popupmanual();
                bottom_popupmanual.show(getSupportFragmentManager(), "modalMenu");
            }
        });
    }

    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}


