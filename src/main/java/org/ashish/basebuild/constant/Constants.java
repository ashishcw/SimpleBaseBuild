package org.ashish.basebuild.constant;

public class Constants {
    //Main
    public static final double VERSION = 1.0;

    //Display settings
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 800;
    public static final int FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000 / FPS;

    //Node settings
    public static final int NODE_SIZE = 16;
    public static final int NODE_OFFSET = 16;
    public static final int MAX_ROWS = (WINDOW_HEIGHT/10)-NODE_SIZE-17;//15;
    public static final int MAX_COLS = (WINDOW_WIDTH/10)-NODE_SIZE-15;
}
