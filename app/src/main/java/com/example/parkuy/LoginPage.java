package com.example.parkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokrpl.parkuy.R;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnBack, btnLanjut;
    TextView tv_username, tv_password;
    TextInputLayout layout_email, layout_password;
    TextInputEditText input_email, input_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        tv_username = findViewById(R.id.username);
        tv_password = findViewById(R.id.password);
        input_email = findViewById(R.id.inputemail);
        input_password = findViewById(R.id.inputpass);
        layout_email = findViewById(R.id.layoutinputemail);
        layout_password = findViewById(R.id.layoutinputpass);
        btnBack = findViewById(R.id.btn_back);
        btnLanjut = findViewById(R.id.lanjutbtn);
        btnBack.setOnClickListener(this);
        btnLanjut.setOnClickListener(this);

        input_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!input_email.getText().toString().isEmpty()){
                    layout_email.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(input_password.getText().toString().length()>7){
                    layout_password.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                Intent moveBack = new Intent(LoginPage.this, LandingPage.class);
                startActivity(moveBack);
                finish();
                break;

            case R.id.lanjutbtn:
                validate();
        }
    }

    private boolean validate() {
        if(input_email.getText().toString().isEmpty()){
            layout_email.setErrorEnabled(true);
            layout_email.setError("Email is required");
            return false;
        }

        if(input_password.getText().toString().length() < 8){
            layout_password.setErrorEnabled(true);
            layout_password.setError("Required at least 8 character");
            return false;
        }
        return true;
    }
}
