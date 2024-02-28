package org.ashish.basebuild;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.display.Window;

import java.awt.*;

public class Main extends Canvas implements Runnable {

    //Thread
    Thread thread;
    private boolean isRunning = false;

    //Window
    Window window;


    public Main() {
        init();
    }

    public static void main(String[] args) {
        //System.out.println("Hello world!");
        new Main();
    }

    private void init(){
        if(this.thread == null){
            this.thread = new Thread(this, "Additional_Thread_1");
        }

        if(this.window == null){
            this.window = new Window(this);
        }


        this.requestFocus();
        start();
    }


    @Override
    public void run(){
        mainLoop();
    }

    private void mainLoop(){
        long lastLoopTime = System.nanoTime();
        long lastFPSTime = 0;


        while(this.isRunning){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            double delta = updateLength / ((double) Constants.OPTIMAL_TIME);

            lastFPSTime += updateLength;
            if (lastFPSTime >= 1000000000) {
                lastFPSTime = 0;
            }

            // Update game logic based on delta
            tick();

            // Repaint or render the game
            render();



            try {
                // Sleep to maintain frame rate
                long gameTime = (lastLoopTime - System.nanoTime() + Constants.OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            } catch (Exception e) {
                // Handle exceptions
                this.isRunning = false;
            }
        }
        stop();
    }

    private synchronized void start(){
        if(this.isRunning){
            return;
        }


        if(this.thread != null){
            this.thread.start();
        }


        this.isRunning = true;

    }

    private synchronized void stop(){
        if(!this.isRunning){
            return;
        }

        if(this.thread != null){
            try {
                this.thread.join();
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        this.isRunning = false;
    }

    private void tick(){
    }

    private void render(){
        var bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(03);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        //additional render calls go here

        bs.show();
        g.dispose();
    }
}