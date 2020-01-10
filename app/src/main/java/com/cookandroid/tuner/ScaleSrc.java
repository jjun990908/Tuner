package com.cookandroid.tuner;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class ScaleSrc {
    int do0_0, re0_0, mi0_0, fa0_0, sol0_0, la0_0, si0_0, do1_0;
    SoundPool sp;

    ScaleSrc(Context context) {
        this.sp = new SoundPool(5,         // 최대 음악파일의 개수
                AudioManager.STREAM_MUSIC, // 스트림 타입
                5);        // 음질 - 기본값:0

        // 각각의 재생하고자하는 음악을 미리 준비한다

        //옥타브 lv 1
        this.do0_0 = sp.load(context,
                R.raw.piano_do0_0,
                1);
        this.re0_0 = sp.load(context,
                R.raw.piano_re0_0,
                1);
        this.mi0_0 = sp.load(context,
                R.raw.piano_mi0_0,
                1);
        this.sol0_0 = sp.load(context,
                R.raw.piano_sol0_0,
                1);
        this.fa0_0 = sp.load(context,
                R.raw.piano_fa0_0,
                1);
        this.la0_0 = sp.load(context,
                R.raw.piano_si0_0,
                1);
        this.si0_0 = sp.load(context,
                R.raw.piano_la0_0,
                1);


        // 옥타브 lv 2
        this.do1_0 = sp.load(context,
                R.raw.piano_do1_0,
                1);
    }
}
