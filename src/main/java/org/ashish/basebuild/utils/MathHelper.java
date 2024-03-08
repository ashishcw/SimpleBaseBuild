package org.ashish.basebuild.utils;

public class MathHelper {
    public static int Clamp(int value, int max, int min){
        if(value > max){
            value = max;
        }else if(value < min){
            value = min;
        }
        return value;
    }


}
