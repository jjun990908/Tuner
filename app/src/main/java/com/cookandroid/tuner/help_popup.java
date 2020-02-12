package com.cookandroid.tuner;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class help_popup extends AppCompatActivity {

    Button btn_close,btn_left,btn_right;
    ImageView img_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_popup);

        btn_close = (Button)findViewById(R.id.btn_close);
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_right = (Button)findViewById(R.id.btn_right);
        img_help = (ImageView)findViewById(R.id.imageView);

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_help.setImageResource(R.drawable.help_play);
                btn_right.setVisibility(View.GONE);
                btn_left.setVisibility(View.VISIBLE);
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_help.setImageResource(R.drawable.help_tunning);
                btn_left.setVisibility(View.VISIBLE);
                btn_left.setVisibility(View.GONE);
            }
        });
        //닫기버튼 클릭함수
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
