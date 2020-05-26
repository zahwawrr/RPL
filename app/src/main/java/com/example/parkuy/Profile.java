package com.example.parkuy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parkuy.Model.User;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout btn_back;
    private String TAG = "profile";
    private CircleImageView fotoProfile;
    private Button btn_logout;
    private TextView tv_name;
    private String nameView, display;
    private SharedPreferences userPref;
    private Bitmap bitmap = null;
    private static final int GALLERY_ADD_PROFILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fotoProfile = findViewById(R.id.pic_register);
        btn_logout = findViewById(R.id.btn_logout);
        tv_name = findViewById(R.id.txt_nama);
        btn_back = findViewById(R.id.btn_back);
        userPref =getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        nameView = userPref.getString("name"," ");
        tv_name.setText(nameView);
        getPhoto();

        btn_back.setOnClickListener(this);
        fotoProfile.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pic_register:
                Intent pic = new Intent(Intent.ACTION_PICK);
                pic.setType("image/*");
                startActivityForResult(pic, GALLERY_ADD_PROFILE);


                break;

            case R.id.btn_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to logout?");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;

            case R.id.btn_back:
                startActivity(new Intent(Profile.this, HomeScreen.class));
                finish();
        }
    }

    private void getPhoto() {
        StringRequest request = new StringRequest(Request.Method.GET, Constants.getPhoto,response -> {
            try {
                JSONObject object = new JSONObject(response);
                JSONObject user = object.getJSONObject("user");
                Picasso.get().load(Constants.URL+"storage/profiles/"+user.getString("photo")).into(fotoProfile);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = userPref.getString("token", "");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void logout() {

        StringRequest request = new StringRequest(Request.Method.GET, Constants.LOGOUT, response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(Profile.this, LandingPage.class));
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = userPref.getString("token", "");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }


    private void update_photo() {
        StringRequest request = new StringRequest(Request.Method.POST, Constants.UPDATE_PROFILE, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")) {
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("photo", object.getString("photo"));
                    editor.apply();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
            Log.d(TAG, "update_photo: error");
        }

        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Log.d(TAG, "update_photo: authorizing");
                String token = userPref.getString("token"," ");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization", "Bearer "+token);
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d(TAG, "update_photo: saving");
                HashMap<String,String> map = new HashMap<>();
                map.put("photo",bitmapToString(bitmap));
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(Profile.this);
        queue.add(request);

    }

    private String bitmapToString(Bitmap bitmap) {
        if(bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte [] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array, Base64.DEFAULT);
        }

        return "";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_ADD_PROFILE && resultCode == RESULT_OK){

            assert data != null;
            Uri imgUri = data.getData();
            fotoProfile.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);

                update_photo();
                Log.d(TAG, "update_photo: stored");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


