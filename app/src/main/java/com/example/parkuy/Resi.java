package com.example.parkuy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Resi extends AppCompatActivity implements View.OnClickListener {
    private Button tombolback, tombolBayar;
    private SharedPreferences parkir, resep;
    private TextView namaParkir, alamatParkir, biayaperjam, totalharga, jammasuk, jamkeluar;
    private String biayaperhour, intime, hargatotal, increment, jam;
    private Date in = null;
    private Date out = null ;

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
        tombolBayar.setOnClickListener(this);


        resep = getApplicationContext().getSharedPreferences("receipt_parkir", MODE_PRIVATE);
        parkir = getApplicationContext().getSharedPreferences("parkir", MODE_PRIVATE);
        namaParkir.setText(parkir.getString("namaParkir", ""));
        alamatParkir.setText(parkir.getString("alamatParkir", ""));
        biayaperhour = "Rp. "+parkir.getString("increment","")+" / "+parkir.getString("perHour","")+" Jam";
        biayaperjam.setText(biayaperhour);
        jammasuk.setText(resep.getString("jamMasuk",""));
        intime = resep.getString("jamMasuk", "");
        hargatotal = parkir.getString("biayaParkir","");
        increment = parkir.getString("increment", "");
        jam = parkir.getString("perHour","");

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long time = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                                String timeString = sdf.format(time);
                                jamkeluar.setText(timeString);
                                try {
                                    in = sdf.parse(intime);
                                    out = sdf.parse(timeString);

                                    DateTime in1 = new DateTime();
                                    DateTime out1 = new DateTime();

                                    int diffHours = Hours.hoursBetween(in1, out1).getHours() % 24;
                                    if(diffHours != 0) {
                                        int harga = Integer.parseInt(hargatotal);
                                        int mul = Integer.parseInt(increment);
                                        int Jam = Integer.parseInt(jam);

                                        if (diffHours / Jam > 0) {
                                            harga += mul * (diffHours / Jam);
                                            totalharga.setText(harga);
                                        }
                                    }
                                    else{
                                        totalharga.setText(parkir.getString("biayaParkir",""));
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tombolbayar:
                startActivity(new Intent(Resi.this, HomeScreen.class));
                finish();
                break;

            case R.id.tombolback:
                startActivity(new Intent(Resi.this, PageAktivitas.class));
                finish();
                break;
        }

    }
}
