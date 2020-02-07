package com.cookandroid.tuner;
import static com.cookandroid.tuner.FFTfunc.*;
import com.cookandroid.tuner.fftpack.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    int blockSize = 2048; // 정밀도(속도와 관련) 설정 (참고: 반드시 2의 배수)
    int sensitivity =  1; // 민감도 설정 (참고: 1~4까지가 적정)

    // 2. IO 연결 객체 (수정 가능, 삭제 불가)
    int channelConfiguration = AudioFormat.CHANNEL_IN_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    RealDoubleFFT transformer;
    RecordAudio recordTask;
    boolean started = true;

    // 3. 화면에 표시하기 위해, 따로 만든 변수들 (수정 가능, 삭제 가능)
    boolean DEBUG_MODE = false;

    static Button[] buttonArray = new Button[17];        // 칼림바 건반의 버튼객체를 담고 있는 배열
    static String[] ScaleArray;                          // C, D, E... 등의 음계를 담고 있는 String 배열
    static double CuHz= 260;                             // 현재 누르고 있는 건반의 음계치를 담고 있는 Int 변수
    int buttonId;                                        // 현재 누르고 있는 건반의 버튼 아이디를 담고 있는 Int 변수
    TextView D0, D1, D2, D3;                             // 순차적으로 들어오는 Hz를 표시하기 위한 TextView
    float before;
    /////////////////////////////////////////////



    Button btn_c_l,btn_d_l,btn_e_l,btn_f_l,btn_g_l,btn_a_l,btn_b_l,btn_c_h,btn_d_h,btn_e_h,btn_f_h,btn_g_h,btn_a_h,btn_b_h,btn_c_hh,btn_d_hh,btn_e_hh,btn_tune;
    TextView keyText,sharpText,flatText;
    ImageButton btn_switch,btn_help;
    Boolean sharpmode=false,flatmode=false;
    ImageView sound_label;
    //건반색 초기화함수
    public void setBtnWhite(){
        for(int i=0;i<17;i++){
            buttonArray[i].setBackgroundResource(R.drawable.key1);
            if (i==0||i==5||i==6||i==11||i==12){
                buttonArray[i].setBackgroundResource(R.drawable.key2);
            }
        }
    }
    //건반라벨 #추가함수
    public void setSharpText(){for(int i=0; i<17;i++){buttonArray[i].setText(ScaleArray[i%7]+"#");}}
    //건반라벨 b추가함수
    public void setFlatText(){for(int i=0; i<17;i++){buttonArray[i].setText(ScaleArray[i%7]+"♭");}}
    //건반라벨 원음변환함수
    public void setOriginText(){ for(int i=0; i<17;i++){buttonArray[i].setText(ScaleArray[i%7]);} }



    public void ROTATE (float inputHz){
        if(inputHz <233){return;}
        int Scaleidx = 0;
        int HZidx = 0;
        float[]SCALEHZ = {233,247, 260, 277, 293, 311, 329, 349, 370, 392, 415, 439, 466, 493, 523, 554, 587, 622, 659, 698, 739, 784, 830, 880, 932, 987, 1046, 1108, 1174, 1244, 1318, 1397};

        for(int i = 0 ; i< SCALEHZ.length; i++){ // 어느 구간에 있는지 확인함 (예를 들어 262는 [252~ 277] 사이이다.)
            if(inputHz < SCALEHZ[i]){
                Scaleidx = i;
                break;
            }
            else Scaleidx = SCALEHZ.length-1;
        }

        double TEMP = SCALEHZ[Scaleidx-1];  // TEMP 는 속해있는 구간의 맨 왼쪽 범위 (예를 들어 262의 TEMP는 252이다.)
        double INTERVAL = (SCALEHZ[Scaleidx] - SCALEHZ[Scaleidx-1])/240; // 그 구간을 30으로 나눈 간격 (예를 들어 252~277의 30등분은 0.83322 이다.
                                                                        // 30등분하는 이유는 그 간격이 1도와 같기 때문이다.

        Log.i("인터벌은", ""+INTERVAL);
        Log.i("템프와 인풋은", TEMP+"        "+inputHz);

        for(int i = 0; i < 240; i++){ // 위에서 구한 30등분한 간격을 얼마나 더해야 InputHZ와 같아지는지 확인함 (인터벌이 한번 더해질때마다 1도씩 늘어나는 것임)
                                     // 예를 들어 252값에 0.8333을 13번쯤 더해야 262와 유사해지므로, HZidx는 13이 된다.
            if(TEMP > inputHz){
                HZidx = i;
                break;
            }
            else{TEMP += INTERVAL;}
            if(i==239){  HZidx = 239;  } // 만약 범위 끝까지 안구해지면 29도를 부여(각도 끝 구간의 예외처리)
        }
        if(220<=HZidx&& HZidx<=239){
            HZidx = 240;
        }
        else if(0<= HZidx && HZidx <=20){
            HZidx = 0;
        }

        anim_rotate(-(-60+(Scaleidx-1)*30+(float)(HZidx*0.125)));
//        sound_label.setRotation(-((Scaleidx-1)*30+HZidx));  // 구간으로 나눈 Scale인덱스엔 30을 곱하고, TEMP는 1도를 부여
        Log.i("각도는", -60 + (Scaleidx-1)*30 + "  +  "+HZidx);
    }

    public void anim_rotate(float i){
        RotateAnimation ra = new RotateAnimation(before,i, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(200);
        ra.setFillAfter(true);
        sound_label.startAnimation(ra);
        before = i;
    }

    @SuppressLint("ClickableViewAccessibility")
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

        // 디버그 연결
        D0 = (TextView)findViewById(R.id.CurrentHzViewNow);
        D1 = (TextView)findViewById(R.id.CurrentHzView0);
        D2 = (TextView)findViewById(R.id.CurrentHzView1);
        D3 = (TextView)findViewById(R.id.CurrentHzView2);


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

        btn_help = (ImageButton)findViewById(R.id.btn_help);
        btn_tune = (Button)findViewById(R.id.btn_tune);
        sharpText = (TextView)findViewById(R.id.sharp);
        flatText = (TextView)findViewById(R.id.flat);
        btn_switch = (ImageButton)findViewById(R.id.switch_mode);
        sound_label = (ImageView)findViewById(R.id.sound_label);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
        //마이크 권한 확인
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},0);
            //없다면 마이크 권한 요청
        }
        buttonId = btn_c_l.getId();
        final ScaleSrc Scale = new ScaleSrc(MainActivity.this);
        final SoundPool sp = Scale.sp;

        btn_c_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        buttonId = v.getId();
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
                        btn_c_l.setBackgroundResource(R.drawable.key2_click);

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
                        buttonId = v.getId();
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
                        btn_d_l.setBackgroundResource(R.drawable.key1_click);

                        break;
                }
                return false;
            }
        });
        btn_e_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();
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

                        btn_e_l.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_f_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();
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

                        btn_f_l.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_g_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();
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

                        btn_g_l.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_a_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();
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

                        btn_a_l.setBackgroundResource(R.drawable.key2_click);
                        break;
                }
                return false;
            }
        });
        btn_b_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();
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

                        btn_b_l.setBackgroundResource(R.drawable.key2_click);
                        break;
                }
                return false;
            }
        });
        btn_c_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();
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
                        btn_c_h.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_d_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();

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
                        btn_d_h.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_e_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();

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
                        btn_e_h.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_f_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();

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
                        btn_f_h.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_g_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();

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
                        btn_g_h.setBackgroundResource(R.drawable.key2_click);
                        break;
                }
                return false;
            }
        });
        btn_a_h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:

                        buttonId = v.getId();

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
                        btn_a_h.setBackgroundResource(R.drawable.key2_click);
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

                        buttonId = v.getId();

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
                        btn_b_h.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_c_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:
                        buttonId = v.getId();

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
                        btn_c_hh.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_d_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:
                        buttonId = v.getId();

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
                        btn_d_hh.setBackgroundResource(R.drawable.key1_click);
                        break;
                }
                return false;
            }
        });
        btn_e_hh.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_DOWN:
                        buttonId = v.getId();

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
                        btn_e_hh.setBackgroundResource(R.drawable.key1_click);
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

        //연주모드 버튼 함수
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "연주모드", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),play_mode.class));
                overridePendingTransition(R.anim.anim_slide_down,R.anim.anim_slide_up);
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
            moveTaskToBack(true);
            finish();
            finishAffinity();
            ExitToast.cancel();
            android.os.Process.killProcess(android.os.Process.myPid());
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



        public void DebugHz(double cin,boolean DEBUG){
            if(!DEBUG)return;
            D3.setText(D2.getText());
            D2.setText(D1.getText());
            D1.setText(D0.getText());
            D0.setText(Double.toString(cin));
        }

        @Override
        protected void onProgressUpdate(double[]... toTransform) {
            float InputAudioHz = MaxInFFTArray(toTransform[0], sensitivity) * frequency / (2*blockSize);



            if(InputAudioHz>234) {
                DebugHz(InputAudioHz, DEBUG_MODE);
                double[] R = {InputAudioHz};
                if (Similar(R,10)) {
                    ROTATE((InputAudioHz));
                }else{ROTATE(InputAudioHz);}
            }

        }
    }
}
