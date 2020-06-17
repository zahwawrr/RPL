package com.example.parkuy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class modal_bottom_akhiriparkir extends BottomSheetDialogFragment {
    private TextView hargaparkir, namaparkiran, alamatparkir;
    SharedPreferences parkir, resep;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_modal_bottom_akhiriparkir, container, false);
        hargaparkir = v.findViewById(R.id.hargaparkir);
        namaparkiran = v.findViewById(R.id.namaparkiran);
        alamatparkir = v.findViewById(R.id.alamatparkir2);
        parkir = getContext().getSharedPreferences("parkir", Context.MODE_PRIVATE);
        resep = getContext().getSharedPreferences("receipt_parkir", Context.MODE_PRIVATE);
        Button btnbatal = (Button)v.findViewById(R.id.tombolbackbatal);

        hargaparkir.setText(resep.getString("harga",""));
        alamatparkir.setText(parkir.getString("alamatParkir", ""));
        namaparkiran.setText(parkir.getString("namaParkir",""));

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PageAktivitas.class));

            }
        });

        Button btnakhiri = (Button)v.findViewById(R.id.tombolakhiriparkir2);
        btnakhiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Resi.class));
            }
        });
        return v;
    }
}

