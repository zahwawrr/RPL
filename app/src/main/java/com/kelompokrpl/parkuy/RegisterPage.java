package com.kelompokrpl.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);

        LinearLayout btnBack;
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                Intent moveBack = new Intent(RegisterPage.this, LandingPage.class);
                startActivity(moveBack);
                break;
        }
    }
}
