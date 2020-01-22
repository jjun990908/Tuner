package com.cookandroid.tuner;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,btn_help,btn_tune;
    View centerView,centerRView,centerLView,centerRRView,centerLLView;
    TextView keyText,sharpText,flatText;
    Boolean sharpmode=false,flatmode=false;

    //건반색 초기화함수
    public void setBtnWhite(){
        btn_c_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_d_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_e_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_f_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_g_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_a_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_b_l.setBackgroundResource(R.drawable.btn_key_shape);
        btn_c_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_d_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_e_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_f_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_g_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_a_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_b_h.setBackgroundResource(R.drawable.btn_key_shape);
        btn_c_hh.setBackgroundResource(R.drawable.btn_key_shape);
        btn_d_hh.setBackgroundResource(R.drawable.btn_key_shape);
        btn_e_hh.setBackgroundResource(R.drawable.btn_key_shape);

    }
    //건반라벨 #추가함수
    public void setSharpText(){
        btn_c_l.setText("C#");
        btn_d_l.setText("D#");
        btn_e_l.setText("E#");
        btn_f_l.setText("F#");
        btn_g_l.setText("G#");
        btn_a_l.setText("A#");
        btn_b_l.setText("B#");
        btn_c_h.setText("C#");
        btn_d_h.setText("D#");
        btn_e_h.setText("E#");
        btn_f_h.setText("F#");
        btn_g_h.setText("G#");
        btn_a_h.setText("A#");
        btn_b_h.setText("B#");
        btn_c_hh.setText("C#");
        btn_d_hh.setText("d#");
        btn_e_hh.setText("e #");
    }
    //건반라벨 b추가함수
    public void setFlatText(){
        btn_c_l.setText("C♭");
        btn_d_l.setText("D♭");
        btn_e_l.setText("E♭");
        btn_f_l.setText("F♭");
        btn_g_l.setText("G♭");
        btn_a_l.setText("A♭");
        btn_b_l.setText("B♭");
        btn_c_h.setText("C♭");
        btn_d_h.setText("D♭");
        btn_e_h.setText("E♭");
        btn_f_h.setText("F♭");
        btn_g_h.setText("G♭");
        btn_a_h.setText("A♭");
        btn_b_h.setText("B♭");
        btn_c_hh.setText("C♭");
        btn_d_hh.setText("D♭");
        btn_e_hh.setText("E♭");
    }
    //건반라벨 원음변환함수
    public void setOriginText(){
        btn_c_l.setText("C");
        btn_d_l.setText("D");
        btn_e_l.setText("E");
        btn_f_l.setText("F");
        btn_g_l.setText("G");
        btn_a_l.setText("A");
        btn_b_l.setText("B");
        btn_c_h.setText("C");
        btn_d_h.setText("D");
        btn_e_h.setText("E");
        btn_f_h.setText("F");
        btn_g_h.setText("G");
        btn_a_h.setText("A");
        btn_b_h.setText("B");
        btn_c_hh.setText("C");
        btn_d_hh.setText("D");
        btn_e_hh.setText("E");
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
        btn_help = (Button)findViewById(R.id.btn_help);
        btn_tune = (Button)findViewById(R.id.btn_tune);
        centerView = (View)findViewById(R.id.view_CenterView);
        centerRView = (View)findViewById(R.id.view_CenterRView);
        centerRRView = (View)findViewById(R.id.view_CenterRRView);
        centerLView = (View)findViewById(R.id.view_CenterLView);
        centerLLView = (View)findViewById(R.id.view_CenterLLView);
        keyText = (TextView)findViewById(R.id.key);
        sharpText = (TextView)findViewById(R.id.sharp);
        flatText = (TextView)findViewById(R.id.flat);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
        //마이크 권한 확인
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
            //없다면 마이크 권한 요청
        }

        final ScaleSrc Scale = new ScaleSrc(MainActivity.this);
        final SoundPool sp = Scale.sp;

        btn_c_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.do0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("C");
                btn_c_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_d_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.re0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("D");
                btn_d_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_e_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.mi0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("E");
                btn_e_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_f_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.fa0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("F");
                btn_f_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_g_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.sol0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("G");
                btn_g_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_a_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.la0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("A");
                btn_a_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_b_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.si0_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("B");
                btn_b_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_c_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.do1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("C");
                btn_c_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_d_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.re1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("D");
                btn_d_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_e_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.mi1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("E");
                btn_e_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_f_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.fa1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("F");
                btn_f_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_g_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.sol1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("G");
                btn_g_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_a_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.la1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("A");
                btn_a_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_b_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.si1_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("B");
                btn_b_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_c_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.do2_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("C");
                btn_c_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_d_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.re2_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("D");
                btn_d_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_e_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(Scale.mi2_0,1,1,1,0,1.0f);

                setBtnWhite();
                keyText.setText("E");
                btn_e_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });

        //도움말 버튼 클릭 함수
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //팝업창 액티비티로 연결
                startActivity(new Intent(getApplicationContext(),help_popup.class));
            }
        });

        btn_tune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //팝업메뉴 객체 생성
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
                                sharpText.setVisibility(view.VISIBLE);
                                flatText.setVisibility(view.GONE);
                                sharpmode = true;
                                flatmode = false;
                                setSharpText();
                                break;
                            case R.id.origin:
                                //원음을 클릭했을때 이벤트 실행코드
                                Toast.makeText(getApplicationContext(),"원음",Toast.LENGTH_SHORT).show();
                                sharpText.setVisibility(view.GONE);
                                flatText.setVisibility(view.GONE);
                                sharpmode = false;
                                flatmode = false;
                                setOriginText();
                                break;
                            case R.id.flat:
                                //b을 클릭했을때 이벤트 실행코드
                                Toast.makeText(getApplicationContext(),"플랫",Toast.LENGTH_SHORT).show();
                                sharpText.setVisibility(view.GONE);
                                flatText.setVisibility(view.VISIBLE);
                                sharpmode = false;
                                flatmode = true;
                                setFlatText();
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    // 잘못 뒤로가기 눌렀을 때
    long first_time;
    long second_time;

    @Override
    public void onBackPressed() {

        Toast ExitToast = Toast.makeText(MainActivity.this, "종료하시려면 뒤로가기 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT);
        second_time = System.currentTimeMillis();
        if(second_time - first_time < 3000){
            super.onBackPressed();
            finishAffinity();
            ExitToast.cancel();
        }
        else{ExitToast.show();
        }
        first_time = System.currentTimeMillis();
    }
}
