package com.kelompokrpl.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LandingPage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        LinearLayout btnMoveMasuk = findViewById(R.id.btn_masuk);
        btnMoveMasuk.setOnClickListener(this);
        LinearLayout btnMoveDaftar = findViewById(R.id.btn_daftar);
        btnMoveDaftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_masuk:
                Intent moveMasuk = new Intent(LandingPage.this, LoginPage.class);
                startActivity(moveMasuk);
                break;
            case R.id.btn_daftar:
                Intent moveDaftar = new Intent(LandingPage.this, RegisterPage.class);
                startActivity(moveDaftar);
                break;
        }
    }
}
