package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {
    private Button tombolActivity;
    private Button tombolFindPark;

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

        tombolFindPark = (Button) findViewById(R.id.tombolFindPark);
        tombolFindPark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openfindpark();
            }
        });
    }

    public void openPageAktivitas() {
        Intent intent = new Intent(this, PageAktivitas.class);
        startActivity(intent);
    }

    public void openfindpark() {
        Intent intent = new Intent(this, findpark.class);
        startActivity(intent);
    }
}

