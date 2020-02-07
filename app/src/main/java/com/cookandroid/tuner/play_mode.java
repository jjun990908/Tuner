package com.cookandroid.tuner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class play_mode extends AppCompatActivity {

    ImageButton btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,btn_tune;
    ImageButton btn_switch;
    Switch btn_vive;
    boolean isViewPressed;
    int jangjo=0;
    @Override
    public void finish(){
        overridePendingTransition(R.anim.anim_slide_not_move,R.anim.anim_slide_up);
        super.finish();
    }
//    public boolean onTouch(View v, MotionEvent event){
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                isViewPressed = true;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (!v.isPressed()) {
//                    isViewPressed = false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                if (isViewPressed) {
//
//                }
//                break;
//            default:
//                break;
//        }
//        return false;
//    } }

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.play_mode);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        //버튼 연결
        btn_c_l = (ImageButton)findViewById(R.id.btn_c_l);
        btn_d_l = (ImageButton)findViewById(R.id.btn_d_l);
        btn_e_l = (ImageButton)findViewById(R.id.btn_e_l);
        btn_f_l = (ImageButton)findViewById(R.id.btn_f_l);
        btn_g_l = (ImageButton)findViewById(R.id.btn_g_l);
        btn_a_l = (ImageButton)findViewById(R.id.btn_a_l);
        btn_b_l = (ImageButton)findViewById(R.id.btn_b_l);
        btn_c_h = (ImageButton)findViewById(R.id.btn_c_h);
        btn_d_h = (ImageButton)findViewById(R.id.btn_d_h);
        btn_e_h = (ImageButton)findViewById(R.id.btn_e_h);
        btn_f_h = (ImageButton)findViewById(R.id.btn_f_h);
        btn_g_h = (ImageButton)findViewById(R.id.btn_g_h);
        btn_a_h = (ImageButton)findViewById(R.id.btn_a_h);
        btn_b_h = (ImageButton)findViewById(R.id.btn_b_h);
        btn_c_hh = (ImageButton)findViewById(R.id.btn_c_hh);
        btn_d_hh = (ImageButton)findViewById(R.id.btn_d_hh);
        btn_e_hh = (ImageButton)findViewById(R.id.btn_e_hh);
        btn_switch = (ImageButton)findViewById(R.id.switch_mode);
        btn_vive = (Switch)findViewById(R.id.btn_vive);

        final Animation anim_Twist = AnimationUtils.loadAnimation(
                this,R.anim.anim_twist);
        final ScaleSrc Scale = new ScaleSrc(play_mode.this);
        final SoundPool sp = Scale.sp;

        btn_c_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_c_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=2){
                            sp.play(Scale.do0_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.do0_0,1,1,1,0,1.0f);
                        }

                        btn_c_l.setImageResource(R.drawable.key2_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_c_l.setImageResource(R.drawable.key2);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_d_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_d_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=4){
                            sp.play(Scale.re0_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.re0_0,1,1,1,0,1.0f);
                        }
                        btn_d_l.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_d_l.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_e_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_e_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=6){
                            sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                        }
                        btn_e_l.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_e_l.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_f_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_f_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=1){
                            sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                        }

                        btn_f_l.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_f_l.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_g_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_g_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=3){
                            sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.sol0_0,1,1,1,0,1.0f);
                        }
                        btn_g_l.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_g_l.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_a_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_a_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=5){
                            sp.play(Scale.la0_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.la0_0,1,1,1,0,1.0f);
                        }
                        btn_a_l.setImageResource(R.drawable.key2_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_a_l.setImageResource(R.drawable.key2);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_b_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_b_l.startAnimation(anim_Twist);
                        }
                        if (jangjo>=7){
                            sp.play(Scale.do1_0,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.si0_0,1,1,1,0,1.0f);
                        }
                        btn_b_l.setImageResource(R.drawable.key2_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_b_l.setImageResource(R.drawable.key2);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_c_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_c_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=2){
                            sp.play(Scale.do1_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.do1_0,1,1,1,0,1.0f);
                        }
                        btn_c_h.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_c_h.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_d_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_d_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=4){
                            sp.play(Scale.re1_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.re1_0,1,1,1,0,1.0f);
                        }
                        btn_d_h.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_d_h.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_e_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_e_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=6){
                            sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                        }
                        btn_e_h.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_e_h.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_f_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_f_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=1){
                            sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                        }
                        btn_f_h.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_f_h.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });

        btn_g_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_g_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=3){
                            sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.sol1_0,1,1,1,0,1.0f);
                        }
                        btn_g_h.setImageResource(R.drawable.key2_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_g_h.setImageResource(R.drawable.key2);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });
        btn_a_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_a_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=5){
                            sp.play(Scale.la1_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.la1_0,1,1,1,0,1.0f);
                        }
                        btn_a_h.setImageResource(R.drawable.key2_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_a_h.setImageResource(R.drawable.key2);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });

        btn_b_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_b_h.startAnimation(anim_Twist);
                        }
                        if (jangjo>=7){
                            sp.play(Scale.do2_0,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.si1_0,1,1,1,0,1.0f);
                        }
                        btn_b_h.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_b_h.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });

        btn_c_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_c_hh.startAnimation(anim_Twist);
                        }
                        if (jangjo>=2){
                            sp.play(Scale.do2_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.do2_0,1,1,1,0,1.0f);
                        }
                        btn_c_hh.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_c_hh.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });

        btn_d_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_d_hh.startAnimation(anim_Twist);
                        }
                        if (jangjo>=4){
                            sp.play(Scale.re2_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.re2_0,1,1,1,0,1.0f);
                        }
                        btn_d_hh.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_d_hh.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });

        btn_e_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(!btn_vive.isChecked()){
                            vibrator.vibrate(30);
                            btn_e_hh.startAnimation(anim_Twist);
                        }
                        if (jangjo>=6){
                            sp.play(Scale.mi2_5,1,1,1,0,1.0f);
                        }
                        else{
                            sp.play(Scale.mi2_0,1,1,1,0,1.0f);
                        }
                        btn_e_hh.setImageResource(R.drawable.key1_click);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                btn_e_hh.setImageResource(R.drawable.key1);
                            }
                        }, 500);
                        break;
                }
                return false;
            }
        });

        btn_vive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked){
                    Toast.makeText(getApplicationContext(),"진동 OFF",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"진동 ON",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //튜닝모드 전환
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(play_mode.this, "튜닝모드", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.anim.anim_slide_down,R.anim.anim_slide_up);

            }
        });

    }


    // 잘못 뒤로가기 눌렀을 때
    long first_time;
    long second_time;

    @Override
    public void onBackPressed() {

        Toast.makeText(play_mode.this, "튜닝모드", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(R.anim.anim_slide_down,R.anim.anim_slide_up);
    }
}
