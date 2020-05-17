package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokrpl.parkuy.R;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{
    LinearLayout btnBack, btnLanjut;
    TextInputLayout layout_name, layout_username, layout_email, layout_password, layout_notelp;
    TextInputEditText input_name, input_username, input_email, input_password, input_notelp;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        
        layout_name = findViewById(R.id.layoutinputname);
        layout_username = findViewById(R.id.layoutinputusername);
        layout_email = findViewById(R.id.layoutinputemail);
        layout_password = findViewById(R.id.layoutinputpass);
        layout_notelp = findViewById(R.id.layoutinputnotelp);
        btnBack = findViewById(R.id.btn_back);
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

        input_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!input_username.getText().toString().isEmpty()){
                    layout_username.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!input_name.getText().toString().isEmpty()){
                    layout_name.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_notelp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!input_notelp.getText().toString().isEmpty()){
                    layout_notelp.setErrorEnabled(false);
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
                Intent moveBack = new Intent(RegisterPage.this, LandingPage.class);
                startActivity(moveBack);
                finish();
                break;
            case R.id.lanjutbtn:
                validate();
                break;
        }
    }

    private boolean validate() {
        if(input_name.getText().toString().isEmpty()){
            layout_name.setErrorEnabled(true);
            layout_name.setError("Name is required");
            return false;
        }

        if(input_username.getText().toString().isEmpty()){
            layout_username.setErrorEnabled(true);
            layout_username.setError("Username is required");
            return false;
        }

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

        if(input_notelp.getText().toString().isEmpty()){
            layout_notelp.setErrorEnabled(true);
            layout_notelp.setError("Phone Number is required");
            return false;
        }
        return true;
    }
}
