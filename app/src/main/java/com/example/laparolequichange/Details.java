package com.example.laparolequichange;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

import org.parceler.Parcels;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Details extends AppCompatActivity {

    // creating a variable
    // for PDF view.
    PDFView pdfView;
    ProgressBar progressBar;

    // url of our PDF file.
    String pdfurl = "https://laparolequichange.org/messages/";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Details.this,Chapitres.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent,0);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // set Progress bar
        progressBar = findViewById(R.id.pb);



        Livres livres = (Livres) Parcels.unwrap(getIntent().getParcelableExtra("livre"));
        int chapterNber = getIntent().getExtras().getInt("chapitre");

        String nameBook = livres.getBook_name().replaceAll("\\s","").replaceAll("è","e").replaceAll("é","e")
                .replaceAll("ï","i").replaceAll("ë","e");


        pdfurl = pdfurl + nameBook.toLowerCase() + String.valueOf(chapterNber) + ".pdf";


        // initializing our pdf view.
        pdfView = findViewById(R.id.pdfView);
        new RetrievePDFfromUrl().execute(pdfurl);

        // set a text in the toolbar
        TextView textView = findViewById(R.id.tvTitle);
        textView.setText(livres.getBook_name() + " " + String.valueOf(chapterNber)  );

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // create an async task class for loading pdf file from URL.
    class RetrievePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressBar.bringToFront();

            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
            progressBar.setVisibility(ProgressBar.INVISIBLE);

        }
    }
}
