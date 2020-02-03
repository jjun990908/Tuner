package com.cookandroid.tuner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class play_mode extends AppCompatActivity {

    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,btn_help,btn_tune;
    Button btn_switch;
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
//    }
    public void setSharpText(int n){
        switch (n) {
            case 7:
                btn_b_l.setText("B#");
                btn_b_h.setText("B#");
            case 6:
                btn_e_h.setText("E#");
                btn_e_hh.setText("E#");
                btn_e_l.setText("E#");
            case 5:
                btn_a_h.setText("A#");
                btn_a_l.setText("A#");
            case 4:
                btn_d_h.setText("D#");
                btn_d_hh.setText("D#");
                btn_d_l.setText("D#");
            case 3:
                btn_g_h.setText("G#");
                btn_g_l.setText("G#");
            case 2:
                btn_c_h.setText("C#");
                btn_c_hh.setText("C#");
                btn_c_l.setText("C#");
            case 1:
                btn_f_h.setText("F#");
                btn_f_l.setText("F#");
        }
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
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.play_mode);

        final Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

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
                        break;
                }
                return false;
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
        btn_tune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //팝업메뉴 객체 생성
                PopupMenu popup = new PopupMenu(getApplicationContext(),view);
                popup.getMenuInflater().inflate(R.menu.popup_playmode,popup.getMenu());
                //팝업메뉴 클릭시 이벤트
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.sharp_1:
                                Toast.makeText(getApplicationContext(),"파샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(1);
                                jangjo= 1;
                                break;
                            case R.id.sharp_2:
                                Toast.makeText(getApplicationContext(),"도샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(2);
                                jangjo= 2;
                                break;
                            case R.id.sharp_3:
                                Toast.makeText(getApplicationContext(),"솔샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(3);
                                jangjo= 3;
                                break;
                            case R.id.sharp_4:
                                Toast.makeText(getApplicationContext(),"레샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(4);
                                jangjo= 4;
                                break;
                            case R.id.sharp_5:
                                Toast.makeText(getApplicationContext(),"라샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(5);
                                jangjo= 5;
                                break;
                            case R.id.sharp_6:
                                Toast.makeText(getApplicationContext(),"미샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(6);
                                jangjo= 6;
                                break;
                            case R.id.sharp_7:
                                Toast.makeText(getApplicationContext(),"시샵",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                setSharpText(7);
                                jangjo= 7;
                                break;
                            case R.id.sharp_0:
                                Toast.makeText(getApplicationContext(),"원음",Toast.LENGTH_SHORT).show();
                                setOriginText();
                                jangjo= 0;

                        }
                        return true;
                    }
                });
                popup.show();
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
