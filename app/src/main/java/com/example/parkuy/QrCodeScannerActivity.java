package com.example.parkuy;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

public class QrCodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private SharedPreferences tempat_parkir, userPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission()){
                Toast.makeText(QrCodeScannerActivity.this, "Permission is granted", Toast.LENGTH_LONG).show();
            }
            else{
                requestPermissions();
            }
        }
    }


    private Boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(QrCodeScannerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermissions()  {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String permission[], int grantResults[]){
        switch(requestCode)
        {
            case REQUEST_CAMERA :
                if(grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(QrCodeScannerActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(QrCodeScannerActivity.this, "Permission Not Granted", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                            }
                                        });
                                return ;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                if(scannerView == null)
                {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else
            {
                requestPermissions();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    private void displayAlertMessage(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(QrCodeScannerActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void handleResult(Result result) {
        String scanResult = result.toString().trim();
        Toast.makeText(this, "Barcode Scanned", Toast.LENGTH_SHORT).show();
        createMulaiParkir(scanResult);
    }

    private void createMulaiParkir(String x) {
        StringRequest request = new StringRequest(Request.Method.POST, Constants.SEND_ID_QR_CODE_PARKIR, response -> {
            try {
                JSONObject object = new JSONObject(response);
                JSONObject parkir =  object.getJSONObject("parkir");
                if(object.getBoolean("success")){
                    tempat_parkir = getSharedPreferences("parkir", MODE_PRIVATE);
                    SharedPreferences.Editor editor = tempat_parkir.edit();
                    editor.putString("idParkir", parkir.getString("id"));
                    editor.putString("namaParkir", parkir.getString("namaParkir"));
                    editor.putString("alamatParkir", parkir.getString("alamatParkir"));
                    editor.putString("biayaParkir", parkir.getString("biayaParkir"));
                    editor.putString("increment", parkir.getString("increment"));
                    editor.putString("perHour", parkir.getString("perHour"));
                    editor.apply();
                    Toast.makeText(this,"Parkir Found", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QrCodeScannerActivity.this, MulaiParkir.class));
                    finish();

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Parkir not found", Toast.LENGTH_SHORT).show();
                scannerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onResume();
                    }
                });
            }
        }, error -> {
            error.printStackTrace();
            onResume();
            Toast.makeText(this, "Parkir not found", Toast.LENGTH_SHORT).show();
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("idParkir", x);
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
