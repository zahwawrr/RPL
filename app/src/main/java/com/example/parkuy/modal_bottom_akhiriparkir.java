package com.example.parkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class modal_bottom_akhiriparkir extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_modal_bottom_akhiriparkir, container, false);

        Button btnbatal = (Button)v.findViewById(R.id.tombolbackbatal);
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

