package com.cookandroid.tuner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Delay before initialize MainActivity.java

        final TimerTask Auth = new TimerTask() {
            @Override
            public void run() {
                Log.i("Waiting", "계속 도는 중");
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
                    Log.i("wating response", ":   initialize");
                }
                else{
                    cancel();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                    startActivity(intent);
                    finish();

                }


            }
        };

        final Timer timer = new Timer();
        timer.schedule(Auth,0,5000);

    }
}
