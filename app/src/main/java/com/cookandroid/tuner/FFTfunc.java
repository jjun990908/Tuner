package com.cookandroid.tuner;


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
        // 위 함수는 배열 안에서 가장 큰 값, 작은 값을 제외한 세 개의 평균만을 리턴함

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
        }
        if (idx1 != idx2) {
            return sum/3;
        } else {
            return (sum - Array_Float[0])/3;
        }
    }

}
