package com.example.laparolequichange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

public class Chapitres extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapitres);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        gridView = findViewById(R.id.gvNbresChapitres);
        ArrayList<Integer> nbreChapitreArrayList = new ArrayList<Integer>();
        Livres livres = (Livres) Parcels.unwrap(getIntent().getParcelableExtra("livre"));

        TextView textView = findViewById(R.id.tvTitle);
        textView.setText(livres.getBook_name());
//        toolbar.setTitle(livres.getBook_name());

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
        for(int i = 1; i <= livres.getChapter_number(); i++){
            nbreChapitreArrayList.add(i);
        }

        GVAdapter adapter = new GVAdapter(this, nbreChapitreArrayList);
        gridView.setAdapter(adapter);
    }
}