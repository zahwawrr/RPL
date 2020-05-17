package com.example.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kelompokrpl.parkuy.R;

public class HomeScreen extends AppCompatActivity {
    private Button tombolActivity;

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
    }

    public void openPageAktivitas() {
        Intent intent = new Intent(this, PageAktivitas.class);
        startActivity(intent);
    }
}
