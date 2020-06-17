package com.example.parkuy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Resi extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Resi";
    private Button tombolback, tombolBayar;
    private SharedPreferences parkir, resep, userPref;
    private TextView namaParkir, alamatParkir, biayaperjam, totalharga, jammasuk, jamkeluar;
    private String biayaperhour, intime, hargatotal, increment, jam;
    private Date in = null;
    private Date out = null ;
    private String timeString;
    private int harga, mul, Jam, diffHours;

    class t extends Thread {
        private Boolean threadStop = false;

        public void threadInterrupt(boolean x) {
            this.threadStop = x;
        }

        @Override
        public void run() {
            try {
                while (!t.interrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            long time = System.currentTimeMillis();
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                            timeString = sdf.format(time);
                            jamkeluar.setText(timeString);
                            try {
                                in = sdf.parse(intime);
                                out = sdf.parse(timeString);

                                DateTime in1 = new DateTime();
                                DateTime out1 = new DateTime();

                                diffHours = Hours.hoursBetween(in1, out1).getHours() % 24;
                                if (diffHours != 0) {
                                    harga = Integer.parseInt(hargatotal);
                                    mul = Integer.parseInt(increment);
                                    Jam = Integer.parseInt(jam);

                                    if (diffHours / Jam > 0) {
                                        harga += mul * (diffHours / Jam);
                                        hargatotal = Integer.toString(harga);
                                        totalharga.setText(harga);

                                    }
                                } else {
                                    totalharga.setText(parkir.getString("biayaParkir", ""));
                                    hargatotal = parkir.getString("biayaparkir", "");
                                }


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    SharedPreferences.Editor editor = resep.edit();
                    editor.putString("harga", hargatotal);
                    editor.apply();
                    if(threadStop)
                    {
                        Log.d(TAG, "run: threadstopped");
                        interrupt();
                        akhiriparkir();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();

            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resiparkir);

        tombolback = (Button) findViewById(R.id.tombolback);
        tombolBayar = findViewById(R.id.tombolbayar);
        namaParkir = findViewById(R.id.namaparkir);
        alamatParkir = findViewById(R.id.alamatparkir);
        biayaperjam = findViewById(R.id.biayaperjam);
        totalharga = findViewById(R.id.totalharga);
        jammasuk = findViewById(R.id.jammasuk);
        jamkeluar = findViewById(R.id.jamkeluar);


        tombolback.setOnClickListener(this);

        userPref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        resep = getApplicationContext().getSharedPreferences("receipt_parkir", MODE_PRIVATE);
        parkir = getApplicationContext().getSharedPreferences("parkir", MODE_PRIVATE);
        namaParkir.setText(parkir.getString("namaParkir", ""));
        alamatParkir.setText(parkir.getString("alamatParkir", ""));
        biayaperhour = "Rp. " + parkir.getString("increment", "") + " / " + parkir.getString("perHour", "") + " Jam";
        biayaperjam.setText(biayaperhour);
        jammasuk.setText(resep.getString("jamMasuk", ""));
        intime = resep.getString("jamMasuk", "");
        hargatotal = parkir.getString("biayaParkir", "");
        increment = parkir.getString("increment", "");
        jam = parkir.getString("perHour", "");
        t thread = new t();
        thread.start();
        tombolBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.threadInterrupt(true);

            }
        });



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tombolback:
                Intent pageAktivitas = new Intent(this, PageAktivitas.class);
                startActivity(pageAktivitas);
                finish();
                break;
        }

    }


    private void akhiriparkir() {
        Log.d(TAG, "akhiriparkir: runmethod AkhiriParkir");
        StringRequest request = new StringRequest(Request.Method.POST, Constants.AKHIRI_PARKIR, response -> {

            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                SharedPreferences.Editor editor = resep.edit();
                editor.clear();
                Log.d(TAG, "akhiriparkir: session ended");
                startActivity(new Intent(Resi.this, HomeScreen.class));
                Toast.makeText(this, "Session ended", Toast.LENGTH_SHORT).show();
                finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {
            error.printStackTrace();
            Log.d(TAG, "akhiriparkir: Problem occured");
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("user_id", userPref.getString("id",""));
                map.put("id", resep.getString("idReceipt", ""));
                map.put("jamKeluar", timeString);
                map.put("totalBiaya", hargatotal);
                map.put("selisihWaktu", Integer.toString(diffHours));
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


}
