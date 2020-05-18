package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Resi extends AppCompatActivity {
    private Button tombolback;
    private Button tombolbayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resiparkir);

        tombolback = (Button) findViewById(R.id.tombolback);
        tombolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageAktivitas();
            }
        });

        tombolbayar = (Button) findViewById(R.id.tombolbayar);
        tombolbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSampaiJumpa();
            }
        });
    }

    public void PageAktivitas() {
        Intent intent = new Intent(this, PageAktivitas.class);
        startActivity(intent);
    }

    public void openSampaiJumpa() {
        Intent intent = new Intent(this, sampaijumpa.class);
        startActivity(intent);
    }
}