package com.ashrafulkabirashik.ashrafulkabirashik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashscreenIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashscreenIntent);
                finish();
            }
        }, 2000);
    }
}