package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class findpark extends AppCompatActivity {
    private Button manualfindpark;
    private Button tombolback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpark);

        tombolback = (Button) findViewById(R.id.tombolback);
        tombolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });

        manualfindpark = (Button) findViewById(R.id.manualfindpark);
        manualfindpark.setOnClickListener(new View.OnClickListener(){
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


