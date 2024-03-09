package org.ashish.basebuild.utils;

import java.awt.*;

public class MathHelper {
    public static int Clamp(int value, int max, int min){
        if(value > max){
            value = max;
        }else if(value < min){
            value = min;
        }
        return value;
    }

    public static float ClampFloat(float value, float max, float min){
        if(value > max){
            value = max;
        }else if(value < min){
            value = min;
        }
        return value;
    }

    public static boolean isColliding(Rectangle source, Rectangle target){
        if(source.intersects(target)){
            return true;
        }
        return false;
    }


}
