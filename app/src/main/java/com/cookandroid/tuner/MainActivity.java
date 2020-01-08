package com.cookandroid.tuner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //버튼 연결
        btn_c_l = (Button)findViewById(R.id.btn_c_l);
        btn_d_l = (Button)findViewById(R.id.btn_d_l);
        btn_e_l = (Button)findViewById(R.id.btn_e_l);
        btn_f_l = (Button)findViewById(R.id.btn_f_l);
        btn_g_l = (Button)findViewById(R.id.btn_g_l);
        btn_a_l = (Button)findViewById(R.id.btn_a_l);
        btn_b_l = (Button)findViewById(R.id.btn_b_l);
        btn_c_h = (Button)findViewById(R.id.btn_c_h);
        btn_d_h = (Button)findViewById(R.id.btn_d_h);
        btn_e_h = (Button)findViewById(R.id.btn_e_h);
        btn_f_h = (Button)findViewById(R.id.btn_f_h);
        btn_a_h = (Button)findViewById(R.id.btn_a_h);
        btn_b_h = (Button)findViewById(R.id.btn_b_h);
        btn_c_hh = (Button)findViewById(R.id.btn_c_hh);
        btn_d_hh = (Button)findViewById(R.id.btn_d_hh);
        btn_e_hh = (Button)findViewById(R.id.btn_e_hh);
        correct = (Button)findViewById(R.id.correct);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
        //마이크 권한 확인
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
            //없다면 마이크 권한 요청
        }

        btn_c_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correct.getVisibility()==view.GONE){
                    correct.setVisibility(view.VISIBLE);
                }else{
                    correct.setVisibility(view.GONE);
                }
            }
        });
    }
}
