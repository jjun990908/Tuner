package com.cookandroid.tuner;
import static com.cookandroid.tuner.FFTfunc.*;
import com.cookandroid.tuner.fftpack.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.media.*;
import android.os.*;
import android.util.*;



public class MainActivity extends AppCompatActivity{

    ////////// FFT initialize ////////////////////
    // 1. 성능 설정 변수 (수정 가능, 삭제 불가)
    int frequency = 4400; // 가청 주파수 설정 (참고: 가청 주파수는 frequency 의 절반)
    int blockSize = 1024; // 정밀도(속도와 관련) 설정 (참고: 반드시 2의 배수)
    int sensitivity =  2; // 민감도 설정 (참고: 1~4까지가 적정)

    // 2. IO 연결 객체 (수정 가능, 삭제 불가)
    int channelConfiguration = AudioFormat.CHANNEL_IN_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    RealDoubleFFT transformer;
    RecordAudio recordTask;

    // 3. 화면에 표시하기 위해, 따로 만든 변수들 (수정 가능, 삭제 가능)
    static float avrg;
    static float[] buffer = new float[5];
    static {for(int i = 0; i < 5; i ++){buffer[i] = 0;}}
    static Button[] buttonArray = new Button[7];
    static String[] ScaleArray;
    static double CuHz= 260;
    int buttonId,color_state,judge_sound;
    boolean started = true, flag = true;
    /////////////////////////////////////////////


    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,btn_help,btn_tune;
    View centerView,centerRView,centerLView,centerRRView,centerLLView;
    TextView keyText,sharpText,flatText;
    Button btn_switch;
    Boolean sharpmode=false,flatmode=false;
    //건반색 초기화함수
    public void setBtnWhite(){ for(int i=0;i<17;i++){ buttonArray[i].setBackgroundResource(R.drawable.btn_key_shape); } }
    //건반라벨 #추가함수
    public void setSharpText(){for(int i=0; i<17;i++){buttonArray[i].setText(ScaleArray[i%7]+"#");}}
    //건반라벨 b추가함수
    public void setFlatText(){for(int i=0; i<17;i++){buttonArray[i].setText(ScaleArray[i%7]+"♭");}}
    //건반라벨 원음변환함수
    public void setOriginText(){ for(int i=0; i<17;i++){buttonArray[i].setText(ScaleArray[i%7]);} }
    //튜닝확인 뷰 색 변환 함수
    public void setOriginColor(){
        centerLLView.setBackgroundResource(R.drawable.smallbtn_shape);
        centerLView.setBackgroundResource(R.drawable.smallbtn_shape);
        centerView.setBackgroundResource(R.drawable.smallbtn_shape);
        centerRView.setBackgroundResource(R.drawable.smallbtn_shape);
        centerRRView.setBackgroundResource(R.drawable.smallbtn_shape);
    }
    //튜닝확인 뷰 색 변환 함수
    public void setColor(int color_state){
        setOriginColor();
        if (color_state == 1){
            centerLLView.setBackgroundResource(R.drawable.btn_bottom_red);
        }
        else if (color_state == 2){
            centerLView.setBackgroundResource(R.drawable.btn_bottom_red);
        }
        else if (color_state == 3){
            centerView.setBackgroundResource(R.drawable.btn_bottom_green);
        }
        else if (color_state == 4){
            centerRView.setBackgroundResource(R.drawable.btn_bottom_red);
        }
        else if (color_state == 5) {
            centerRRView.setBackgroundResource(R.drawable.btn_bottom_red);
        }
    }

    //각 음계에 대한 튜닝 범위 설정
    public void judge_sound(int judge_sound){
        switch (judge_sound){
            case 1:
                if (avrg<251) setColor(1);
                else if(251<=avrg && avrg<256) setColor(2);
                else if(256<=avrg && avrg<266) setColor(3);
                else if(266<=avrg && avrg<271) setColor(4);
                else if(271<=avrg) setColor(5);
                break;
            case 2:
                if (avrg<283) setColor(1);
                else if(283<=avrg && avrg<288) setColor(2);
                else if(288<=avrg && avrg<298) setColor(3);
                else if(298<=avrg && avrg<303) setColor(4);
                else if(303<=avrg) setColor(5);
                break;
            case 3:
                if (avrg<319) setColor(1);
                else if(319<=avrg && avrg<324) setColor(2);
                else if(324<=avrg && avrg<334) setColor(3);
                else if(334<=avrg && avrg<339) setColor(4);
                else if(338<=avrg) setColor(5);
                break;
            case 4:
                if (avrg<339) setColor(1);
                else if(339<=avrg && avrg<344) setColor(2);
                else if(344<=avrg && avrg<354) setColor(3);
                else if(354<=avrg && avrg<359) setColor(4);
                else if(359<=avrg) setColor(5);
                break;
            case 5:
                if (avrg<382) setColor(1);
                else if(382<=avrg && avrg<387) setColor(2);
                else if(387<=avrg && avrg<397) setColor(3);
                else if(397<=avrg && avrg<402) setColor(4);
                else if(402<=avrg) setColor(5);
                break;
            case 6:
                if (avrg<430) setColor(1);
                else if(430<=avrg && avrg<435) setColor(2);
                else if(435<=avrg && avrg<445) setColor(3);
                else if(445<=avrg && avrg<450) setColor(4);
                else if(450<=avrg) setColor(5);
                break;
            case 7:
                if (avrg<483) setColor(1);
                else if(483<=avrg && avrg<488) setColor(2);
                else if(488<=avrg && avrg<498) setColor(3);
                else if(498<=avrg && avrg<503) setColor(4);
                else if(503<=avrg) setColor(5);
                break;
            case 8:
                if (avrg<513) setColor(1);
                else if(513<=avrg && avrg<518) setColor(2);
                else if(518<=avrg && avrg<528) setColor(3);
                else if(528<=avrg && avrg<533) setColor(4);
                else if(533<=avrg) setColor(5);
                break;
            case 9:
                if (avrg<577) setColor(1);
                else if(577<=avrg && avrg<582) setColor(2);
                else if(582<=avrg && avrg<592) setColor(3);
                else if(592<=avrg && avrg<597) setColor(4);
                else if(597<=avrg) setColor(5);
                break;
            case 10:
                if (avrg<649) setColor(1);
                else if(649<=avrg && avrg<654) setColor(2);
                else if(654<=avrg && avrg<664) setColor(3);
                else if(664<=avrg && avrg<669) setColor(4);
                else if(669<=avrg) setColor(5);
                break;
            case 11:
                if (avrg<688) setColor(1);
                else if(688<=avrg && avrg<693) setColor(2);
                else if(693<=avrg && avrg<703) setColor(3);
                else if(703<=avrg && avrg<708) setColor(4);
                else if(708<=avrg) setColor(5);
                break;
            case 12:
                if (avrg<774) setColor(1);
                else if(774<=avrg && avrg<779) setColor(2);
                else if(779<=avrg && avrg<789) setColor(3);
                else if(789<=avrg && avrg<794) setColor(4);
                else if(794<=avrg) setColor(5);
                break;
            case 13:
                if (avrg<870) setColor(1);
                else if(870<=avrg && avrg<875) setColor(2);
                else if(875<=avrg && avrg<885) setColor(3);
                else if(885<=avrg && avrg<890) setColor(4);
                else if(890<=avrg) setColor(5);
                break;
            case 14:
                if (avrg<977) setColor(1);
                else if(977<=avrg && avrg<982) setColor(2);
                else if(982<=avrg && avrg<992) setColor(3);
                else if(992<=avrg && avrg<997) setColor(4);
                else if(997<=avrg) setColor(5);
                break;
            case 15:
                if (avrg<1036) setColor(1);
                else if(1036<=avrg && avrg<1041) setColor(2);
                else if(1041<=avrg && avrg<1051) setColor(3);
                else if(1051<=avrg && avrg<1056) setColor(4);
                else if(1056<=avrg) setColor(5);
                break;
            case 16:
                if (avrg<1164) setColor(1);
                else if(1164<=avrg && avrg<1169) setColor(2);
                else if(1169<=avrg && avrg<1179) setColor(3);
                else if(1179<=avrg && avrg<1184) setColor(4);
                else if(1184<=avrg) setColor(5);
                break;
            case 18:
                if (avrg<267) setColor(1);
                else if(267<=avrg && avrg<272) setColor(2);
                else if(272<=avrg && avrg<282) setColor(3);
                else if(282<=avrg && avrg<287) setColor(4);
                else if(287<=avrg) setColor(5);
                break;
            case 19:
                if (avrg<301) setColor(1);
                else if(301<=avrg && avrg<306) setColor(2);
                else if(306<=avrg && avrg<316) setColor(3);
                else if(316<=avrg && avrg<321) setColor(4);
                else if(321<=avrg) setColor(5);
                break;
            case 20:
                if (avrg<360) setColor(1);
                else if(360<=avrg && avrg<365) setColor(2);
                else if(375<=avrg && avrg<385) setColor(3);
                else if(885<=avrg && avrg<890) setColor(4);
                else if(890<=avrg) setColor(5);
                break;
            case 21:
                if (avrg<405) setColor(1);
                else if(405<=avrg && avrg<410) setColor(2);
                else if(410<=avrg && avrg<420) setColor(3);
                else if(420<=avrg && avrg<425) setColor(4);
                else if(425<=avrg) setColor(5);
                break;
            case 22:
                if (avrg<456) setColor(1);
                else if(456<=avrg && avrg<461) setColor(2);
                else if(461<=avrg && avrg<471) setColor(3);
                else if(471<=avrg && avrg<476) setColor(4);
                else if(476<=avrg) setColor(5);
                break;
            case 23:
                if (avrg<544) setColor(1);
                else if(544<=avrg && avrg<549) setColor(2);
                else if(549<=avrg && avrg<559) setColor(3);
                else if(559<=avrg && avrg<564) setColor(4);
                else if(564<=avrg) setColor(5);
                break;
            case 24:
                if (avrg<612) setColor(1);
                else if(612<=avrg && avrg<617) setColor(2);
                else if(617<=avrg && avrg<627) setColor(3);
                else if(627<=avrg && avrg<632) setColor(4);
                else if(632<=avrg) setColor(5);
                break;
            case 25:
                if (avrg<729) setColor(1);
                else if(729<=avrg && avrg<734) setColor(2);
                else if(734<=avrg && avrg<744) setColor(3);
                else if(744<=avrg && avrg<749) setColor(4);
                else if(749<=avrg) setColor(5);
                break;
            case 26:
                if (avrg<820) setColor(1);
                else if(820<=avrg && avrg<825) setColor(2);
                else if(825<=avrg && avrg<835) setColor(3);
                else if(835<=avrg && avrg<840) setColor(4);
                else if(840<=avrg) setColor(5);
                break;
            case 27:
                if (avrg<922) setColor(1);
                else if(922<=avrg && avrg<927) setColor(2);
                else if(927<=avrg && avrg<937) setColor(3);
                else if(937<=avrg && avrg<942) setColor(4);
                else if(942<=avrg) setColor(5);
                break;
            case 28:
                if (avrg<1098) setColor(1);
                else if(1098<=avrg && avrg<1103) setColor(2);
                else if(1103<=avrg && avrg<1113) setColor(3);
                else if(1113<=avrg && avrg<1118) setColor(4);
                else if(1118<=avrg) setColor(5);
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///FFT initialize ///
        transformer = new RealDoubleFFT(blockSize);
        recordTask = new RecordAudio();
        recordTask.execute();
        ScaleArray = new String[]{"C", "D", "E", "F", "G", "A", "B"};
        /////////////////////



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

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
        buttonArray = new Button[]{btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh};

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
        btn_switch = (Button)findViewById(R.id.switch_mode);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
        //마이크 권한 확인
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
            //없다면 마이크 권한 요청
        }
        buttonId = btn_c_l.getId();
        final ScaleSrc Scale = new ScaleSrc(MainActivity.this);
        final SoundPool sp = Scale.sp;

        btn_c_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.do0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("C");
                btn_c_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_d_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.re0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.re0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("D");
                btn_d_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_e_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.re0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("E");
                btn_e_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_f_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.mi0_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.fa0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("F");
                btn_f_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_g_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.fa0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.sol0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("G");
                btn_g_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_a_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.la0_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.sol0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.la0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("A");
                btn_a_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_b_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.do1_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.la0_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.si0_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("B");
                btn_b_l.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_c_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();
                if (sharpmode){
                    sp.play(Scale.do1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.si0_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.do1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("C");
                btn_c_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_d_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.re1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.re1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("D");
                btn_d_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_e_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.re1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("E");
                btn_e_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_f_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.mi1_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.fa1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("F");
                btn_f_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_g_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.fa1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.sol1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("G");
                btn_g_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_a_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.la1_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.sol1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.la1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("A");
                btn_a_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_b_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.do2_0,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.la1_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.si1_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("B");
                btn_b_h.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_c_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.do2_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.si1_0,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.do2_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("C");
                btn_c_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_d_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.re2_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.do2_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.re2_0,1,1,1,0,1.0f);
                }

                setBtnWhite();
                keyText.setText("D");
                btn_d_hh.setBackgroundResource(R.drawable.btn_key_select_shape);
            }
        });
        btn_e_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonId = view.getId();

                if (sharpmode){
                    sp.play(Scale.mi2_5,1,1,1,0,1.0f);
                }
                else if (flatmode){
                    sp.play(Scale.re2_5,1,1,1,0,1.0f);
                }
                else{
                    sp.play(Scale.mi2_0,1,1,1,0,1.0f);
                }

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

        //연주모드 버튼 함수
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "연주모드", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),play_mode.class));
                overridePendingTransition(R.anim.anim_slide_down,R.anim.anim_slide_up);
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




    public class RecordAudio extends AsyncTask<Void, double[], Void> {

        @Override
        protected Void doInBackground(Void... params) {
            System.out.println("플래그 2:  백그라운드 쓰레드 실행");

            try {
                int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
                System.out.println("플래그 3: 버퍼 모듈 초기화");
                AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency,
                        channelConfiguration, audioEncoding, bufferSize);
                short[] buffer = new short[blockSize];
                double[] toTransform = new double[blockSize];

                audioRecord.startRecording();
                while (started) {
                    int bufferReadResult = audioRecord.read(buffer, 0, blockSize); //blockSize = 256
                    //Log.i("bufferReadResult", Integer.toString(bufferReadResult));

                    for (int i = 0; i < blockSize && i < bufferReadResult; i++) {
                        toTransform[i] = (double) buffer[i] / Short.MAX_VALUE;
                    }
                    transformer.ft(toTransform);
                    publishProgress(toTransform);
                }
                audioRecord.stop();
            } catch (Throwable t) {
                Log.e("오디오레코딩 실패", "PATAL ERROR;");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(double[]... toTransform) {
            setCuHz(buttonArray, buttonId, CuHz, flatmode, sharpmode);
            float InputAudioHz = -1;
            InputAudioHz = MaxInFFTArray(toTransform[0], sensitivity) * frequency / (2*blockSize);
            Log.i("Button ID", "  "+buttonId);



            if (CuHz * (0.9) <= InputAudioHz && InputAudioHz <= CuHz * 1.1) {
                for (int i = 0; i < 4; i++) {
                    float tmparray = buffer[i + 1];
                    buffer[i] = tmparray;
                }
                buffer[4] = InputAudioHz;
                avrg = NoiseDetect(buffer);

                Log.i("current Hz", Float.toString(InputAudioHz));
                Log.i("avrg Hz", Float.toString(avrg));

                for (int i=1;i<5;i++){
                    if (buttonId == buttonArray[i-1].getId()){
                        judge_sound(i);
                    }
                }
            }
        }
    }
}
