package com.example.laparolequichange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TimelineActivity";

    RecyclerView rvLivres;
    List<Livres> livres;
    LivreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        toolbar.setTitle("La Parole Qui Change");

        TextView textView = findViewById(R.id.tvTitle);
        textView.setText("La Parole Qui Change");
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);


        // Find the recycler view
        rvLivres = findViewById(R.id.rvLivres);

        livres = new ArrayList<>();
        adapter = new LivreAdapter(this,livres);

        adapter.setOnItemClickListener(new LivreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(MainActivity.this,Chapitres.class);
                Livres livre = livres.get(position);
                intent.putExtra("livre", Parcels.wrap(livre));

                MainActivity.this.startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);



        //Recycler view setup: layout manager and adapter
        rvLivres.setLayoutManager(layoutManager);
        rvLivres.setAdapter(adapter);
        try {
            livreItem();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void livreItem() throws IOException {

        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open("LivreInfos.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null){

            String[] data = line.split(":");
            String key = data[0].trim();
            String value = data[1].trim();
            String number = data[2].trim();
            String description = data[3].trim();
            String available = data[4].trim();

            int imageId = getResources().getIdentifier(value,"drawable", getPackageName());

            Livres livre = new Livres( key,Integer.valueOf(number),imageId,description,available);
            livres.add(livre);
        }
        reader.close();


    }
}