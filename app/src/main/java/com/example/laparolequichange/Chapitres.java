package com.example.laparolequichange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

public class Chapitres extends AppCompatActivity {
    GridView gridView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Chapitres.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent,0);
        return true;
    }

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

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        for(int i = 1; i <= livres.getChapter_number(); i++){
            nbreChapitreArrayList.add(i);
        }

        GVAdapter adapter = new GVAdapter(this, nbreChapitreArrayList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Chapitres.this, Details.class);
                intent.putExtra("chapitre", nbreChapitreArrayList.get(i));
                intent.putExtra("livre", Parcels.wrap(livres));
                startActivity(intent);
            }
        });
    }

}