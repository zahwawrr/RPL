package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mulaiparkir extends AppCompatActivity {
    private Button tombolback;
    private Button tombolmulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulaiparkir);

        tombolback = (Button) findViewById(R.id.tombolback);
        tombolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfindpark();
            }
        });

        tombolmulai = (Button) findViewById(R.id.tombolmulai);
        tombolmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAktivitas();
            }
        });
    }

    public void openfindpark() {
        Intent intent = new Intent(this, findpark.class);
        startActivity(intent);
    }

    public void openPageAktivitas() {
        Intent intent = new Intent(this, PageAktivitas.class);
        startActivity(intent);
    }
}