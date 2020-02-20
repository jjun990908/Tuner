package com.cookandroid.tuner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.media.SoundPool;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

/*
이 클래스는 LinearLayout 으로 묶은 건반들의 TouchEvent 를 관리합니다.
*/

public class PianoView {

    ArrayList<Key> Keys = new ArrayList<>();
    ArrayList<Key> Other = new ArrayList<>();
    ImageButton temp;
    ConstraintF KeyList;
    Context context;
    ScaleSrc Scale;
    SoundPool sp;
    public PianoView(ConstraintF KeyList, Context context){
        this.KeyList = KeyList;
        this.context = context;
        Scale = new ScaleSrc(context);
        sp = Scale.sp;
    }


    public void setKeys(final ArrayList<ImageButton> Buttons, boolean isOther) {
        if(isOther){
            for(int Attr = 0; Attr < Buttons.size(); Attr++){
                Keys.add(new Key(Buttons.get(Attr)));
            }
            setTouch();
        } else{
            for(int Attr = 0; Attr < Buttons.size(); Attr++){
                Other.add(new Key(Buttons.get(Attr)));
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    public void setTouch(){
        KeyList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();


                if(action == MotionEvent.ACTION_DOWN){
                    float x1 = event.getX(0);
                    float y2 = event.getY(0);

                    Key o = KeyOther(x1,y2);
                    if (o != null) {
                        event.setAction(MotionEvent.ACTION_DOWN);
                        o.view.dispatchTouchEvent(event);
                        return true;
                    }

                }


                boolean isDownAction = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE;

                for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
                    float x = event.getX(touchIndex);
                    float y = event.getY(touchIndex);

                    Key k = KeyForCoordinate(x,y);
                    if (k != null) {

                        k.Down = action != MotionEvent.ACTION_UP;
                    }
                }

                for(Key k: Keys){

                    if(k.Down){
                        if(!k.isPlaying) {
                            event.setAction(MotionEvent.ACTION_DOWN);
                            k.view.dispatchTouchEvent(event);
                            k.isPlaying = true;
                        }
                        else{k.Down=false;}
                    }else{
                        ReleaseKey(k);
                        k.isPlaying = false;
                    }
                }

                return true;
            }

        });

    }


    private Key KeyForCoordinate(float x, float y){
        for(Key k : Keys){
            if(k.contains(x, y)){
                return k;
            }
        }
        return null;
    }

    private Key KeyOther(float x, float y){

        for(Key k : Other){
            if(k.contains(x, y)){
                return k;
            }
        }
        return null;
    }

    private void ReleaseKey(Key k){
        k.Down = false;
    }
}
