package com.cookandroid.tuner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class play_mode extends AppCompatActivity {

    ImageButton btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh;
    ImageButton btn_switch, btn_sharp;
    ImageButton btn_vive;
    ConstraintF CL;
    boolean changemod = false;
    PianoView pianoView;
    boolean vive = true;
    boolean[] same = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
    int[] jangjo ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    long LOADING_TIME = System.currentTimeMillis();


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        // Setup onTouchEvent for detecting type of touch gesture
        // Sensey.getInstance().setupDispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void finish(){
        overridePendingTransition(R.anim.anim_slide_not_move,R.anim.anim_slide_up);
        super.finish();
    }

    public void set_false(){
        for(int i=0; i<same.length;i++){
            same[i] = false;
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(Color.BLACK);
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
        btn_sharp = (ImageButton)findViewById(R.id.btn_sharp);

        btn_vive = (ImageButton) findViewById(R.id.btn_vive);
        CL = findViewById(R.id.CL);



        final Animation anim_Twist = AnimationUtils.loadAnimation(
                this,R.anim.anim_twist);
        final ScaleSrc Scale = new ScaleSrc(play_mode.this);
        final SoundPool sp = Scale.sp;



        CL.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final Rect[] chktouch = new Rect[17];
                for (int i=0; i<chktouch.length;i++){
                    chktouch[i] = new Rect();
                }
                btn_c_l.getHitRect(chktouch[0]);
                btn_d_l.getHitRect(chktouch[1]);
                btn_e_l.getHitRect(chktouch[2]);
                btn_f_l.getHitRect(chktouch[3]);
                btn_g_l.getHitRect(chktouch[4]);
                btn_a_l.getHitRect(chktouch[5]);
                btn_b_l.getHitRect(chktouch[6]);
                btn_c_h.getHitRect(chktouch[7]);
                btn_d_h.getHitRect(chktouch[8]);
                btn_e_h.getHitRect(chktouch[9]);
                btn_f_h.getHitRect(chktouch[10]);
                btn_g_h.getHitRect(chktouch[11]);
                btn_a_h.getHitRect(chktouch[12]);
                btn_b_h.getHitRect(chktouch[13]);
                btn_c_hh.getHitRect(chktouch[14]);
                btn_d_hh.getHitRect(chktouch[15]);
                btn_e_hh.getHitRect(chktouch[16]);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        if (chktouch[0].contains((int)event.getX(), (int)event.getY())) {
                            if (!same[0]){
                                if(!changemod){
                                    if(vive){
                                        vibrator.vibrate(30);
                                        btn_c_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[0]==0){
                                        sp.play(Scale.do0_0,1,1,1,0,1.0f);
                                        btn_c_l.setImageResource(R.drawable.keyc_c);
                                    }
                                    else if(jangjo[0]==1){
                                        sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                        btn_c_l.setImageResource(R.drawable.keycs_c);
                                    }
                                    else{
                                        sp.play(Scale.si00_0,1,1,1,0,1.0f);
                                        btn_c_l.setImageResource(R.drawable.keycf_c);
                                    }

                                    new Handler().postDelayed(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[0]==0){
                                                btn_c_l.setImageResource(R.drawable.key_c);
                                            }
                                            else if(jangjo[0]==1){
                                                btn_c_l.setImageResource(R.drawable.keys_c);
                                            }
                                            else{
                                                btn_c_l.setImageResource(R.drawable.keyf_c);
                                            }
                                        }
                                    }, 500);
                                }
                                else{
                                    if (jangjo[0]==0) {
                                        jangjo[0]++;
                                        btn_c_l.setImageResource(R.drawable.keys_c);
                                        sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                    }
                                    else if(jangjo[0]==1) {
                                        jangjo[0]++;
                                        btn_c_l.setImageResource(R.drawable.keyf_c);
                                        sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                    }
                                    else {
                                        jangjo[0] = 0;
                                        btn_c_l.setImageResource(R.drawable.key_c);
                                        sp.play(Scale.do0_0,1,1,1,0,1.0f);
                                    }
                                }
                                set_false();
                                same[0] = true;
                            }
                        }
                        else if (chktouch[1].contains((int)event.getX(), (int)event.getY())) {
                            if (!same[1]){
                                if(!changemod){
                                    if(vive){
                                        vibrator.vibrate(30);
                                        btn_d_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[1]==0){
                                        sp.play(Scale.re0_0,1,1,1,0,1.0f);
                                        btn_d_l.setImageResource(R.drawable.keyc_d);
                                    }
                                    else if(jangjo[1]==1){
                                        sp.play(Scale.re0_5,1,1,1,0,1.0f);
                                        btn_d_l.setImageResource(R.drawable.keycs_d);
                                    }
                                    else{
                                        sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                        btn_d_l.setImageResource(R.drawable.keycf_d);
                                    }

                                    new Handler().postDelayed(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            if (jangjo[1]==0){
                                                btn_d_l.setImageResource(R.drawable.key_d);
                                            }
                                            else if(jangjo[1]==1){
                                                btn_d_l.setImageResource(R.drawable.keys_d);
                                            }
                                            else{
                                                btn_d_l.setImageResource(R.drawable.keyf_d);
                                            }
                                        }
                                    }, 500);
                                }
                                else{
                                    if (jangjo[1]==0) {
                                        jangjo[1]++;
                                        btn_d_l.setImageResource(R.drawable.keys_d);
                                        sp.play(Scale.re0_5,1,1,1,0,1.0f);
                                    }
                                    else if(jangjo[1]==1) {
                                        jangjo[1]++;
                                        btn_d_l.setImageResource(R.drawable.keyf_d);
                                        sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                    }
                                    else {
                                        jangjo[1] = 0;
                                        btn_d_l.setImageResource(R.drawable.key_d);
                                        sp.play(Scale.re0_0,1,1,1,0,1.0f);
                                    }
                                }
                                set_false();
                                same[1] = true;
                            }
                        }
                        else if (chktouch[2].contains((int)event.getX(), (int)event.getY())) {
                            if (!same[2]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_e_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[2] == 0) {
                                        sp.play(Scale.mi0_0, 1, 1, 1, 0, 1.0f);
                                        btn_e_l.setImageResource(R.drawable.keyc_e);
                                    } else if (jangjo[2] == 1) {
                                        sp.play(Scale.mi0_5, 1, 1, 1, 0, 1.0f);
                                        btn_e_l.setImageResource(R.drawable.keyc_f);
                                    } else {
                                        sp.play(Scale.re0_5, 1, 1, 1, 0, 1.0f);
                                        btn_e_l.setImageResource(R.drawable.keycf_e);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (jangjo[2] == 0) {
                                                btn_e_l.setImageResource(R.drawable.key_e);
                                            } else if (jangjo[2] == 1) {
                                                btn_e_l.setImageResource(R.drawable.key_f);
                                            } else {
                                                btn_e_l.setImageResource(R.drawable.keyf_e);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[2] == 0) {
                                        jangjo[2]++;
                                        btn_e_l.setImageResource(R.drawable.key_f);
                                        sp.play(Scale.mi0_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[2] == 1) {
                                        jangjo[2]++;
                                        btn_e_l.setImageResource(R.drawable.keyf_e);
                                        sp.play(Scale.re0_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[2] = 0;
                                        btn_e_l.setImageResource(R.drawable.key_e);
                                        sp.play(Scale.mi0_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[2] = true;
                            }

                        }
                        else if (chktouch[3].contains((int)event.getX(), (int)event.getY())){
                            if (!same[3]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_f_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[3] == 0) {
                                        sp.play(Scale.fa0_0, 1, 1, 1, 0, 1.0f);
                                        btn_f_l.setImageResource(R.drawable.keyc_f);
                                    } else if (jangjo[3] == 1) {
                                        sp.play(Scale.fa0_5, 1, 1, 1, 0, 1.0f);
                                        btn_f_l.setImageResource(R.drawable.keycs_f);
                                    } else {
                                        sp.play(Scale.mi0_0, 1, 1, 1, 0, 1.0f);
                                        btn_f_l.setImageResource(R.drawable.keyc_e);
                                    }
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (jangjo[3] == 0) {
                                                btn_f_l.setImageResource(R.drawable.key_f);
                                            } else if (jangjo[3] == 1) {
                                                btn_f_l.setImageResource(R.drawable.keys_f);
                                            } else {
                                                btn_f_l.setImageResource(R.drawable.key_e);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[3] == 0) {
                                        jangjo[3]++;
                                        btn_f_l.setImageResource(R.drawable.keys_f);
                                        sp.play(Scale.fa0_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[3] == 1) {
                                        jangjo[3]++;
                                        btn_f_l.setImageResource(R.drawable.key_e);
                                        sp.play(Scale.mi0_0, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[3] = 0;
                                        btn_f_l.setImageResource(R.drawable.key_f);
                                        sp.play(Scale.fa0_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[3] = true;
                            }
                        }
                        else if (chktouch[4].contains((int)event.getX(), (int)event.getY())){
                            if (!same[4]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_g_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[4] == 0) {
                                        sp.play(Scale.sol0_0, 1, 1, 1, 0, 1.0f);
                                        btn_g_l.setImageResource(R.drawable.keyc_g);
                                    } else if (jangjo[4] == 1) {
                                        sp.play(Scale.sol0_5, 1, 1, 1, 0, 1.0f);
                                        btn_g_l.setImageResource(R.drawable.keycs_g);
                                    } else {
                                        sp.play(Scale.fa0_5, 1, 1, 1, 0, 1.0f);
                                        btn_g_l.setImageResource(R.drawable.keycf_g);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (jangjo[4] == 0) {
                                                btn_g_l.setImageResource(R.drawable.key_g);
                                            } else if (jangjo[4] == 1) {
                                                btn_g_l.setImageResource(R.drawable.keys_g);
                                            } else {
                                                btn_g_l.setImageResource(R.drawable.keyf_g);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[4] == 0) {
                                        jangjo[4]++;
                                        btn_g_l.setImageResource(R.drawable.keys_g);
                                        sp.play(Scale.sol0_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[4] == 1) {
                                        jangjo[4]++;
                                        btn_g_l.setImageResource(R.drawable.keyf_g);
                                        sp.play(Scale.fa0_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[4] = 0;
                                        btn_g_l.setImageResource(R.drawable.key_g);
                                        sp.play(Scale.sol0_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[4] = true;
                            }
                        }
                        else if (chktouch[5].contains((int)event.getX(), (int)event.getY())){
                            if (!same[5]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_a_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[5] == 0) {
                                        sp.play(Scale.la0_0, 1, 1, 1, 0, 1.0f);
                                        btn_a_l.setImageResource(R.drawable.keyc_a);
                                    } else if (jangjo[5] == 1) {
                                        sp.play(Scale.la0_5, 1, 1, 1, 0, 1.0f);
                                        btn_a_l.setImageResource(R.drawable.keycs_a);
                                    } else {
                                        sp.play(Scale.sol0_5, 1, 1, 1, 0, 1.0f);
                                        btn_a_l.setImageResource(R.drawable.keycf_a);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[5] == 0) {
                                                btn_a_l.setImageResource(R.drawable.key_a);
                                            } else if (jangjo[5] == 1) {
                                                btn_a_l.setImageResource(R.drawable.keys_a);
                                            } else {
                                                btn_a_l.setImageResource(R.drawable.keyf_a);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[5] == 0) {
                                        jangjo[5]++;
                                        btn_a_l.setImageResource(R.drawable.keys_a);
                                        sp.play(Scale.la0_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[5] == 1) {
                                        jangjo[5]++;
                                        btn_a_l.setImageResource(R.drawable.keyf_a);
                                        sp.play(Scale.sol0_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[5] = 0;
                                        btn_a_l.setImageResource(R.drawable.key_a);
                                        sp.play(Scale.la0_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[5] = true;
                            }
                        }
                        else if (chktouch[6].contains((int)event.getX(), (int)event.getY())){
                            if (!same[6]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_b_l.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[6] == 0) {
                                        sp.play(Scale.si0_0, 1, 1, 1, 0, 1.0f);
                                        btn_b_l.setImageResource(R.drawable.keyc_b);
                                    } else if (jangjo[6] == 1) {
                                        sp.play(Scale.si0_5, 1, 1, 1, 0, 1.0f);
                                        btn_b_l.setImageResource(R.drawable.keycs_b_red);
                                    } else {
                                        sp.play(Scale.la0_5, 1, 1, 1, 0, 1.0f);
                                        btn_b_l.setImageResource(R.drawable.keycf_b);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[6] == 0) {
                                                btn_b_l.setImageResource(R.drawable.key_b);
                                            } else if (jangjo[6] == 1) {
                                                btn_b_l.setImageResource(R.drawable.keys_b_red);
                                            } else {
                                                btn_b_l.setImageResource(R.drawable.keyf_b);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[6] == 0) {
                                        jangjo[6]++;
                                        btn_b_l.setImageResource(R.drawable.keys_b_red);
                                        sp.play(Scale.si0_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[6] == 1) {
                                        jangjo[6]++;
                                        btn_b_l.setImageResource(R.drawable.keyf_b);
                                        sp.play(Scale.la0_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[6] = 0;
                                        btn_b_l.setImageResource(R.drawable.key_b);
                                        sp.play(Scale.si0_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[6] = true;
                            }
                        }
                        else if (chktouch[7].contains((int)event.getX(), (int)event.getY())){
                            if (!same[7]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_c_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[7] == 0) {
                                        sp.play(Scale.do1_0, 1, 1, 1, 0, 1.0f);
                                        btn_c_h.setImageResource(R.drawable.keyc_c1);
                                    } else if (jangjo[7] == 1) {
                                        sp.play(Scale.do1_5, 1, 1, 1, 0, 1.0f);
                                        btn_c_h.setImageResource(R.drawable.keycs_c1);
                                    } else {
                                        sp.play(Scale.si0_0, 1, 1, 1, 0, 1.0f);
                                        btn_c_h.setImageResource(R.drawable.keycf_c1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[7] == 0) {
                                                btn_c_h.setImageResource(R.drawable.key_c1);
                                            } else if (jangjo[7] == 1) {
                                                btn_c_h.setImageResource(R.drawable.keys_c1);
                                            } else {
                                                btn_c_h.setImageResource(R.drawable.keyf_c1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[7] == 0) {
                                        jangjo[7]++;
                                        btn_c_h.setImageResource(R.drawable.keys_c1);
                                        sp.play(Scale.do1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[7] == 1) {
                                        jangjo[7]++;
                                        btn_c_h.setImageResource(R.drawable.keyf_c1);
                                        sp.play(Scale.si0_0, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[7] = 0;
                                        btn_c_h.setImageResource(R.drawable.key_c1);
                                        sp.play(Scale.do1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[7] = true;
                            }
                        }
                        else if (chktouch[8].contains((int)event.getX(), (int)event.getY())){
                            if (!same[8]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_d_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[8] == 0) {
                                        sp.play(Scale.re1_0, 1, 1, 1, 0, 1.0f);
                                        btn_d_h.setImageResource(R.drawable.keyc_d1);
                                    } else if (jangjo[8] == 1) {
                                        sp.play(Scale.re1_5, 1, 1, 1, 0, 1.0f);
                                        btn_d_h.setImageResource(R.drawable.keycs_d1);
                                    } else {
                                        sp.play(Scale.do1_5, 1, 1, 1, 0, 1.0f);
                                        btn_d_h.setImageResource(R.drawable.keycf_d1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[8] == 0) {
                                                btn_d_h.setImageResource(R.drawable.key_d1);
                                            } else if (jangjo[8] == 1) {
                                                btn_d_h.setImageResource(R.drawable.keys_d1);
                                            } else {
                                                btn_d_h.setImageResource(R.drawable.keyf_d1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[8] == 0) {
                                        jangjo[8]++;
                                        btn_d_h.setImageResource(R.drawable.keys_d1);
                                        sp.play(Scale.re1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[8] == 1) {
                                        jangjo[8]++;
                                        btn_d_h.setImageResource(R.drawable.keyf_d1);
                                        sp.play(Scale.do1_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[8] = 0;
                                        btn_d_h.setImageResource(R.drawable.key_d1);
                                        sp.play(Scale.re1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[8] = true;
                            }
                        }
                        else if (chktouch[9].contains((int)event.getX(), (int)event.getY())){
                            if (!same[9]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_e_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[9] == 0) {
                                        sp.play(Scale.mi1_0, 1, 1, 1, 0, 1.0f);
                                        btn_e_h.setImageResource(R.drawable.keyc_e1);
                                    } else if (jangjo[9] == 1) {
                                        sp.play(Scale.mi1_5, 1, 1, 1, 0, 1.0f);
                                        btn_e_h.setImageResource(R.drawable.keyc_f1);
                                    } else {
                                        sp.play(Scale.re1_5, 1, 1, 1, 0, 1.0f);
                                        btn_e_h.setImageResource(R.drawable.keycf_e1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[9] == 0) {
                                                btn_e_h.setImageResource(R.drawable.key_e1);
                                            } else if (jangjo[9] == 1) {
                                                btn_e_h.setImageResource(R.drawable.key_f1);
                                            } else {
                                                btn_e_h.setImageResource(R.drawable.keyf_e1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[9] == 0) {
                                        jangjo[9]++;
                                        btn_e_h.setImageResource(R.drawable.keys_f1);
                                        sp.play(Scale.mi1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[9] == 1) {
                                        jangjo[9]++;
                                        btn_e_h.setImageResource(R.drawable.keyf_e1);
                                        sp.play(Scale.re1_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[9] = 0;
                                        btn_e_h.setImageResource(R.drawable.key_e1);
                                        sp.play(Scale.mi1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[9] = true;
                            }
                        }
                        else if (chktouch[10].contains((int)event.getX(), (int)event.getY())){
                            if (!same[10]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_f_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[10] == 0) {
                                        sp.play(Scale.fa1_0, 1, 1, 1, 0, 1.0f);
                                        btn_f_h.setImageResource(R.drawable.keyc_f1);
                                    } else if (jangjo[10] == 1) {
                                        sp.play(Scale.fa1_5, 1, 1, 1, 0, 1.0f);
                                        btn_f_h.setImageResource(R.drawable.keycs_f1);
                                    } else {
                                        sp.play(Scale.mi1_0, 1, 1, 1, 0, 1.0f);
                                        btn_f_h.setImageResource(R.drawable.keyc_e1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[10] == 0) {
                                                btn_f_h.setImageResource(R.drawable.key_f1);
                                            } else if (jangjo[10] == 1) {
                                                btn_f_h.setImageResource(R.drawable.keys_f1);
                                            } else {
                                                btn_f_h.setImageResource(R.drawable.key_e1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[10] == 0) {
                                        jangjo[10]++;
                                        btn_f_h.setImageResource(R.drawable.keys_f1);
                                        sp.play(Scale.fa1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[10] == 1) {
                                        jangjo[10]++;
                                        btn_f_h.setImageResource(R.drawable.key_e1);
                                        sp.play(Scale.mi1_0, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[10] = 0;
                                        btn_f_h.setImageResource(R.drawable.key_f1);
                                        sp.play(Scale.fa1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[10] = true;
                            }
                        }
                        else if (chktouch[11].contains((int)event.getX(), (int)event.getY())){
                            if (!same[11]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_g_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[11] == 0) {
                                        sp.play(Scale.sol1_0, 1, 1, 1, 0, 1.0f);
                                        btn_g_h.setImageResource(R.drawable.keyc_g1);
                                    } else if (jangjo[11] == 1) {
                                        sp.play(Scale.sol1_5, 1, 1, 1, 0, 1.0f);
                                        btn_g_h.setImageResource(R.drawable.keycs_g1);
                                    } else {
                                        sp.play(Scale.fa1_5, 1, 1, 1, 0, 1.0f);
                                        btn_g_h.setImageResource(R.drawable.keycf_g1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[11] == 0) {
                                                btn_g_h.setImageResource(R.drawable.key_g1);
                                            } else if (jangjo[11] == 1) {
                                                btn_g_h.setImageResource(R.drawable.keys_g1);
                                            } else {
                                                btn_g_h.setImageResource(R.drawable.keyf_g1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[11] == 0) {
                                        jangjo[11]++;
                                        btn_g_h.setImageResource(R.drawable.keys_g1);
                                        sp.play(Scale.sol1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[11] == 1) {
                                        jangjo[11]++;
                                        btn_g_h.setImageResource(R.drawable.keyf_g1);
                                        sp.play(Scale.fa1_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[11] = 0;
                                        btn_g_h.setImageResource(R.drawable.key_g1);
                                        sp.play(Scale.sol1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[11] = true;
                            }
                        }
                        else if (chktouch[12].contains((int)event.getX(), (int)event.getY())){
                            if (!same[12]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_a_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[12] == 0) {
                                        sp.play(Scale.la1_0, 1, 1, 1, 0, 1.0f);
                                        btn_a_h.setImageResource(R.drawable.keyc_a1);
                                    } else if (jangjo[12] == 1) {
                                        sp.play(Scale.la1_5, 1, 1, 1, 0, 1.0f);
                                        btn_a_h.setImageResource(R.drawable.keycs_a1);
                                    } else {
                                        sp.play(Scale.sol1_5, 1, 1, 1, 0, 1.0f);
                                        btn_a_h.setImageResource(R.drawable.keycf_a1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[12] == 0) {
                                                btn_a_h.setImageResource(R.drawable.key_a1);
                                            } else if (jangjo[12] == 1) {
                                                btn_a_h.setImageResource(R.drawable.keys_a1);
                                            } else {
                                                btn_a_h.setImageResource(R.drawable.keyf_a1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[12] == 0) {
                                        jangjo[12]++;
                                        btn_a_h.setImageResource(R.drawable.keys_a1);
                                        sp.play(Scale.la1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[12] == 1) {
                                        jangjo[12]++;
                                        btn_a_h.setImageResource(R.drawable.keyf_a1);
                                        sp.play(Scale.sol1_5, 1, 1, 1, 0, 1.0f);

                                    } else {
                                        jangjo[12] = 0;
                                        btn_a_h.setImageResource(R.drawable.key_a1);
                                        sp.play(Scale.la1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[12] = true;
                            }
                        }
                        else if (chktouch[13].contains((int)event.getX(), (int)event.getY())){
                            if (!same[13]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_b_h.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[13] == 0) {
                                        sp.play(Scale.si1_0, 1, 1, 1, 0, 1.0f);
                                        btn_b_h.setImageResource(R.drawable.keyc_b1);
                                    } else if (jangjo[13] == 1) {
                                        sp.play(Scale.si1_5, 1, 1, 1, 0, 1.0f);
                                        btn_b_h.setImageResource(R.drawable.keyc_c2);
                                    } else {
                                        sp.play(Scale.la1_5, 1, 1, 1, 0, 1.0f);
                                        btn_b_h.setImageResource(R.drawable.keycf_b1);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[13] == 0) {
                                                btn_b_h.setImageResource(R.drawable.key_b1);
                                            } else if (jangjo[13] == 1) {
                                                btn_b_h.setImageResource(R.drawable.key_c2);
                                            } else {
                                                btn_b_h.setImageResource(R.drawable.keyf_b1);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[13] == 0) {
                                        jangjo[13]++;
                                        btn_b_h.setImageResource(R.drawable.key_c2);
                                        sp.play(Scale.si1_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[13] == 1) {
                                        jangjo[13]++;
                                        btn_b_h.setImageResource(R.drawable.keyf_b1);
                                        sp.play(Scale.la1_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[13] = 0;
                                        btn_b_h.setImageResource(R.drawable.key_b1);
                                        sp.play(Scale.si1_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[13] = true;
                            }
                        }
                        else if (chktouch[14].contains((int)event.getX(), (int)event.getY())){
                            if (!same[14]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_c_hh.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[14] == 0) {
                                        sp.play(Scale.do2_0, 1, 1, 1, 0, 1.0f);
                                        btn_c_hh.setImageResource(R.drawable.keyc_c2);
                                    } else if (jangjo[14] == 1) {
                                        sp.play(Scale.do2_5, 1, 1, 1, 0, 1.0f);
                                        btn_c_hh.setImageResource(R.drawable.keycs_c2);
                                    } else {
                                        sp.play(Scale.si1_0, 1, 1, 1, 0, 1.0f);
                                        btn_c_hh.setImageResource(R.drawable.keycf_c2);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[14] == 0) {
                                                btn_c_hh.setImageResource(R.drawable.key_c2);
                                            } else if (jangjo[14] == 1) {
                                                btn_c_hh.setImageResource(R.drawable.keys_c2);
                                            } else {
                                                btn_c_hh.setImageResource(R.drawable.keyf_c2);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[14] == 0) {
                                        jangjo[14]++;
                                        btn_c_hh.setImageResource(R.drawable.keys_c2);
                                        sp.play(Scale.do2_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[14] == 1) {
                                        jangjo[14]++;
                                        btn_c_hh.setImageResource(R.drawable.keyf_c2);
                                        sp.play(Scale.si1_0, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[14] = 0;
                                        btn_c_hh.setImageResource(R.drawable.key_c2);
                                        sp.play(Scale.do2_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[14] = true;
                            }
                        }
                        else if (chktouch[15].contains((int)event.getX(), (int)event.getY())){
                            if (!same[15]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_d_hh.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[15] == 0) {
                                        sp.play(Scale.re2_0, 1, 1, 1, 0, 1.0f);
                                        btn_d_hh.setImageResource(R.drawable.keyc_d2);
                                    } else if (jangjo[15] == 1) {
                                        sp.play(Scale.re2_5, 1, 1, 1, 0, 1.0f);
                                        btn_d_hh.setImageResource(R.drawable.keycs_d2);
                                    } else {
                                        sp.play(Scale.do2_5, 1, 1, 1, 0, 1.0f);
                                        btn_d_hh.setImageResource(R.drawable.keycf_d2);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[15] == 0) {
                                                btn_d_hh.setImageResource(R.drawable.key_d2);
                                            } else if (jangjo[15] == 1) {
                                                btn_d_hh.setImageResource(R.drawable.keys_d2);
                                            } else {
                                                btn_d_hh.setImageResource(R.drawable.keyf_d2);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[15] == 0) {
                                        jangjo[15]++;
                                        btn_d_hh.setImageResource(R.drawable.keys_d2);
                                        sp.play(Scale.re2_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[15] == 1) {
                                        jangjo[15]++;
                                        btn_d_hh.setImageResource(R.drawable.keyf_d2);
                                        sp.play(Scale.do2_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[15] = 0;
                                        btn_d_hh.setImageResource(R.drawable.key_d2);
                                        sp.play(Scale.re2_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[15] = true;
                            }
                        }
                        else if (chktouch[16].contains((int)event.getX(), (int)event.getY())){
                            if (!same[16]) {
                                if (!changemod) {
                                    if (vive) {
                                        vibrator.vibrate(30);
                                        btn_e_hh.startAnimation(anim_Twist);
                                    }
                                    if (jangjo[16] == 0) {
                                        sp.play(Scale.mi2_0, 1, 1, 1, 0, 1.0f);
                                        btn_e_hh.setImageResource(R.drawable.keyc_e2);
                                    } else if (jangjo[16] == 1) {
                                        sp.play(Scale.mi2_5, 1, 1, 1, 0, 1.0f);
                                        btn_e_hh.setImageResource(R.drawable.keys_e2);
                                    } else {
                                        sp.play(Scale.re2_5, 1, 1, 1, 0, 1.0f);
                                        btn_e_hh.setImageResource(R.drawable.keycf_e2);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //여기에 딜레이 후 시작할 작업들을 입력
                                            if (jangjo[16] == 0) {
                                                btn_e_hh.setImageResource(R.drawable.key_e2);
                                            } else if (jangjo[16] == 1) {
                                                btn_e_hh.setImageResource(R.drawable.keys_e2);
                                            } else {
                                                btn_e_hh.setImageResource(R.drawable.keyf_e2);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    if (jangjo[16] == 0) {
                                        jangjo[16]++;
                                        btn_e_hh.setImageResource(R.drawable.keys_e2);
                                        sp.play(Scale.mi2_5, 1, 1, 1, 0, 1.0f);
                                    } else if (jangjo[16] == 1) {
                                        jangjo[16]++;
                                        btn_e_hh.setImageResource(R.drawable.keyf_e2);
                                        sp.play(Scale.re2_5, 1, 1, 1, 0, 1.0f);
                                    } else {
                                        jangjo[16] = 0;
                                        btn_e_hh.setImageResource(R.drawable.key_e2);
                                        sp.play(Scale.mi2_0, 1, 1, 1, 0, 1.0f);
                                    }
                                }
                                set_false();
                                same[16] = true;
                            }
                        }


                        return true;
                }
                return false;
            }
        });
//
//        View.OnTouchListener touchListener = new View.OnTouchListener(){
//            public boolean onTouch(View v, MotionEvent event){
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                    case MotionEvent.ACTION_MOVE:
//                        if (chktouch[0].contains((int)event.getX(), (int)event.getY())) {
//                            if(!changemod){
//                                if(vive){
//                                    vibrator.vibrate(30);
//                                    btn_c_l.startAnimation(anim_Twist);
//                                }
//                                if (jangjo[0]==0){
//                                    sp.play(Scale.do0_0,1,1,1,0,1.0f);
//                                    btn_c_l.setImageResource(R.drawable.keyc_c);
//                                }
//                                else if(jangjo[0]==1){
//                                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
//                                    btn_c_l.setImageResource(R.drawable.keycs_c);
//                                }
//                                else{
//                                    sp.play(Scale.si00_0,1,1,1,0,1.0f);
//                                    btn_c_l.setImageResource(R.drawable.keycf_c);
//                                }
//
//                                new Handler().postDelayed(new Runnable()
//                                {
//                                    @Override
//                                    public void run()
//                                    {
//                                        //여기에 딜레이 후 시작할 작업들을 입력
//                                        if (jangjo[0]==0){
//                                            btn_c_l.setImageResource(R.drawable.key_c);
//                                        }
//                                        else if(jangjo[0]==1){
//                                            btn_c_l.setImageResource(R.drawable.keys_c);
//                                        }
//                                        else{
//                                            btn_c_l.setImageResource(R.drawable.keyf_c);
//                                        }
//                                    }
//                                }, 500);
//                            }
//                            else{
//                                if (jangjo[0]==0) {
//                                    jangjo[0]++;
//                                    btn_c_l.setImageResource(R.drawable.keys_c);
//                                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
//                                }
//                                else if(jangjo[0]==1) {
//                                    jangjo[0]++;
//                                    btn_c_l.setImageResource(R.drawable.keyf_c);
//                                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
//                                }
//                                else {
//                                    jangjo[0] = 0;
//                                    btn_c_l.setImageResource(R.drawable.key_c);
//                                    sp.play(Scale.do0_0,1,1,1,0,1.0f);
//                                }
//                            }
//                        }
//                        else if (chktouch[1].contains((int)event.getX(), (int)event.getY())){
//                            if(!changemod){
//                                if(vive){
//                                    vibrator.vibrate(30);
//                                    btn_d_l.startAnimation(anim_Twist);
//                                }
//                                if (jangjo[1]==0){
//                                    sp.play(Scale.re0_0,1,1,1,0,1.0f);
//                                    btn_d_l.setImageResource(R.drawable.keyc_d);
//                                }
//                                else if(jangjo[1]==1){
//                                    sp.play(Scale.re0_5,1,1,1,0,1.0f);
//                                    btn_d_l.setImageResource(R.drawable.keycs_d);
//                                }
//                                else{
//                                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
//                                    btn_d_l.setImageResource(R.drawable.keycf_d);
//                                }
//
//                                new Handler().postDelayed(new Runnable()
//                                {
//                                    @Override
//                                    public void run()
//                                    {
//                                        if (jangjo[1]==0){
//                                            btn_d_l.setImageResource(R.drawable.key_d);
//                                        }
//                                        else if(jangjo[1]==1){
//                                            btn_d_l.setImageResource(R.drawable.keys_d);
//                                        }
//                                        else{
//                                            btn_d_l.setImageResource(R.drawable.keyf_d);
//                                        }
//                                    }
//                                }, 500);
//                            }
//                            else{
//                                if (jangjo[1]==0) {
//                                    jangjo[1]++;
//                                    btn_d_l.setImageResource(R.drawable.keys_d);
//                                    sp.play(Scale.re0_5,1,1,1,0,1.0f);
//                                }
//                                else if(jangjo[1]==1) {
//                                    jangjo[1]++;
//                                    btn_d_l.setImageResource(R.drawable.keyf_d);
//                                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
//                                }
//                                else {
//                                    jangjo[1] = 0;
//                                    btn_d_l.setImageResource(R.drawable.key_d);
//                                    sp.play(Scale.re0_0,1,1,1,0,1.0f);
//                                }
//                            }
//                        }
//                        return true;
//                }
//                return false;
//            }
//        };
//        btn_c_l.setOnTouchListener(touchListener);
        btn_c_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_c_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[0]==0){
                                sp.play(Scale.do0_0,1,1,1,0,1.0f);
                                btn_c_l.setImageResource(R.drawable.keyc_c);
                            }
                            else if(jangjo[0]==1){
                                sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                btn_c_l.setImageResource(R.drawable.keycs_c);
                            }
                            else{
                                sp.play(Scale.si00_0,1,1,1,0,1.0f);
                                btn_c_l.setImageResource(R.drawable.keycf_c);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[0]==0){
                                        btn_c_l.setImageResource(R.drawable.key_c);
                                    }
                                    else if(jangjo[0]==1){
                                        btn_c_l.setImageResource(R.drawable.keys_c);
                                    }
                                    else{
                                        btn_c_l.setImageResource(R.drawable.keyf_c);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[0]==0) {
                                jangjo[0]++;
                                btn_c_l.setImageResource(R.drawable.keys_c);
                                sp.play(Scale.do0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[0]==1) {
                                jangjo[0]++;
                                btn_c_l.setImageResource(R.drawable.keyf_c);
                                sp.play(Scale.do0_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[0] = 0;
                                btn_c_l.setImageResource(R.drawable.key_c);
                                sp.play(Scale.do0_0,1,1,1,0,1.0f);
                            }
                        }
                }
                return false;
            }
        });
        btn_d_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                    case MotionEvent.ACTION_DOWN:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_d_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[1]==0){
                                sp.play(Scale.re0_0,1,1,1,0,1.0f);
                                btn_d_l.setImageResource(R.drawable.keyc_d);
                            }
                            else if(jangjo[1]==1){
                                sp.play(Scale.re0_5,1,1,1,0,1.0f);
                                btn_d_l.setImageResource(R.drawable.keycs_d);
                            }
                            else{
                                sp.play(Scale.do0_5,1,1,1,0,1.0f);
                                btn_d_l.setImageResource(R.drawable.keycf_d);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    if (jangjo[1]==0){
                                        btn_d_l.setImageResource(R.drawable.key_d);
                                    }
                                    else if(jangjo[1]==1){
                                        btn_d_l.setImageResource(R.drawable.keys_d);
                                    }
                                    else{
                                        btn_d_l.setImageResource(R.drawable.keyf_d);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[1]==0) {
                                jangjo[1]++;
                                btn_d_l.setImageResource(R.drawable.keys_d);
                                sp.play(Scale.re0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[1]==1) {
                                jangjo[1]++;
                                btn_d_l.setImageResource(R.drawable.keyf_d);
                                sp.play(Scale.do0_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[1] = 0;
                                btn_d_l.setImageResource(R.drawable.key_d);
                                sp.play(Scale.re0_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_e_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_e_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[2]==0){
                                sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                                btn_e_l.setImageResource(R.drawable.keyc_e);
                            }
                            else if(jangjo[2]==1){
                                sp.play(Scale.mi0_5,1,1,1,0,1.0f);
                                btn_e_l.setImageResource(R.drawable.keyc_f);
                            }
                            else{
                                sp.play(Scale.re0_5,1,1,1,0,1.0f);
                                btn_e_l.setImageResource(R.drawable.keycf_e);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    if (jangjo[2]==0){
                                        btn_e_l.setImageResource(R.drawable.key_e);
                                    }
                                    else if(jangjo[2]==1){
                                        btn_e_l.setImageResource(R.drawable.key_f);
                                    }
                                    else{
                                        btn_e_l.setImageResource(R.drawable.keyf_e);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[2]==0) {
                                jangjo[2]++;
                                btn_e_l.setImageResource(R.drawable.key_f);
                                sp.play(Scale.mi0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[2]==1) {
                                jangjo[2]++;
                                btn_e_l.setImageResource(R.drawable.keyf_e);
                                sp.play(Scale.re0_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[2] = 0;
                                btn_e_l.setImageResource(R.drawable.key_e);
                                sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_f_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_f_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[3]==0){
                                sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                                btn_f_l.setImageResource(R.drawable.keyc_f);
                            }
                            else if(jangjo[3]==1){
                                sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                                btn_f_l.setImageResource(R.drawable.keycs_f);
                            }
                            else{
                                sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                                btn_f_l.setImageResource(R.drawable.keyc_e);
                            }
                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    if (jangjo[3]==0){
                                        btn_f_l.setImageResource(R.drawable.key_f);
                                    }
                                    else if(jangjo[3]==1){
                                        btn_f_l.setImageResource(R.drawable.keys_f);
                                    }
                                    else{
                                        btn_f_l.setImageResource(R.drawable.key_e);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[3]==0) {
                                jangjo[3]++;
                                btn_f_l.setImageResource(R.drawable.keys_f);
                                sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[3]==1) {
                                jangjo[3]++;
                                btn_f_l.setImageResource(R.drawable.key_e);
                                sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[3] = 0;
                                btn_f_l.setImageResource(R.drawable.key_f);
                                sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_g_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_g_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[4]==0){
                                sp.play(Scale.sol0_0,1,1,1,0,1.0f);
                                btn_g_l.setImageResource(R.drawable.keyc_g);
                            }
                            else if(jangjo[4]==1){
                                sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                                btn_g_l.setImageResource(R.drawable.keycs_g);
                            }
                            else{
                                sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                                btn_g_l.setImageResource(R.drawable.keycf_g);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    if (jangjo[4]==0){
                                        btn_g_l.setImageResource(R.drawable.key_g);
                                    }
                                    else if(jangjo[4]==1){
                                        btn_g_l.setImageResource(R.drawable.keys_g);
                                    }
                                    else{
                                        btn_g_l.setImageResource(R.drawable.keyf_g);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[4]==0) {
                                jangjo[4]++;
                                btn_g_l.setImageResource(R.drawable.keys_g);
                                sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[4]==1) {
                                jangjo[4]++;
                                btn_g_l.setImageResource(R.drawable.keyf_g);
                                sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[4] = 0;
                                btn_g_l.setImageResource(R.drawable.key_g);
                                sp.play(Scale.sol0_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_a_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_a_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[5]==0){
                                sp.play(Scale.la0_0,1,1,1,0,1.0f);
                                btn_a_l.setImageResource(R.drawable.keyc_a);
                            }
                            else if(jangjo[5]==1){
                                sp.play(Scale.la0_5,1,1,1,0,1.0f);
                                btn_a_l.setImageResource(R.drawable.keycs_a);
                            }
                            else{
                                sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                                btn_a_l.setImageResource(R.drawable.keycf_a);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[5]==0){
                                        btn_a_l.setImageResource(R.drawable.key_a);
                                    }
                                    else if(jangjo[5]==1){
                                        btn_a_l.setImageResource(R.drawable.keys_a);
                                    }
                                    else{
                                        btn_a_l.setImageResource(R.drawable.keyf_a);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[5]==0) {
                                jangjo[5]++;
                                btn_a_l.setImageResource(R.drawable.keys_a);
                                sp.play(Scale.la0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[5]==1) {
                                jangjo[5]++;
                                btn_a_l.setImageResource(R.drawable.keyf_a);
                                sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[5] = 0;
                                btn_a_l.setImageResource(R.drawable.key_a);
                                sp.play(Scale.la0_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_b_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_b_l.startAnimation(anim_Twist);
                            }
                            if (jangjo[6]==0){
                                sp.play(Scale.si0_0,1,1,1,0,1.0f);
                                btn_b_l.setImageResource(R.drawable.keyc_b);
                            }
                            else if(jangjo[6]==1){
                                sp.play(Scale.si0_5,1,1,1,0,1.0f);
                                btn_b_l.setImageResource(R.drawable.keycs_b_red);
                            }
                            else{
                                sp.play(Scale.la0_5,1,1,1,0,1.0f);
                                btn_b_l.setImageResource(R.drawable.keycf_b);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[6]==0){
                                        btn_b_l.setImageResource(R.drawable.key_b);
                                    }
                                    else if(jangjo[6]==1){
                                        btn_b_l.setImageResource(R.drawable.keys_b_red);
                                    }
                                    else{
                                        btn_b_l.setImageResource(R.drawable.keyf_b);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[6]==0) {
                                jangjo[6]++;
                                btn_b_l.setImageResource(R.drawable.keys_b_red);
                                sp.play(Scale.si0_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[6]==1) {
                                jangjo[6]++;
                                btn_b_l.setImageResource(R.drawable.keyf_b);
                                sp.play(Scale.la0_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[6] = 0;
                                btn_b_l.setImageResource(R.drawable.key_b);
                                sp.play(Scale.si0_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_c_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_c_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[7]==0){
                                sp.play(Scale.do1_0,1,1,1,0,1.0f);
                                btn_c_h.setImageResource(R.drawable.keyc_c1);
                            }
                            else if(jangjo[7]==1){
                                sp.play(Scale.do1_5,1,1,1,0,1.0f);
                                btn_c_h.setImageResource(R.drawable.keycs_c1);
                            }
                            else{
                                sp.play(Scale.si0_0,1,1,1,0,1.0f);
                                btn_c_h.setImageResource(R.drawable.keycf_c1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[7]==0){
                                        btn_c_h.setImageResource(R.drawable.key_c1);
                                    }
                                    else if(jangjo[7]==1){
                                        btn_c_h.setImageResource(R.drawable.keys_c1);
                                    }
                                    else{
                                        btn_c_h.setImageResource(R.drawable.keyf_c1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[7]==0) {
                                jangjo[7]++;
                                btn_c_h.setImageResource(R.drawable.keys_c1);
                                sp.play(Scale.do1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[7]==1) {
                                jangjo[7]++;
                                btn_c_h.setImageResource(R.drawable.keyf_c1);
                                sp.play(Scale.si0_0,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[7] = 0;
                                btn_c_h.setImageResource(R.drawable.key_c1);
                                sp.play(Scale.do1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_d_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_d_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[8]==0){
                                sp.play(Scale.re1_0,1,1,1,0,1.0f);
                                btn_d_h.setImageResource(R.drawable.keyc_d1);
                            }
                            else if(jangjo[8]==1){
                                sp.play(Scale.re1_5,1,1,1,0,1.0f);
                                btn_d_h.setImageResource(R.drawable.keycs_d1);
                            }
                            else{
                                sp.play(Scale.do1_5,1,1,1,0,1.0f);
                                btn_d_h.setImageResource(R.drawable.keycf_d1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[8]==0){
                                        btn_d_h.setImageResource(R.drawable.key_d1);
                                    }
                                    else if(jangjo[8]==1){
                                        btn_d_h.setImageResource(R.drawable.keys_d1);
                                    }
                                    else{
                                        btn_d_h.setImageResource(R.drawable.keyf_d1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[8]==0) {
                                jangjo[8]++;
                                btn_d_h.setImageResource(R.drawable.keys_d1);
                                sp.play(Scale.re1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[8]==1) {
                                jangjo[8]++;
                                btn_d_h.setImageResource(R.drawable.keyf_d1);
                                sp.play(Scale.do1_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[8] = 0;
                                btn_d_h.setImageResource(R.drawable.key_d1);
                                sp.play(Scale.re1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_e_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_e_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[9]==0){
                                sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                                btn_e_h.setImageResource(R.drawable.keyc_e1);
                            }
                            else if(jangjo[9]==1){
                                sp.play(Scale.mi1_5,1,1,1,0,1.0f);
                                btn_e_h.setImageResource(R.drawable.keyc_f1);
                            }
                            else{
                                sp.play(Scale.re1_5,1,1,1,0,1.0f);
                                btn_e_h.setImageResource(R.drawable.keycf_e1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[9]==0){
                                        btn_e_h.setImageResource(R.drawable.key_e1);
                                    }
                                    else if(jangjo[9]==1){
                                        btn_e_h.setImageResource(R.drawable.key_f1);
                                    }
                                    else{
                                        btn_e_h.setImageResource(R.drawable.keyf_e1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[9]==0) {
                                jangjo[9]++;
                                btn_e_h.setImageResource(R.drawable.keys_f1);
                                sp.play(Scale.mi1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[9]==1) {
                                jangjo[9]++;
                                btn_e_h.setImageResource(R.drawable.keyf_e1);
                                sp.play(Scale.re1_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[9] = 0;
                                btn_e_h.setImageResource(R.drawable.key_e1);
                                sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_f_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_f_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[10]==0){
                                sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                                btn_f_h.setImageResource(R.drawable.keyc_f1);
                            }
                            else if(jangjo[10]==1){
                                sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                                btn_f_h.setImageResource(R.drawable.keycs_f1);
                            }
                            else{
                                sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                                btn_f_h.setImageResource(R.drawable.keyc_e1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[10]==0){
                                        btn_f_h.setImageResource(R.drawable.key_f1);
                                    }
                                    else if(jangjo[10]==1){
                                        btn_f_h.setImageResource(R.drawable.keys_f1);
                                    }
                                    else{
                                        btn_f_h.setImageResource(R.drawable.key_e1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[10]==0) {
                                jangjo[10]++;
                                btn_f_h.setImageResource(R.drawable.keys_f1);
                                sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[10]==1) {
                                jangjo[10]++;
                                btn_f_h.setImageResource(R.drawable.key_e1);
                                sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[10] = 0;
                                btn_f_h.setImageResource(R.drawable.key_f1);
                                sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        btn_g_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_g_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[11]==0){
                                sp.play(Scale.sol1_0,1,1,1,0,1.0f);
                                btn_g_h.setImageResource(R.drawable.keyc_g1);
                            }
                            else if(jangjo[11]==1){
                                sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                                btn_g_h.setImageResource(R.drawable.keycs_g1);
                            }
                            else{
                                sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                                btn_g_h.setImageResource(R.drawable.keycf_g1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[11]==0){
                                        btn_g_h.setImageResource(R.drawable.key_g1);
                                    }
                                    else if(jangjo[11]==1){
                                        btn_g_h.setImageResource(R.drawable.keys_g1);
                                    }
                                    else{
                                        btn_g_h.setImageResource(R.drawable.keyf_g1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[11]==0) {
                                jangjo[11]++;
                                btn_g_h.setImageResource(R.drawable.keys_g1);
                                sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[11]==1) {
                                jangjo[11]++;
                                btn_g_h.setImageResource(R.drawable.keyf_g1);
                                sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[11] = 0;
                                btn_g_h.setImageResource(R.drawable.key_g1);
                                sp.play(Scale.sol1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });
        btn_a_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_a_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[12]==0){
                                sp.play(Scale.la1_0,1,1,1,0,1.0f);
                                btn_a_h.setImageResource(R.drawable.keyc_a1);
                            }
                            else if(jangjo[12]==1){
                                sp.play(Scale.la1_5,1,1,1,0,1.0f);
                                btn_a_h.setImageResource(R.drawable.keycs_a1);
                            }
                            else{
                                sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                                btn_a_h.setImageResource(R.drawable.keycf_a1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[12]==0){
                                        btn_a_h.setImageResource(R.drawable.key_a1);
                                    }
                                    else if(jangjo[12]==1){
                                        btn_a_h.setImageResource(R.drawable.keys_a1);
                                    }
                                    else{
                                        btn_a_h.setImageResource(R.drawable.keyf_a1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[12]==0) {
                                jangjo[12]++;
                                btn_a_h.setImageResource(R.drawable.keys_a1);
                                sp.play(Scale.la1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[12]==1) {
                                jangjo[12]++;
                                btn_a_h.setImageResource(R.drawable.keyf_a1);
                                sp.play(Scale.sol1_5,1,1,1,0,1.0f);

                            }
                            else {
                                jangjo[12] = 0;
                                btn_a_h.setImageResource(R.drawable.key_a1);
                                sp.play(Scale.la1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        btn_b_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                    case MotionEvent.ACTION_DOWN:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_b_h.startAnimation(anim_Twist);
                            }
                            if (jangjo[13]==0){
                                sp.play(Scale.si1_0,1,1,1,0,1.0f);
                                btn_b_h.setImageResource(R.drawable.keyc_b1);
                            }
                            else if(jangjo[13]==1){
                                sp.play(Scale.si1_5,1,1,1,0,1.0f);
                                btn_b_h.setImageResource(R.drawable.keyc_c2);
                            }
                            else{
                                sp.play(Scale.la1_5,1,1,1,0,1.0f);
                                btn_b_h.setImageResource(R.drawable.keycf_b1);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[13]==0){
                                        btn_b_h.setImageResource(R.drawable.key_b1);
                                    }
                                    else if(jangjo[13]==1){
                                        btn_b_h.setImageResource(R.drawable.key_c2);
                                    }
                                    else{
                                        btn_b_h.setImageResource(R.drawable.keyf_b1);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[13]==0) {
                                jangjo[13]++;
                                btn_b_h.setImageResource(R.drawable.key_c2);
                                sp.play(Scale.si1_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[13]==1) {
                                jangjo[13]++;
                                btn_b_h.setImageResource(R.drawable.keyf_b1);
                                sp.play(Scale.la1_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[13] = 0;
                                btn_b_h.setImageResource(R.drawable.key_b1);
                                sp.play(Scale.si1_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        btn_c_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_c_hh.startAnimation(anim_Twist);
                            }
                            if (jangjo[14]==0){
                                sp.play(Scale.do2_0,1,1,1,0,1.0f);
                                btn_c_hh.setImageResource(R.drawable.keyc_c2);
                            }
                            else if(jangjo[14]==1){
                                sp.play(Scale.do2_5,1,1,1,0,1.0f);
                                btn_c_hh.setImageResource(R.drawable.keycs_c2);
                            }
                            else{
                                sp.play(Scale.si1_0,1,1,1,0,1.0f);
                                btn_c_hh.setImageResource(R.drawable.keycf_c2);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[14]==0){
                                        btn_c_hh.setImageResource(R.drawable.key_c2);
                                    }
                                    else if(jangjo[14]==1){
                                        btn_c_hh.setImageResource(R.drawable.keys_c2);
                                    }
                                    else{
                                        btn_c_hh.setImageResource(R.drawable.keyf_c2);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[14]==0) {
                                jangjo[14]++;
                                btn_c_hh.setImageResource(R.drawable.keys_c2);
                                sp.play(Scale.do2_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[14]==1) {
                                jangjo[14]++;
                                btn_c_hh.setImageResource(R.drawable.keyf_c2);
                                sp.play(Scale.si1_0,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[14] = 0;
                                btn_c_hh.setImageResource(R.drawable.key_c2);
                                sp.play(Scale.do2_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        btn_d_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_d_hh.startAnimation(anim_Twist);
                            }
                            if (jangjo[15]==0){
                                sp.play(Scale.re2_0,1,1,1,0,1.0f);
                                btn_d_hh.setImageResource(R.drawable.keyc_d2);
                            }
                            else if(jangjo[15]==1){
                                sp.play(Scale.re2_5,1,1,1,0,1.0f);
                                btn_d_hh.setImageResource(R.drawable.keycs_d2);
                            }
                            else{
                                sp.play(Scale.do2_5,1,1,1,0,1.0f);
                                btn_d_hh.setImageResource(R.drawable.keycf_d2);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[15]==0){
                                        btn_d_hh.setImageResource(R.drawable.key_d2);
                                    }
                                    else if(jangjo[15]==1){
                                        btn_d_hh.setImageResource(R.drawable.keys_d2);
                                    }
                                    else{
                                        btn_d_hh.setImageResource(R.drawable.keyf_d2);
                                    }
                                }
                            }, 500);
                        }
                        else{
                            if (jangjo[15]==0) {
                                jangjo[15]++;
                                btn_d_hh.setImageResource(R.drawable.keys_d2);
                                sp.play(Scale.re2_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[15]==1) {
                                jangjo[15]++;
                                btn_d_hh.setImageResource(R.drawable.keyf_d2);
                                sp.play(Scale.do2_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[15] = 0;
                                btn_d_hh.setImageResource(R.drawable.key_d2);
                                sp.play(Scale.re2_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        btn_e_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!changemod){
                            if(vive){
                                vibrator.vibrate(30);
                                btn_e_hh.startAnimation(anim_Twist);
                            }
                            if (jangjo[16]==0){
                                sp.play(Scale.mi2_0,1,1,1,0,1.0f);
                                btn_e_hh.setImageResource(R.drawable.keyc_e2);
                            }
                            else if(jangjo[16]==1){
                                sp.play(Scale.mi2_5,1,1,1,0,1.0f);
                                btn_e_hh.setImageResource(R.drawable.keys_e2);
                            }
                            else{
                                sp.play(Scale.re2_5,1,1,1,0,1.0f);
                                btn_e_hh.setImageResource(R.drawable.keycf_e2);
                            }

                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                //여기에 딜레이 후 시작할 작업들을 입력
                                    if (jangjo[16]==0){
                                        btn_e_hh.setImageResource(R.drawable.key_e2);
                                    }
                                    else if(jangjo[16]==1){
                                        btn_e_hh.setImageResource(R.drawable.keys_e2);
                                    }
                                    else{
                                        btn_e_hh.setImageResource(R.drawable.keyf_e2);
                                    }
                            }
                            }, 500);
                        }
                        else{
                            if (jangjo[16]==0) {
                                jangjo[16]++;
                                btn_e_hh.setImageResource(R.drawable.keys_e2);
                                sp.play(Scale.mi2_5,1,1,1,0,1.0f);
                            }
                            else if(jangjo[16]==1) {
                                jangjo[16]++;
                                btn_e_hh.setImageResource(R.drawable.keyf_e2);
                                sp.play(Scale.re2_5,1,1,1,0,1.0f);
                            }
                            else {
                                jangjo[16] = 0;
                                btn_e_hh.setImageResource(R.drawable.key_e2);
                                sp.play(Scale.mi2_0,1,1,1,0,1.0f);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        //진동모드 전환
        btn_vive.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    if (vive) {
                        btn_vive.setBackgroundResource(R.drawable.butoon_vi_off);
                        Toast.makeText(getApplicationContext(), "진동 OFF", Toast.LENGTH_SHORT).show();
                        vive = false;
                    }
                    else {
                        btn_vive.setBackgroundResource(R.drawable.butoon_vi_on);
                        Toast.makeText(getApplicationContext(),"진동 ON",Toast.LENGTH_SHORT).show();
                        vive = true;
                    }
                }
                return false;
            }
        });
        //튜닝모드 전환
        btn_switch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(play_mode.this, "튜닝모드", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(R.anim.anim_slide_down,R.anim.anim_slide_up);
                }
                return false;
            }
        });

        //샾 플랫 전환 버튼
        btn_sharp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (!changemod){
                        changemod = true;
                        Toast.makeText(play_mode.this, "반음 설정", Toast.LENGTH_SHORT).show();
                        btn_sharp.setImageResource(R.drawable.button_sharp_push);
                    }
                    else {
                        changemod = false;
                        Toast.makeText(play_mode.this, "연주 모드", Toast.LENGTH_SHORT).show();
                        btn_sharp.setImageResource(R.drawable.button_sharp);
                    }
                }
                return false;
            }
        });

        ArrayList<ImageButton> Keys = new ArrayList<>();
        for(ImageButton k: (new ImageButton[]{btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,
                btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh})){
            Keys.add(k);
        }

        ArrayList<ImageButton> Other = new ArrayList<>();
        for(ImageButton k: (new ImageButton[]{btn_sharp, btn_switch, btn_vive})){
            Other.add(k);
        }
//

        pianoView = new PianoView(CL, play_mode.this);
        pianoView.setKeys(Keys, true);
        pianoView.setKeys(Other, false);


    }


    // 잘못 뒤로가기 눌렀을 때
    long first_time;
    long second_time;

    @Override
    public void onBackPressed() {
        if(changemod){

            changemod = false;
            Toast.makeText(play_mode.this, "연주 모드", Toast.LENGTH_SHORT).show();
            btn_sharp.setImageResource(R.drawable.button_sharp);
        }
        else {
            Toast.makeText(play_mode.this, "튜닝 모드", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(R.anim.anim_slide_down, R.anim.anim_slide_up);
        }
    }
}
