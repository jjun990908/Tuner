package com.cookandroid.tuner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        while (true) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                //마이크 권한 확인
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
                //없다면 마이크 권한 요청
            } else {
                try {
                    Thread.sleep(2300);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            }
        }
    }
    }
