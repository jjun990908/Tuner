package com.cookandroid.tuner;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class ScaleSrc {
    static int do0_0, re0_0, mi0_0, fa0_0, sol0_0, la0_0, si0_0;
    static int do0_5, re0_5, mi0_5, fa0_5, sol0_5, la0_5, si0_5;
    static int do1_0, re1_0, mi1_0, fa1_0, sol1_0, la1_0, si1_0;
    static int do1_5, re1_5, mi1_5, fa1_5, sol1_5, la1_5, si1_5;
    static int do2_0, re2_0, mi2_0;
    static int do2_5, re2_5, mi2_5;
    static int si00_0;
    static SoundPool sp;

    static double[] scaleArrayNormal;
    static double[] scaleArrayFlat;
    static double[] scaleArraySharp;
    static String[] scaleArrayString;
    static {
        scaleArrayNormal = new double[]{261.62, 293.66, 329.62, 349.22, 392, 440, 493.88, 523.25, 587.32, 659.25, 698.25, 784, 877.99, 987.76, 1046.5, 1174.6, 1318.51};
        scaleArraySharp = new double[]{277.18, 311.12, 349.22, 370, 415.30, 466.16, 523.25, 554.36, 619.4, 698.5, 739, 830.60, 932.32, 1046.5, 1108.7, 1244.50, 1396.91};
        scaleArrayFlat = new double[]{0, 277.18, 311.12, 329.62, 370, 415.30, 466.16, 493.88, 554.36, 619.4, 659.25, 739, 830.60, 932.32, 987.76, 1108.7, 1244.50};
        scaleArrayString = new String[]{"C", "D", "E", "F", "G", "A", "B"};
    }

    private static void ConstPublisher(Context context){
        sp = new SoundPool(5,         // 최대 음악파일의 개수
                AudioManager.STREAM_MUSIC, // 스트림 타입
                0);        // 음질 - 기본값:0

        // 각각의 재생하고자하는 음악을 미리 준비한다

        //옥타브 lv -1
        si00_0 = sp.load(context,
                R.raw.kal_si00_0,
                1);

        //옥타브 lv 0
        do0_0 = sp.load(context,
                R.raw.kal_do0_0,
                1);
        do0_5 = sp.load(context,
                R.raw.kal_do0_5,
                1);
        re0_0 = sp.load(context,
                R.raw.kal_re0_0,
                1);
        re0_5 = sp.load(context,
                R.raw.kal_re0_5,
                1);
        mi0_0 = sp.load(context,
                R.raw.kal_mi0_0,
                1);
        mi0_5 = sp.load(context,
                R.raw.kal_fa0_0,
                1);
        sol0_0 = sp.load(context,
                R.raw.kal_sol0_0,
                1);
        sol0_5 = sp.load(context,
                R.raw.kal_sol0_5,
                1);
        fa0_0 = sp.load(context,
                R.raw.kal_fa0_0,
                1);
        fa0_5 = sp.load(context,
                R.raw.kal_fa0_5,
                1);
        la0_0 = sp.load(context,
                R.raw.kal_la0_0,
                1);
        la0_5 = sp.load(context,
                R.raw.kal_la0_5,
                1);
        si0_0 = sp.load(context,
                R.raw.kal_si0_0,
                1);
        si0_5 = sp.load(context,
                R.raw.kal_do1_0,
                1);


        // 옥타브 lv 1
        do1_0 = sp.load(context,
                R.raw.kal_do1_0,
                1);
        do1_5 = sp.load(context,
                R.raw.kal_do1_5,
                1);
        re1_0 = sp.load(context,
                R.raw.kal_re1_0,
                1);
        re1_5 = sp.load(context,
                R.raw.kal_re1_5,
                1);
        mi1_0 = sp.load(context,
                R.raw.kal_mi1_0,
                1);
        mi1_5 = sp.load(context,
                R.raw.kal_fa1_0,
                1);
        fa1_0 = sp.load(context,
                R.raw.kal_fa1_0,
                1);
        fa1_5 = sp.load(context,
                R.raw.kal_fa1_5,
                1);
        sol1_0 = sp.load(context,
                R.raw.kal_sol1_0,
                1);
        sol1_5 = sp.load(context,
                R.raw.kal_sol1_5,
                1);
        la1_0 = sp.load(context,
                R.raw.kal_la1_0,
                1);
        la1_5 = sp.load(context,
                R.raw.kal_la1_5,
                1);
        si1_0 = sp.load(context,
                R.raw.kal_si1_0,
                1);
        si1_5 = sp.load(context,
                R.raw.kal_do2_0,
                1);

        // 옥타브 lv 2
        do2_0 = sp.load(context,
                R.raw.kal_do2_0,
                1);
        do2_5 = sp.load(context,
                R.raw.kal_do2_5,
                1);
        re2_0 = sp.load(context,
                R.raw.kal_re2_0,
                1);
        re2_5 = sp.load(context,
                R.raw.kal_re2_5,
                1);
        mi2_0 = sp.load(context,
                R.raw.kal_mi2_0,
                1);
        mi2_5 = sp.load(context,
                R.raw.kal_mi2_5,
                1);

    }


    ScaleSrc(Context context) {
        ConstPublisher(context);
    }
}
