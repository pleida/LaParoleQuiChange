package com.example.laparolequichange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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


        // Find the recycler view
        rvLivres = findViewById(R.id.rvLivres);

        livres = new ArrayList<>();
        adapter = new LivreAdapter(this,livres);

        adapter.setOnItemClickListener(new LivreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(MainActivity.this,Chapitres.class);
                Livres livre = livres.get(position);

                MainActivity.this.startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);



        //Recycler view setup: layout manager and adapter
        rvLivres.setLayoutManager(layoutManager);
        rvLivres.setAdapter(adapter);
        livreItem();


    }

    public void livreItem(){
        String[] tags = getResources().getStringArray(R.array.tags);
        Log.i("lengh", String.valueOf(tags.length));
        for(String tag : tags) {
            String[] pair = tag.split(":");

            String key = pair[0];
            String value = "R.drawable." + pair[1];
            String number = pair[2];

            Livres livre = new Livres( key,value,Integer.valueOf(number));
            livres.add(livre);
        }
        Log.i("list items", livres.toString());
    }
}