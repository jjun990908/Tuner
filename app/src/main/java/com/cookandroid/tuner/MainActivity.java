package com.cookandroid.tuner;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,check,btn_help,btn_tune;

    //버튼색 초기화함수
    public void setBtnWhite(){
        btn_c_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_d_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_e_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_f_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_g_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_a_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_b_l.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_c_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_d_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_e_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_f_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_g_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_a_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_b_h.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_c_hh.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_d_hh.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        btn_e_hh.setBackgroundColor(Color.parseColor("#00FFFFFF"));

    }
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
        btn_g_h = (Button)findViewById(R.id.btn_g_h);
        btn_a_h = (Button)findViewById(R.id.btn_a_h);
        btn_b_h = (Button)findViewById(R.id.btn_b_h);
        btn_c_hh = (Button)findViewById(R.id.btn_c_hh);
        btn_d_hh = (Button)findViewById(R.id.btn_d_hh);
        btn_e_hh = (Button)findViewById(R.id.btn_e_hh);
        check = (Button)findViewById(R.id.correct);
        btn_help = (Button)findViewById(R.id.btn_help);
        btn_tune = (Button)findViewById(R.id.btn_tune);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
        //마이크 권한 확인
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
            //없다면 마이크 권한 요청
        }

        btn_c_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("C");
                btn_c_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_d_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("D");
                btn_d_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_e_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("E");
                btn_e_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_f_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("F");
                btn_f_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_g_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("G");
                btn_g_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_a_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("A");
                btn_a_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_b_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("B");
                btn_b_l.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_c_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("C");
                btn_c_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_d_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("D");
                btn_d_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_e_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("E");
                btn_e_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_f_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("F");
                btn_f_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_g_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("G");
                btn_g_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_a_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("A");
                btn_a_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_b_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("B");
                btn_b_h.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_c_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("C");
                btn_c_hh.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_d_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("D");
                btn_d_hh.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });
        btn_e_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnWhite();
                check.setText("E");
                btn_e_hh.setBackgroundColor(Color.parseColor("#2800C4FF"));
            }
        });



        btn_tune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //팝업객체 생성
                PopupMenu popup = new PopupMenu(getApplicationContext(),view);
                popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());
                //팝업메뉴 클릭시 이벤트
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.sharp:
                                //#을 클릭했을때 이벤트 실행코드
                                Toast.makeText(getApplicationContext(),"샵",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.origin:
                                //0을 클릭했을때 이벤트 실행코드
                                Toast.makeText(getApplicationContext(),"원음",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.flat:
                                //b을 클릭했을때 이벤트 실행코드
                                Toast.makeText(getApplicationContext(),"플랫",Toast.LENGTH_SHORT).show();
                                break;

                        }
                        return true;
                    }
                });
                popup.show();

            }
        });


    }
}
