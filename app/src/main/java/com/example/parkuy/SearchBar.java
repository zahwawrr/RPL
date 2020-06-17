package com.example.parkuy;

import android.os.Bundle;

import com.example.parkuy.Model.Parkir;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class SearchBar extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Parkir> parkirs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

    }

}
