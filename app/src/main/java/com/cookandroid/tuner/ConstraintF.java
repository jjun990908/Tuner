package com.cookandroid.tuner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;

public class ConstraintF extends ConstraintLayout {
    ImageButton VIBE, TUNE, EXIT;


    public ConstraintF(Context context){
        super(context);
        TUNE = (ImageButton) findViewById(R.id.btn_sharp);

    }
    public ConstraintF(Context context, AttributeSet attr){

        super(context, attr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}