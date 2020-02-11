package com.cookandroid.tuner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Input_Code_Popup extends AppCompatActivity {
    TextView txt_codeview, text_Explanation2;
    Button btn_close,btn_confirmcode, btn_confirm, btn_buycode;
    boolean codenumbercheck =false , codeenglishcheck =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_code_popup);
        btn_confirmcode = findViewById(R.id.btn_confirmcode);
        btn_confirmcode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText code = (EditText)findViewById(R.id.editText_inputcode);
                text_Explanation2 = findViewById(R.id.text_Explanation2);
                String codetext = code.getText().toString();
                if (codetext.length() == 0 ) {         //공백일 때 처리할 내용
                    text_Explanation2.setText("코드를 입력해 주세요");
                    codenumbercheck = false;
                    codeenglishcheck = false;
                }
                else if(codetext.length() !=7 ){
                    text_Explanation2.setText("올바르지 않은 코드입니다");
                    codenumbercheck = false;
                    codeenglishcheck = false;
                }
                else {                                                //공백이 아닐 때 처리할 내용
                    if(codetext.charAt(0)== 87 && codetext.charAt(1)== 75 || codetext.charAt(0)== 119 && codetext.charAt(1)== 107){
                        codeenglishcheck = true;
                    }
                    else{
                        text_Explanation2.setText("올바르지 않은 코드입니다");
                        codeenglishcheck = false;
                    }

                    int count = 0;
                    for(int i = 2; i<7; i++ ) {
                        if (codetext.charAt(i) >= 48 && codetext.charAt(i) <= 57 && codeenglishcheck ==true) {
                            count++;
                        }
                    }
                     if (count !=5){
                         text_Explanation2.setText("올바르지 않은 코드입니다");
                         count = 0;
                     }
                     else {
                         text_Explanation2.setText("올바른 코드입니다");
                         codenumbercheck = true;
                         count = 0;
                     }
                }
            }
        });
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(codeenglishcheck == true && codenumbercheck ==true){
                    SharedPreferences sharedPreferences = getSharedPreferences("check", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Boolean checked = true;
                    editor.putBoolean("codecheck", checked);
                    editor.commit();
                    finish();
                }
            }

        });

        btn_close = (Button)findViewById(R.id.btn_close);


        //닫기버튼 클릭함수
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
