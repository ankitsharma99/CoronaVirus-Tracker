package com.example.coronavirustracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private ImageView iv_mainLogo;
    private TextView tv_mainLogoText;
    private final int TIMEOUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getSupportActionBar().hide();

        tv_mainLogoText = findViewById(R.id.tv_mainLogoTextView);
        iv_mainLogo = findViewById(R.id.iv_mainLogoSplash);

        iv_mainLogo.animate().translationXBy(-500).alpha(0).setDuration(TIMEOUT);
        tv_mainLogoText.animate().translationXBy(500).alpha(0).setDuration(TIMEOUT);


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(TIMEOUT);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();


    }
}