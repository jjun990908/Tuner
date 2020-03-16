package com.cookandroid.tuner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onestore.iap.api.IapEnum;
import com.onestore.iap.api.IapResult;
import com.onestore.iap.api.ProductDetail;
import com.onestore.iap.api.PurchaseClient;
import com.onestore.iap.api.PurchaseData;

import java.util.ArrayList;
import java.util.List;

public class Input_CodePopup extends AppCompatActivity {
    private PurchaseClient mPurchaseClient;
    private final String TAG = getClass().getSimpleName();
    // api version 상수
    private static final int IAP_API_VERSION = 5;
    private static final int LOGIN_REQUEST_CODE = 1001;
    private static final int PURCHASE_REQUEST_CODE = 2001;
    TextView txt_codeview, text_Explanation2;
    Button btn_close, btn_confirmcode, btn_confirm, btn_buycode;
    boolean codenumbercheck = false, codeenglishcheck = false;
    Context mContext;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPurchaseClient = new PurchaseClient(this, AppSecurity.getPublicKey());

        // 원스토어 서비스로 인앱결제를 위한 서비스 바인딩을 요청합니다.
        mPurchaseClient.connect(mServiceConnectionListener);


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
                goBuy();

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
    // 2-3 Init
    PurchaseClient.ServiceConnectionListener mServiceConnectionListener = new PurchaseClient.ServiceConnectionListener() {
        @Override
        public void onConnected() {
            Log.d(TAG, "Service connected");
            checkBillingSupportedAndLoadPurchases();
        }

        @Override
        public void onDisconnected() {
            Log.d(TAG, "Service disconnected");
        }

        @Override
        public void onErrorNeedUpdateException() {
            Log.e(TAG, "connect onError, 원스토어 서비스앱의 업데이트가 필요합니다");
            PurchaseClient.launchUpdateOrInstallFlow(Input_CodePopup.this);
        }


    };
    // 2-4 리스너
    PurchaseClient.BillingSupportedListener mBillingSupportedListener = new PurchaseClient.BillingSupportedListener() {

        @Override
        public void onSuccess() {
            Log.d(TAG, "isBillingSupportedAsync onSuccess");
        }

        @Override
        public void onError(IapResult result) {
            Log.e(TAG, "isBillingSupportedAsync onError, " + result.toString());
        }

        @Override
        public void onErrorRemoteException() {
            Log.e(TAG, "isBillingSupportedAsync onError, 원스토어 서비스와 연결을 할 수 없습니다");
        }

        @Override
        public void onErrorSecurityException() {
            Log.e(TAG, "isBillingSupportedAsync onError, 비정상 앱에서 결제가 요청되었습니다");
        }

        @Override
        public void onErrorNeedUpdateException() {
            Log.e(TAG, "isBillingSupportedAsync onError, 원스토어 서비스앱의 업데이트가 필요합니다");
        }

    };
    // 2-4 실제 실행하는 메소드
    private void checkBillingSupportedAndLoadPurchases() {
        Log.d(TAG, "checkBillingSupportedAndLoadPurchases()");

        if (mPurchaseClient == null) {
            Log.d(TAG, "PurchaseClient is not initialized");
            return;
        }
        // 럭키꺼임
        // showProgress(this);
        mPurchaseClient.isBillingSupportedAsync(IAP_API_VERSION, mBillingSupportedListener);
    }
    // 2-5 리스너
    PurchaseClient.QueryProductsListener mQueryProductsListener = new PurchaseClient.QueryProductsListener() {
        @Override
        public void onSuccess(List<ProductDetail> productDetails) {
            Log.d(TAG, "queryProductsAsync onSuccess, " + productDetails.toString());
        }

        @Override
        public void onErrorRemoteException() {
            Log.e(TAG, "queryProductsAsync onError, 원스토어 서비스와 연결을 할 수 없습니다");
        }

        @Override
        public void onErrorSecurityException() {
            Log.e(TAG, "queryProductsAsync onError, 비정상 앱에서 결제가 요청되었습니다");
        }

        @Override
        public void onErrorNeedUpdateException() {
            Log.e(TAG, "queryProductsAsync onError, 원스토어 서비스앱의 업데이트가 필요합니다");
        }

        @Override
        public void onError(IapResult result) {
            Log.e(TAG, "queryProductsAsync onError, " + result.toString());
        }
    };
    // 2-5 실제 실행하는 메소드
    public void goBuy(){
        loadAllProducts();

        buyProduct(AppConstant.WIKIWIKI_INAPP_3000,IapEnum.ProductType.IN_APP);
    }
    private void loadAllProducts() {
        ArrayList<String> inapp = new ArrayList<>();
        inapp.add(AppConstant.WIKIWIKI_INAPP_3000);
        loadProducts(IapEnum.ProductType.IN_APP, inapp);

        // 월정액
//        ArrayList<String> auto = new ArrayList<>();
//        auto.add(AppConstant.PRODUCT_AUTO_100000);
//        loadProducts(IapEnum.ProductType.AUTO, auto);
    }
    private void loadProducts(IapEnum.ProductType productType, final ArrayList<String> products) {
        Log.d(TAG, "loadProducts");

        if (mPurchaseClient == null) {
            Log.d(TAG, "PurchaseClient is not initialized");
            return;
        }

        // 럭키꺼
        // showProgress(this);

        mPurchaseClient.queryProductsAsync(IAP_API_VERSION, products, productType.getType(), mQueryProductsListener);
    }
    // 2-6 리스너
    PurchaseClient.PurchaseFlowListener mPurchaseFlowListener = new PurchaseClient.PurchaseFlowListener() {
        @Override
        public void onSuccess(PurchaseData purchaseData) {
            Log.d(TAG, "launchPurchaseFlowAsync onSuccess, " + purchaseData.toString());
            // 구매완료 후 developer payload 검증을 수해한다.
            if (!isValidPayload(purchaseData.getDeveloperPayload())) {
                Log.d(TAG, "launchPurchaseFlowAsync onSuccess, Payload is not valid.");
                return;
            }

            // 구매완료 후 signature 검증을 수행한다.
            // isValidPurchase 메서드 이름을 verifyPurchase 로 변경했음. *LuckyActivity.java line58 참고
            boolean validPurchase = AppSecurity.verifyPurchase(purchaseData.getPurchaseData(), purchaseData.getSignature());
            if (validPurchase) {
                if (AppConstant.WIKIWIKI_INAPP_3000.equals(purchaseData.getProductId())) {{
                    // MainActivity.java의 SharedPreferences cc 객체에 true 전달
                    SharedPreferences sharedPreferences = getSharedPreferences("check", MODE_PRIVATE);
                    Toast.makeText(Input_CodePopup.this, "연주모드를 사용하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Boolean checked = true;
                    editor.putBoolean("codecheck", checked);
                    editor.commit();

                    SharedPreferences cc = getSharedPreferences("check", MODE_PRIVATE);

                    // Activity 전환
                    Toast.makeText(Input_CodePopup.this, "연주 모드", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), play_mode.class));
                    overridePendingTransition(R.anim.anim_slide_down, R.anim.anim_slide_up);

                    finish();
                }
                } else {
                    Log.d(TAG, "launchPurchaseFlowAsync onSuccess, Signature is not valid.");
                    return;
                }
            }
        }
        @Override
        public void onError(IapResult result) {
            Log.e(TAG, "launchPurchaseFlowAsync onError, " + result.toString());
        }
        @Override
        public void onErrorRemoteException() {
            Log.e(TAG, "launchPurchaseFlowAsync onError, 원스토어 서비스와 연결을 할 수 없습니다");
        }

        @Override
        public void onErrorSecurityException() {
            Log.e(TAG, "launchPurchaseFlowAsync onError, 비정상 앱에서 결제가 요청되었습니다");
        }

        @Override
        public void onErrorNeedUpdateException() {
            Log.e(TAG, "launchPurchaseFlowAsync onError, 원스토어 서비스앱의 업데이트가 필요합니다");
        }
    };
    // 2-6 line 257에 쓰이는 isValidPayload 매소드(럭키에 구현된거 가져옴)
    private boolean isValidPayload(String payload) {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String savedPayload = sp.getString(AppConstant.KEY_PAYLOAD, "");

        Log.d(TAG, "isValidPayload saved payload ::" + savedPayload);
        Log.d(TAG, "isValidPayload server payload ::" + payload);

        return savedPayload.equals(payload);
    }
    // 2-6 리스너가 사용되는 메소드(호출은 goBuy에서 함)
    private void buyProduct(final String productId, final IapEnum.ProductType productType) {
        Log.d(TAG, "buyProduct() - productId:" + productId + " productType: " + productType.getType());

        if (mPurchaseClient == null) {
            Log.d(TAG, "PurchaseClient is not initialized");
            return;
        }

        /*
         * TODO: AppSecurity.generatePayload() 는 예제일 뿐, 각 개발사의 규칙에 맞는 payload를 생성하여야 한다.
         *
         * 구매요청을 위한 Developer payload를 생성합니다.
         * Developer Payload 는 상품의 구매 요청 시에 개발자가 임의로 지정 할 수 있는 문자열입니다.
         * 이 Payload 값은 결제 완료 이후에 응답 값에 다시 전달 받게 되며 결제 요청 시의 값과 차이가 있다면 구매 요청에 변조가 있다고 판단 하면 됩니다.
         * Payload 검증을 통해 Freedom 과 같은 변조 된 요청을 차단 할 수 있으며, Payload 의 발급 및 검증 프로세스를 자체 서버를 통해 이루어 지도록합니다.
         */
        String devPayload = AppSecurity.generatePayload();

        // 구매 후 dev payload를 검증하기 위하여 프리퍼런스에 임시로 저장합니다.
        savePayloadString(devPayload);
        //럭키꺼
        //showProgress(this);

        /*
         * PurchaseClient의 launchPurchaseFlowAsync API를 이용하여 구매요청을 진행합니다.
         * 상품명을 공백("")으로 요청할 경우 개발자센터에 등록된 상품명을 결제화면에 노출됩니다. 구매시 지정할 경우 해당 문자열이 결제화면에 노출됩니다.
         */
        if (mPurchaseClient.launchPurchaseFlowAsync(IAP_API_VERSION, this, PURCHASE_REQUEST_CODE, productId, "",
                productType.getType(), devPayload, "", false, mPurchaseFlowListener) == false) {
            // listener is null
        }
    }
    // payload 저장하는 메소드
    private void savePayloadString(String payload) {
        SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
        spe.putString(AppConstant.KEY_PAYLOAD, payload);
        spe.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult resultCode " + resultCode);

        switch (requestCode) {
            case PURCHASE_REQUEST_CODE:
                /*
                 * launchPurchaseFlowAsync API 호출 시 전달받은 intent 데이터를 handlePurchaseData를 통하여 응답값을 파싱합니다.
                 * 파싱 이후 응답 결과를 launchPurchaseFlowAsync 호출 시 넘겨준 PurchaseFlowListener 를 통하여 전달합니다.
                 */
                if (resultCode == Activity.RESULT_OK) {
                    if (mPurchaseClient.handlePurchaseData(data) == false) {
                        Log.e(TAG, "onActivityResult handlePurchaseData false ");
                        // listener is null
                    }
                } else {
                    Log.e(TAG, "onActivityResult user canceled");
                    // user canceled , do nothing..
                }
                break;
            default:
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        if (mPurchaseClient == null) {
            Log.d(TAG, "PurchaseClient is not initialized");
            return;
        }

        // 앱 종료시 PurchaseClient를 이용하여 서비스를 terminate 시킵니다.
        mPurchaseClient.terminate();
    }

}

