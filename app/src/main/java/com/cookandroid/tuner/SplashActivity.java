package com.cookandroid.tuner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst",false);
        if(!checkFirst){
            Toast.makeText(SplashActivity.this, "튜닝 기능을 위해 마이크 권한을 허가해주세요.", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst",true);
            editor.commit();
            try{
            Thread.sleep(2300);}
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }


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
