package com.cookandroid.tuner;
import com.cookandroid.tuner.fftpack.*;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import android.Manifest;
import android.app.*;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.media.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity{
    float fixed = -1;
    static float buffer[] = new float[5];
    static {for(int i = 0; i < 5; i ++){buffer[i] = 0;}}
    static Button buttonArray[] = new Button[7];
    static String ScaleArray[];
    int frequency = 4400;
    int channelConfiguration = AudioFormat.CHANNEL_IN_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    double CuHz= 260;
    int buttonId;

    RealDoubleFFT transformer;

    /////////////////////////////////////////////////////////////
    ////블록사이즈는 성능에 영향을 존나 크게 미치니까 가급적 1024 이하로 설정////
    /////////////////////////////////////////////////////////////
    int blockSize = 1024;/////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    Button startStopButton;
    boolean started = true;


    RecordAudio recordTask;
    ImageView imageView;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    TextView textView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        transformer = new RealDoubleFFT(blockSize);

        recordTask = new RecordAudio();
        recordTask.execute();

        ScaleArray = new String[]{"C", "D", "E", "F", "G", "A", "B"};
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
            System.out.println("플래그 2: 다른 쓰레드에서 백그라운드 실행");

            try { // 예외처리를 무식하게 try에 모두 다 때려박음 : 나중에 몇 개의 부분만 선택해서 try에 넣도록 수정 필요

                int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
                System.out.println("플래그 3: 오디오 사이즈에 할당된 버퍼? 같은게 생김");
                AudioRecord audioRecord = new AudioRecord( MediaRecorder.AudioSource.MIC, frequency,
                        channelConfiguration, audioEncoding, bufferSize);
                System.out.println("플래그 -1: 오디오 레코딩에 잘못된 권한, 치명적 오류");
                short[] buffer = new short[blockSize];
                double[] toTransform = new double[blockSize];

                audioRecord.startRecording();
                Log.e("here you are", "this");
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
                Log.e("오디오레코딩 실패함", "망한듯;;;");
                System.out.println("플래그 -2: 치명적 오류 발생, 로깅파일 분석하셈");
            }
            return null;
        }

        private float maxium(double[] arrays){
            int sound;
            double maxius;
            maxius = arrays[0];
            float idx = 0;
            for(int i = 0; i < arrays.length; i++)
                if (maxius < arrays[i]){
                    maxius = arrays[i];
                    idx = i;
                }
            if (maxius >3) {
                return idx * 4400/2/1024;
            }
            else{
                return -1; }
        }

        private float sumavrg(float [] arrays){
            float max, min, sum;
            max=sum=0;
            min = 9999;
            int idxM, idxm;
            idxM=2;
            idxm=1;
            for(int i = 0; i< 5; i++){
                if (arrays[i] > max+1){ max = arrays[i]; idxM = i; }
                if (arrays[i] < min-1){ min = arrays[i]; idxm = i;}

            }
            if (idxM == idxm){
                for(int i = 0; i< 5; i++){
                    if(i != idxM && i!= idxm){ sum += arrays[i];}

                }
                return sum/4;
            }

            else{
                for(int i = 0; i< 5; i++){
                    if(i != idxM && i!= idxm){ sum += arrays[i];}

                }
                return sum/3;
            }
        }

        private void setHz() {
                Button btns[] = {btn_c_l, btn_d_l, btn_e_l, btn_f_l, btn_g_l, btn_a_l, btn_b_l, btn_c_h, btn_d_h, btn_e_h, btn_f_h, btn_g_h, btn_a_h, btn_b_h, btn_c_hh, btn_d_hh, btn_e_hh};
                String arrays[] = {"C", "D", "E", "F", "G", "A", "B"};
            if (sharpmode == false && flatmode == false) {
                double btnHz[] = {261.62, 293.66, 329.62, 349.22, 392, 440, 493.88, 523.25, 587.32, 659.25, 698.25, 784, 877.99, 987.76, 1046.5, 1174.6, 1318.51};
                for (int i = 0; i < btns.length; i++) {
                    if (btns[i].getId() == buttonId && CuHz != btnHz[i]) {
                        CuHz = btnHz[i];
                        Log.i("현재음", arrays[i % 7] + "     " + Boolean.toString(sharpmode) + Boolean.toString(flatmode));
                    }
                }
            }
            else if (sharpmode == true && flatmode == false){
                double btnHz[] = {277.18, 311.12, 349.22, 370, 415.30, 466.16, 523.25, 554.36, 619.4, 698.5, 739, 830.60, 932.32, 1046.5, 1108.7, 1244.50, 1396.91};
                for (int i = 0; i < btns.length; i++) {
                    if (btns[i].getId() == buttonId && CuHz != btnHz[i]) {
                        CuHz = btnHz[i];
                        Log.i("현재음", arrays[i % 7] + "     " + Boolean.toString(sharpmode) + Boolean.toString(flatmode));
                    }
                }
            }
            else{
                double btnHz[] = {0, 277.18, 311.12, 329.62, 370, 415.30, 466.16, 493.88, 554.36, 619.4, 659.25, 739, 830.60, 932.32, 987.76, 1108.7, 1244.50};
                for (int i = 0; i < btns.length; i++) {
                    if (btns[i].getId() == buttonId && CuHz != btnHz[i]) {
                        CuHz = btnHz[i];
                        Log.i("현재음", arrays[i % 7] + "     " + Boolean.toString(sharpmode) + Boolean.toString(flatmode));
                    }
                }
            }
        }

        @Override
        protected void onProgressUpdate(double[]... toTransform) {
            setHz();
            float fixedtemp = -1;
            float avrg = 0;
            fixedtemp = maxium(toTransform[0]);
            if (CuHz * (0.8) <= fixedtemp && fixedtemp <= CuHz * 1.2) {
                for (int i = 0; i < 4; i++) {
                    float tmparray = buffer[i + 1];
                    buffer[i] = tmparray;
                }
                buffer[4] = fixedtemp;
                avrg = sumavrg(buffer);


                Log.i("current Hz", Float.toString(fixedtemp));
                Log.i("avrg Hz", Float.toString(avrg));

                fixed = avrg;

            }
        }
    }

}
