package com.cookandroid.tuner;


import android.util.Log;
import android.widget.Button;

public class FFTfunc {
    protected static float MaxInFFTArray(double [] Array_Double, int sensitivity){
        // 위 함수는 배열 안에서 가장 큰 값을 리턴, 소리가 너무 작으면 -1 리턴
        double MAX = 0; // initialize
        int idx = 0; // initialize

        for(int i=0; i< Array_Double.length; i++){
            if(Array_Double[i] > MAX){
                MAX = Array_Double[i];
                idx = i;
            }
        }/*i*/

        return MAX>sensitivity?idx:-1; //삼항 연산자, 너무 작은 소리는 -1 리턴
    } // MaxInFFTArray



    protected static float NoiseDetect(float[] Array_Float) {
        // 위 함수는 배열 안에서 가장 큰 값, 가장 작은 값을 제외한 세 개 값의 평균을 리턴함 (노이즈 제거)

        float MAX = -Float.MAX_VALUE;
        float min = Float.MIN_VALUE;

        int idx1 = 0;
        int idx2 = 1;
        int sum = 0;
        for (int i = 0; i < Array_Float.length; i++) {
            if (Array_Float[i] > MAX) {
                MAX = Array_Float[i];
                idx1 = i;
            }
            if (Array_Float[i] < min) {
                min = Array_Float[i];
                idx2 = i;
            }
        }/*i*/

        for (int i = 0; i < Array_Float.length; i++) {
            if (i != idx1 && i != idx2) {
                sum += Array_Float[i];
            }
        }/*i*/

        if (idx1 != idx2) {
            return sum/3;
        } else {
            return (sum - Array_Float[0])/3;
        }
    }

    protected static void setCuHz(Button[] buttons,int Pressed_Button_ID, double Current_HZ ,boolean flatmode, boolean sharpmode){
        for(int i=0;i<buttons.length;i++){
            if(sharpmode && buttons[i].getId() == Pressed_Button_ID && Double.compare(ScaleSrc.scaleArraySharp[i], Current_HZ)!=0){
                MainActivity.CuHz = ScaleSrc.scaleArraySharp[i];
            }
            else if(flatmode && buttons[i].getId() == Pressed_Button_ID && Double.compare(ScaleSrc.scaleArrayFlat[i], Current_HZ)!=0){
                MainActivity.CuHz = ScaleSrc.scaleArrayFlat[i];
                System.out.println("s11");
            }
            else if (buttons[i].getId() == Pressed_Button_ID && Double.compare(ScaleSrc.scaleArrayNormal[i], Current_HZ)!=0){
                MainActivity.CuHz = ScaleSrc.scaleArrayNormal[i];
                System.out.println("s22");
            }
            else{continue;}


        }
    }
}
