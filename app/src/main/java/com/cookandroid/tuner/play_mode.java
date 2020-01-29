package com.cookandroid.tuner;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class play_mode extends AppCompatActivity {

    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,btn_help,btn_tune;
    Button btn_switch;
    Boolean sharpmode=false,flatmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.play_mode);

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
        btn_switch = (Button)findViewById(R.id.switch_mode);

        final ScaleSrc Scale = new ScaleSrc(play_mode.this);
        final SoundPool sp = Scale.sp;

        btn_c_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.do0_0,1,1,1,0,1.0f);
                }
                btn_c_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_c_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_d_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.re0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.re0_0,1,1,1,0,1.0f);
                }
                btn_d_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_d_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_e_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.re0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                }
                btn_e_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_e_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_f_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                }
                btn_f_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_f_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_g_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.sol0_0,1,1,1,0,1.0f);
                }
                btn_g_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_g_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_a_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.la0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.la0_0,1,1,1,0,1.0f);
                }
                btn_a_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_a_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_b_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.do1_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.la0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.si0_0,1,1,1,0,1.0f);
                }
                btn_b_l.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_b_l.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_c_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.do1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.si0_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.do1_0,1,1,1,0,1.0f);
                }
                btn_c_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_c_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_d_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.re1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.re1_0,1,1,1,0,1.0f);
                }
                btn_d_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_d_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_e_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.re1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                }
                btn_e_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_e_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_f_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                }
                btn_f_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_f_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_g_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.sol1_0,1,1,1,0,1.0f);
                }
                btn_g_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_g_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_a_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.la1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.la1_0,1,1,1,0,1.0f);
                }
                btn_a_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_a_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_b_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.do2_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.la1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.si1_0,1,1,1,0,1.0f);
                }
                btn_b_h.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_b_h.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_c_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.do2_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.si1_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.do2_0,1,1,1,0,1.0f);
                }
                btn_c_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_c_hh.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_d_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.re2_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do2_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.re2_0,1,1,1,0,1.0f);
                }
                btn_d_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_d_hh.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
            }
        });
        btn_e_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharpmode){
                    sp.play(Scale.mi2_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.re2_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.mi2_0,1,1,1,0,1.0f);
                }
                btn_e_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //여기에 딜레이 후 시작할 작업들을 입력
                        btn_e_hh.setBackgroundResource(R.drawable.btn_key_shape);
                    }
                }, 500);
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

        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(play_mode.this, "튜닝모드", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.anim.anim_slide_up,R.anim.anim_slide_up);
            }
        });

    }


    // 잘못 뒤로가기 눌렀을 때
    long first_time;
    long second_time;

    @Override
    public void onBackPressed() {

        Toast ExitToast = Toast.makeText(play_mode.this, "종료하시려면 뒤로가기 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT);
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
