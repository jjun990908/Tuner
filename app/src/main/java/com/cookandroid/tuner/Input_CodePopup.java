package com.cookandroid.tuner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Input_CodePopup extends AppCompatActivity {
    TextView txt_codeview, text_Explanation2;
    Button btn_close, btn_confirmcode, btn_confirm, btn_buycode;
    boolean codenumbercheck = false, codeenglishcheck = false;
    Context mContext;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_code_popup);
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText code = (EditText) findViewById(R.id.editText_inputcode);
                text_Explanation2 = findViewById(R.id.text_Explanation2);
                String codetext = code.getText().toString();
                if (codetext.length() == 0) {         //공백일 때 처리할 내용
                    text_Explanation2.setText("코드를 입력해 주세요");
                    codenumbercheck = false;
                    codeenglishcheck = false;
                } else if (codetext.length() != 7) {
                    text_Explanation2.setText("올바르지 않은 코드입니다");
                    codenumbercheck = false;
                    codeenglishcheck = false;
                } else {                                                //공백이 아닐 때 처리할 내용
                    if ((codetext.charAt(0) == 115 && codetext.charAt(1) == 107) || (codetext.charAt(0) == 83 && codetext.charAt(1) == 75) || (codetext.charAt(0) == 87 && codetext.charAt(1) == 75) || (codetext.charAt(0) == 119 && codetext.charAt(1) == 107) || (codetext.charAt(0) == 112 && codetext.charAt(1) == 109) || (codetext.charAt(0) == 80 && codetext.charAt(1) == 77) || (codetext.charAt(0) == 65 && codetext.charAt(1) == 67) || (codetext.charAt(0) == 97 && codetext.charAt(1) == 99) || (codetext.charAt(0) == 67 && codetext.charAt(1) == 72) || (codetext.charAt(0) == 99 && codetext.charAt(1) == 104) || (codetext.charAt(0) == 65 && codetext.charAt(1) == 84) || (codetext.charAt(0) == 97 && codetext.charAt(1) == 116)) {
                        codeenglishcheck = true;
                    } else {
                        text_Explanation2.setText("올바르지 않은 코드입니다");
                        codeenglishcheck = false;
                    }

                    int count = 0;
                    for (int i = 2; i < 7; i++) {
                        if (codetext.charAt(i) >= 48 && codetext.charAt(i) <= 57 && codeenglishcheck == true) {
                            count++;
                        }
                    }
                    if (count != 5) {
                        text_Explanation2.setText("올바르지 않은 코드입니다");
                        count = 0;
                    } else {
                        text_Explanation2.setText("올바른 코드입니다");
                        codenumbercheck = true;
                        count = 0;
                    }
                    if (codeenglishcheck == true && codenumbercheck == true) {
                        SharedPreferences sharedPreferences = getSharedPreferences("check", MODE_PRIVATE);
                        Toast.makeText(Input_CodePopup.this, "연주모드를 사용하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Boolean checked = true;
                        editor.putBoolean("codecheck", checked);
                        editor.commit();

                        SharedPreferences cc = getSharedPreferences("check", MODE_PRIVATE);


                        Toast.makeText(Input_CodePopup.this, "연주 모드", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), play_mode.class));
                        overridePendingTransition(R.anim.anim_slide_down, R.anim.anim_slide_up);

                        finish();
                    }
                }
            }

        });
        btn_buycode = findViewById(R.id.btn_buycode);
        btn_buycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btn_close = (Button) findViewById(R.id.btn_close);


        //닫기버튼 클릭함수
        btn_close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        finish();
                }
                return false;
            }
        });
    }
}

