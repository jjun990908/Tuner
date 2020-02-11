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
    boolean PASS = false;  // 사용자의 화면이 MainActivity 로 넘어갔는지 확인함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Timer().schedule(Auth,3000,5000); // 아래에 있는 Auth(오디오 권한 받기) 를 5초마다 실행함.

    }


    final TimerTask Auth = new TimerTask() {
        @Override
        public void run() {
            Log.i("사용자의 마이크 권한을 받는 중...", "");
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
                Log.i("Wating response", ":         AUDIO");
            }
            else{
                cancel(); // 지금 이 Task 를 멈춘다.
                if(!PASS){
                    PASS = true;
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };

}
