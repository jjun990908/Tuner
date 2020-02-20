package com.cookandroid.tuner;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;


class Key extends RectF {
    boolean Down = false;
    boolean isPlaying = false;
    final ImageButton view;

    //Constructor
    Key(ImageButton view){
        this.view = view;
        view.post(new Runnable() {
            @Override
            public void run() { setRect();}
        });
    }

    //Attr Setter (Left, Top, Right, Bottoms)
    void setRect(){
        this.set(view.getLeft(),view.getTop(), view.getRight(), view.getBottom());
    }

}