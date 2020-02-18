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

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.Constants;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;

public class Input_CodePopup extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    TextView txt_codeview, text_Explanation2;
    Button btn_close,btn_confirmcode, btn_confirm, btn_buycode;
    boolean codenumbercheck =false , codeenglishcheck =false;
    Context mContext;
    BillingProcessor bp, mBillingProcessor;
    //private PurchaseHeartsAdapter skusAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // onActivityResult 부분이 없을시 구글 인앱 결제창이 동시에 2개가 나타나는 현상이 발생하였고 (원인은 잘 모름)
        // 해당 부분이 있는 경우에는 그런 현상이 발생되지 않았음.
        if (mBillingProcessor.handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }
    @Override
    public void onBillingInitialized() {
        /*
         * Called when BillingProcessor was initialized and it's ready to purchase
         */

        // 초기화하는 메소드로 여러가지 상품이 존재할때 상품의 정보를 가져와 셋팅할수 있는 부분이다.
        // 딱히 초기화는 부분이 필요 없다면 아무런 내용이 없어도 상관 없다.
        // SkuDetails.priceText: 아이템 가격에 현지 화폐 단위를 붙인 String을 리턴한다. 예를들면 '0.99$'이런 식이다.
        // SkuDetails.priceLong: 가격을 long 으로 리턴한다. 0.99 이런식이다.
        // SkuDetails.productId: 제품ID를 가지고 온다. 이를 통해서 어떤 아이템을 구매했는지 판별 가능하다.
        SkuDetails mProduct = mBillingProcessor.getPurchaseListingDetails("buyplaymode");
        if(mProduct != null) {
            String temp = mProduct.productId + " / " + mProduct.priceText + " / "
                    + mProduct.priceValue + " / " + mProduct.priceLong;
            Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
        } else {
        }
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        SkuDetails skuDetails = mBillingProcessor.getPurchaseListingDetails("buyplaymode");

        SharedPreferences sharedPreferences = getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Boolean checked = true;
        editor.putBoolean("codecheck", checked);
        editor.commit();

        SharedPreferences cc = getSharedPreferences("check", MODE_PRIVATE);

        startActivity(new Intent(getApplicationContext(), play_mode.class));
        overridePendingTransition(R.anim.anim_slide_down, R.anim.anim_slide_up);
        finish();
    }
    @Override
    public void onBillingError(int errorCode, Throwable error) {
        /*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         */

        // 인앱 결제시 에러가 발생시 처리하는 메소드 이다.
        // Constants.BILLING_RESPONSE_RESULT_USER_CANCELED 에러는 사용자가 단순 결재 취소한 경우이다.
        if (errorCode != Constants.BILLING_RESPONSE_RESULT_USER_CANCELED) {
            String errorMessage = "구매 에러 발생 " + " (Code " + errorCode + ")";
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPurchaseHistoryRestored() {
        /*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_code_popup);
        mBillingProcessor = new BillingProcessor(this,"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiCjoveQ2+WNj5QElXLUDsUAz5edl+bVJjZ62p3Ig1u2J/nfN6nPE4Cwp4cEIOue7ClVRRQM3sObXHnO+1nUYEbPXnCpNTdx0+Yll6A1eqKJFI6LaSArtKZj7kHj7fIBZkzeJB8yqpe6N+kVI2igQ4ZkK2SwVWjBPBi6+SmHvPIluZ4LmGudVvJeRQ8dooe0y6To6x8HLcaqJPluKvAWYC8vNDXFhKwBdEtfbQJbsUN/k2TI4uReH2gXi1vHKnIHZ3hQz1GL+/CAluNmEzRP5CHOiwCdxVz9MYCk0iDRIuo06o88sFPSJrInW0hSulZsQENdiWpnKhaq5IZdaZTtdxwIDAQAB" ,this);
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiCjoveQ2+WNj5QElXLUDsUAz5edl+bVJjZ62p3Ig1u2J/nfN6nPE4Cwp4cEIOue7ClVRRQM3sObXHnO+1nUYEbPXnCpNTdx0+Yll6A1eqKJFI6LaSArtKZj7kHj7fIBZkzeJB8yqpe6N+kVI2igQ4ZkK2SwVWjBPBi6+SmHvPIluZ4LmGudVvJeRQ8dooe0y6To6x8HLcaqJPluKvAWYC8vNDXFhKwBdEtfbQJbsUN/k2TI4uReH2gXi1vHKnIHZ3hQz1GL+/CAluNmEzRP5CHOiwCdxVz9MYCk0iDRIuo06o88sFPSJrInW0hSulZsQENdiWpnKhaq5IZdaZTtdxwIDAQAB",this);
        bp.initialize();
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText code = (EditText) findViewById(R.id.editText_inputcode);
                text_Explanation2 = findViewById(R.id.text_Explanation2);
                boolean codeexcept=false;
                String codetext = code.getText().toString();
                if (codetext.length() == 0) {         //공백일 때 처리할 내용
                    text_Explanation2.setText("코드를 입력해 주세요");
                    codenumbercheck = false;
                    codeenglishcheck = false;
                } else if (codetext.charAt(0)==65 && codetext.charAt(1)==76 && codetext.charAt(2)==67 && codetext.charAt(3)==72 && codetext.charAt(4)==65 && codetext.charAt(5)==78&&codetext.length()==8||codetext.length()==8&&codetext.charAt(0)==97 && codetext.charAt(1)==108 && codetext.charAt(2)==99 && codetext.charAt(3)==104 && codetext.charAt(4)==97 && codetext.charAt(5)==110){
                    for (int i=48;i<53;i++){
                        for(int j=48;j<=57;j++)
                            if (codetext.charAt(6) == i && codetext.charAt(7) == j)
                                codeexcept = true;
                    }
                    if (codetext.charAt(6)==53&&codetext.charAt(7)==48) codeexcept = true;
                    if (codeexcept){
                        text_Explanation2.setText("올바른 코드입니다");
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
                    else{
                        text_Explanation2.setText("올바르지 않은 코드입니다");
                        codenumbercheck = false;
                        codeenglishcheck = false;
                    }
                }
                else if (codetext.length() != 7) {
                    text_Explanation2.setText("올바르지 않은 코드입니다");
                    codenumbercheck = false;
                    codeenglishcheck = false;
                } else {                                                //공백이 아닐 때 처리할 내용
                    if ((codetext.charAt(0)== 115 && codetext.charAt(1)==107) || (codetext.charAt(0)==83 && codetext.charAt(1)==75) || (codetext.charAt(0) == 87 && codetext.charAt(1) == 75) || (codetext.charAt(0) == 119 && codetext.charAt(1) == 107) || (codetext.charAt(0) == 112 && codetext.charAt(1) == 109) || (codetext.charAt(0) == 80 && codetext.charAt(1) == 77)) {
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
                mBillingProcessor.purchase(Input_CodePopup.this,"buyplaymode");
            }
        });

        btn_close = (Button)findViewById(R.id.btn_close);




        //닫기버튼 클릭함수
        btn_close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        finish();
                }
                return false;
            }
        });
    }


}
