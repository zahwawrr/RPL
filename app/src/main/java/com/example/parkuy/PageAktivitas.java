package com.example.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PageAktivitas extends AppCompatActivity {
    private Button tombolback;
    private Button tombolakhiriparkir;
    SharedPreferences resep, parkir;
    private TextView hargaparkir, namaparkiran, alamatparkir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        hargaparkir = findViewById(R.id.nombiaya);
        namaparkiran = findViewById(R.id.namaparkir);
        alamatparkir = findViewById(R.id.alamatpakir);

        resep = getApplicationContext().getSharedPreferences("receipt_parkir", MODE_PRIVATE);
        parkir = getApplicationContext().getSharedPreferences("parkir", MODE_PRIVATE);

        hargaparkir.setText(resep.getString("harga",""));
        alamatparkir.setText(parkir.getString("alamatParkir", ""));
        namaparkiran.setText(parkir.getString("namaParkir",""));


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
                switch (v.getId()) {

                    case R.id.tombolakhiriparkir:
                    modal_bottom_akhiriparkir bottom_akhiriparkir = new modal_bottom_akhiriparkir();
                    bottom_akhiriparkir.show(getSupportFragmentManager(), "modalMenu");
                    break;

                    case R.id.tombolback:
                    openHomeScreen();
                }
            }
        });
    }

    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
