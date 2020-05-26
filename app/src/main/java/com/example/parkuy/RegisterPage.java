package com.example.parkuy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;


public class RegisterPage extends AppCompatActivity implements View.OnClickListener{
    LinearLayout btnBack, btnLanjut;
    TextInputLayout layout_name, layout_username, layout_email, layout_password, layout_notelp;
    TextInputEditText input_name, input_username, input_email, input_password, input_notelp;
    SharedPreferences userPref;

    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        
        layout_name = findViewById(R.id.layoutinputname);
        layout_username = findViewById(R.id.layoutinputusername);
        layout_email = findViewById(R.id.layoutinputemail);
        layout_password = findViewById(R.id.layoutinputpass);
        layout_notelp = findViewById(R.id.layoutinputnotelp);
        input_email = findViewById(R.id.inputemail);
        input_password = findViewById(R.id.inputpass);
        input_username = findViewById(R.id.inputusername);
        input_name = findViewById(R.id.inputname);
        input_notelp = findViewById(R.id.inputnotelp);
        btnBack = findViewById(R.id.btn_back);
        btnLanjut = findViewById(R.id.lanjutbtn);
        btnBack.setOnClickListener(this);
        btnLanjut.setOnClickListener(this);
        userPref = getSharedPreferences("user", MODE_PRIVATE);

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
                if(validate()){
                    register();
                }
                break;
        }
    }

    private void register() {
        StringRequest request = new StringRequest(Request.Method.POST, Constants.REGISTER, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    JSONObject user = object.getJSONObject("user");
                    SharedPreferences userPref = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", object.getString("token"));
                    editor.putString("name", user.getString("name"));
                    editor.putString("id", user.getString("id"));
                    editor.putString("username", user.getString("username"));
                    editor.putString("email", user.getString("email"));
                    editor.putString("notelp", user.getString("notelp"));
                    editor.apply();
                    Toast.makeText(RegisterPage.this, "Register Success", Toast.LENGTH_SHORT).show();
                    Intent toLogin = new Intent(RegisterPage.this, LoginPage.class);
                    startActivity(toLogin);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            error.printStackTrace();

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map  = new HashMap<>();
                map.put("name", input_name.getText().toString().trim());
                map.put("username", input_username.getText().toString().trim());
                map.put("email", input_email.getText().toString().trim());
                map.put("password", input_password.getText().toString());
                map.put("notelp", input_notelp.getText().toString().trim());
                return map;
            }


        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
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
