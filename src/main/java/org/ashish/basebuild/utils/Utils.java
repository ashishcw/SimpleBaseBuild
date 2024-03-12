package org.ashish.basebuild.utils;

import java.awt.*;

public class Utils {
    public static boolean IsTargetReached(Point source, Point target){
        if(
                ((source.getX() == target.getX())
                &&
                (source.getY() == target.getY()))
        ){
            return true;
        }
        return false;
    }
}
