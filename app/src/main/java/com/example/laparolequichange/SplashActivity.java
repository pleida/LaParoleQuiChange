package com.example.laparolequichange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000; // 5 seconds
    private ImageView imageView;

    SharedPreferences sharedPreferences;
    boolean nightMode;

    Animation top_anim, bottom_anim;

    @Override
    protected void onStart() {
        super.onStart();
        // we used SharedPreferences to save mode if exit the app and go back again
        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);

        if(nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = findViewById(R.id.ivSplashScreen);
        imageView.setImageResource(R.drawable.logo);

        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        imageView.setAnimation(bottom_anim);


        new Handler().postDelayed(new Runnable(){
              @Override
              public void run() {
                    // the method will executed once the timer
                    // Start your app's main activity
                  Intent i = new Intent(SplashActivity.this,MainActivity.class);
                  startActivity(i);

                  // close this activity
                  finish();
              }

        },SPLASH_TIME_OUT );

    }

}