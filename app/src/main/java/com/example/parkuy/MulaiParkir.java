package com.example.parkuy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MulaiParkir extends AppCompatActivity {
    private Button tombolback;
    private Button tombolmulai;
    private TextView namaparkir, alamatparkir, biayaperjam, totalharga;
    private String biayaperhour;
    SharedPreferences parkir, resep, userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulaiparkir);

        namaparkir = findViewById(R.id.namaparkir);
        alamatparkir = findViewById(R.id.alamatparkir);
        biayaperjam = findViewById(R.id.biayaperjam);
        totalharga = findViewById(R.id.totalharga);

        userPref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        parkir = getApplicationContext().getSharedPreferences("parkir", MODE_PRIVATE);
        namaparkir.setText(parkir.getString("namaParkir", ""));
        alamatparkir.setText(parkir.getString("alamatParkir", ""));
        biayaperhour = "Rp. "+parkir.getString("increment","")+" / "+parkir.getString("perHour","")+" Jam";
        biayaperjam.setText(biayaperhour);
        totalharga.setText(parkir.getString("biayaParkir",""));


        tombolback = (Button) findViewById(R.id.tombolback);
        tombolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openhomescreen();
            }
        });

        tombolmulai = (Button) findViewById(R.id.tombolmulai);
        tombolmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startParkir();

            }
        });
    }

    private void startParkir() {
        StringRequest request = new StringRequest(Request.Method.POST, Constants.MULAI_PARKIR, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){
                    JSONObject receipt_parkir = object.getJSONObject("receipt");
                    resep = getSharedPreferences("receipt_parkir", MODE_PRIVATE);
                    SharedPreferences.Editor editor = resep.edit();
                    editor.putString("idReceipt", receipt_parkir.getString("id"));
                    editor.putString("jamMasuk", receipt_parkir.getString("jamMasuk"));
                    editor.apply();
                    startActivity(new Intent(MulaiParkir.this, PageAktivitas.class));
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
                HashMap<String, String> map = new HashMap<>();
                map.put("idParkir", parkir.getString("idParkir",""));
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                String token = userPref.getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + token);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void openhomescreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

}