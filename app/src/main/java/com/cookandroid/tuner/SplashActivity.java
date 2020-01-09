package com.cookandroid.tuner;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Thread.sleep(1000);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("state", "launch");
            startActivity(intent);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

}
