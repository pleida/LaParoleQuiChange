package com.example.laparolequichange;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;

import org.parceler.Parcels;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.PowerManager;

public class Details extends AppCompatActivity {

    // creating a variable
    // for PDF view.
    PDFView pdfView;
    ProgressBar progressBar;

    // creating a variable for
    // button and media player
    ImageButton playBtn;
    ImageButton btnNext;
    ImageButton btnPrevious;
    MediaPlayer mediaPlayer;
    // creating a seekbar
    SeekBar seekBar;
    // creating
    TextView txtAudioPositon;
    TextView txtAudioDuration;
    TextView tvMessage;

    // url of our PDF file.
    String pdfurl = "https://laparolequichange.org/messages/";

    String nameBook;
    int chapterNber;
    boolean isLoad = false;
    char audioLetter = 'a';
    //int audioIndex = 0;

    private PowerManager.WakeLock wakeLock;


    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {

            //mediaPlayer.release();
           // mediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Details.this,Chapitres.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent,0);
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);

        // set Progress bar
        progressBar = findViewById(R.id.pb);

        Livres livres = (Livres) Parcels.unwrap(getIntent().getParcelableExtra("livre"));
        chapterNber = getIntent().getExtras().getInt("chapitre");

        nameBook = livres.getBook_name().replaceAll("\\s","").replaceAll("è","e").replaceAll("é","e")
                .replaceAll("ï","i").replaceAll("ë","e");


        pdfurl = pdfurl + nameBook.toLowerCase() + String.valueOf(chapterNber) + ".pdf";


        // initializing our pdf view.
        pdfView = findViewById(R.id.pdfView);
        new RetrievePDFfromUrl().execute(pdfurl);

        // set a text in the toolbar
        TextView textView = findViewById(R.id.tvTitle);
        textView.setText(livres.getBook_name() + " " + String.valueOf(chapterNber)  );

        // The message if there is no pdf
        tvMessage = findViewById(R.id.tvMessage);

        // initializing the buttons
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        // button to play the next audio
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               nextAudio(view);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioLetter == 'c'){
                    audioLetter = 'b';
                }else if(audioLetter == 'b'){
                    audioLetter = 'a';
                }
                String otherAudioUrl = "https://laparolequichange.org/messages/" + nameBook.toLowerCase() + chapterNber + audioLetter + "_15min.mp3";
                Log.i("links",otherAudioUrl);

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;

                    loadAudio(otherAudioUrl);
                    playAudio(view);


                }
            }
        });

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // set a seekBar
        seekBar = findViewById(R.id.seekTo);
        // set Audio position and duration
        txtAudioPositon = findViewById(R.id.txtSong_position);
        txtAudioDuration = findViewById(R.id.txtDuration);


        // initializing our buttons
        playBtn = findViewById(R.id.btnPlay);


        // setting on click listener for our play and pause buttons audio.
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to play audio.
                playAudio(v);
            }
        });
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
//                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            if (inputStream != null){
                pdfView.fromStream(inputStream).load();
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }else{
                tvMessage.setVisibility(TextView.VISIBLE);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }


        }
    }
    // creating a method play audio
    private void getAudio() {

        String audioUrl = "https://laparolequichange.org/messages/" + nameBook.toLowerCase() + chapterNber + "_15min.mp3";

        Log.i("link",audioUrl);

        // initializing media player
        mediaPlayer = new MediaPlayer();

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.
        loadAudio(audioUrl);
        if(!isLoad){

            String otherAudioUrl = "https://laparolequichange.org/messages/" + nameBook.toLowerCase() + chapterNber + audioLetter + "_15min.mp3";
            Log.i("links",otherAudioUrl);
            loadAudio(otherAudioUrl);
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();
    }


    // create the audio position method
    private void AudioPosition(){
        long millisecondsCurrent = mediaPlayer.getCurrentPosition();
        long minutesCurrent = millisecondsCurrent / 60000;
        long secondsCurrent = (millisecondsCurrent % 60000) / 1000;
        String timeStringCurrent = String.format("%02d:%02d", minutesCurrent, secondsCurrent);
        txtAudioPositon.setText(timeStringCurrent);
    }

    // create the audio duration method
    private void AudioDuration(){
        long milliseconds = mediaPlayer.getDuration();
        long minutes = milliseconds / 60000;
        long seconds = (milliseconds % 60000) / 1000;
        String timeString = String.format("%02d:%02d", minutes, seconds);

        txtAudioDuration.setText(timeString);
    }

    // create the play audio method
    private void playAudio(View v){
        if ( mediaPlayer!= null && mediaPlayer.isPlaying()) {
            Drawable drawable = ContextCompat.getDrawable(v.getContext(), R.drawable.play_circle);
            playBtn.setImageDrawable(drawable);
            // pausing the media player if media player
            // is playing we are calling below line to
            // stop our media player.
            mediaPlayer.pause();

        }else{
            Drawable drawable = ContextCompat.getDrawable(v.getContext(), R.drawable.pause_circle);
            playBtn.setImageDrawable(drawable);
            if(!isLoad){
                getAudio();
            }


            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration());

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress); // seek to position
                        updateSeekBar();
                        if(mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()){
                            nextAudio(v);
                        }
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.pause(); // pause playback while user adjusts SeekBar
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.start(); // resume playback once user releases SeekBar thumb
                    updateSeekBar();

                }
            });

            mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    seekBar.setProgress(mp.getCurrentPosition());
                }
            });

            updateSeekBar();
        }
    }

    public void loadAudio(String url){
        try {
            if(mediaPlayer == null){
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            }
            mediaPlayer.setDataSource(url);

            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            isLoad = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // function to play the next audio
    public void nextAudio(View v){
        if(audioLetter == 'a'){
            audioLetter = 'b';
        }else if(audioLetter == 'b'){
            audioLetter = 'c';
        }
        String otherAudioUrl = "https://laparolequichange.org/messages/" + nameBook.toLowerCase() + chapterNber + audioLetter + "_15min.mp3";
        Log.i("links",otherAudioUrl);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;

            loadAudio(otherAudioUrl);
            playAudio(v);


        }
    }

    public void updateSeekBar(){
        if ( mediaPlayer != null && mediaPlayer.isPlaying()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        if (mediaPlayer.isPlaying()) {
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            AudioPosition();
                            new Handler().postDelayed(this, 100); // update every 100ms
                        }
                    }

                }
            }, 100);
        }
        // for the duration
        AudioDuration();
    }
}
