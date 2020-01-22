package com.cookandroid.tuner;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class ScaleSrc {
    int do0_0, re0_0, mi0_0, fa0_0, sol0_0, la0_0, si0_0;
    int do0_5, re0_5, mi0_5, fa0_5, sol0_5, la0_5, si0_5;
    int do1_0, re1_0, mi1_0, fa1_0, sol1_0, la1_0, si1_0;
    int do1_5, re1_5, mi1_5, fa1_5, sol1_5, la1_5, si1_5;
    int do2_0, re2_0, mi2_0;
    int do2_5, re2_5, mi2_5;
    SoundPool sp;

    ScaleSrc(Context context) {
        this.sp = new SoundPool(5,         // 최대 음악파일의 개수
                AudioManager.STREAM_MUSIC, // 스트림 타입
                5);        // 음질 - 기본값:0

        // 각각의 재생하고자하는 음악을 미리 준비한다

        //옥타브 lv 0
        this.do0_0 = sp.load(context,
                R.raw.kal_do0_0,
                1);
        this.do0_5 = sp.load(context,
                R.raw.kal_do0_5,
                1);
        this.re0_0 = sp.load(context,
                R.raw.kal_re0_0,
                1);
        this.re0_5 = sp.load(context,
                R.raw.kal_re0_5,
                1);
        this.mi0_0 = sp.load(context,
                R.raw.kal_mi0_0,
                1);
        this.mi0_5 = sp.load(context,
                R.raw.kal_fa0_0,
                1);
        this.sol0_0 = sp.load(context,
                R.raw.kal_sol0_0,
                1);
        this.sol0_5 = sp.load(context,
                R.raw.kal_sol0_5,
                1);
        this.fa0_0 = sp.load(context,
                R.raw.kal_fa0_0,
                1);
        this.fa0_5 = sp.load(context,
                R.raw.kal_fa0_5,
                1);
        this.la0_0 = sp.load(context,
                R.raw.kal_la0_0,
                1);
        this.la0_5 = sp.load(context,
                R.raw.kal_la0_5,
                1);
        this.si0_0 = sp.load(context,
                R.raw.kal_si0_0,
                1);
        this.si0_5 = sp.load(context,
                R.raw.kal_do1_0,
                1);


        // 옥타브 lv 1
        this.do1_0 = sp.load(context,
                R.raw.kal_do1_0,
                1);
        this.do1_5 = sp.load(context,
                R.raw.kal_do1_5,
                1);
        this.re1_0 = sp.load(context,
                R.raw.kal_re1_0,
                1);
        this.re1_5 = sp.load(context,
                R.raw.kal_re1_5,
                1);
        this.mi1_0 = sp.load(context,
                R.raw.kal_mi1_0,
                1);
        this.mi1_5 = sp.load(context,
                R.raw.kal_fa1_0,
                1);
        this.fa1_0 = sp.load(context,
                R.raw.kal_fa1_0,
                1);
        this.fa1_5 = sp.load(context,
                R.raw.kal_fa1_5,
                1);
        this.sol1_0 = sp.load(context,
                R.raw.kal_sol1_0,
                1);
        this.sol1_5 = sp.load(context,
                R.raw.kal_sol1_5,
                1);
        this.la1_0 = sp.load(context,
                R.raw.kal_la1_0,
                1);
        this.la1_5 = sp.load(context,
                R.raw.kal_la1_5,
                1);
        this.si1_0 = sp.load(context,
                R.raw.kal_si1_0,
                1);
        this.si1_5 = sp.load(context,
                R.raw.kal_do2_0,
                1);

        // 옥타브 lv 2
        this.do2_0 = sp.load(context,
                R.raw.kal_do2_0,
                1);
        this.do2_5 = sp.load(context,
                R.raw.kal_do2_5,
                1);
        this.re2_0 = sp.load(context,
                R.raw.kal_re2_0,
                1);
        this.re2_5 = sp.load(context,
                R.raw.kal_re2_5,
                1);
        this.mi2_0 = sp.load(context,
                R.raw.kal_mi2_0,
                1);
        this.mi2_5 = sp.load(context,
                R.raw.kal_mi2_5,
                1);
    }
}
