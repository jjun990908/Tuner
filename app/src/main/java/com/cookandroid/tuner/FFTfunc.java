package com.cookandroid.tuner;


public class FFTfunc {
    public static float MaxInFFTArray(double [] Array_Double, int sensitivity){
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


}
