package org.ashish.basebuild;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.display.Window;
import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.handler.input.KeyboardInput;
import org.ashish.basebuild.handler.input.mouseinput.MouseInput;
import org.ashish.basebuild.objects.TempObj;
import org.ashish.basebuild.objects.common.Node;
import org.ashish.basebuild.objects.map.MapGeneration;
import org.ashish.basebuild.objects.player.Player;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main extends Canvas implements Runnable {

    //Thread
    Thread thread;
    private boolean isRunning = false;

    //Window
    Window window;

    //Handler
    MainHandler mainHandler;

    //Input
    KeyboardInput keyboardInput;
    MouseInput mouseInput;


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

        if(this.mainHandler == null){
            this.mainHandler = new MainHandler();
        }

        //Input-Keyboard
        if(this.keyboardInput == null){
            this.keyboardInput = new KeyboardInput();
        }
        this.addKeyListener(this.keyboardInput);

        //Input-Mouse
        if(this.mouseInput == null){
            this.mouseInput = new MouseInput();
        }
        this.addMouseListener(this.mouseInput);

        //Node
        Node.createNodeGrid();

        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                this.mainHandler.addGameObjectToList(Node.nodes[i][j]);
            }
        }


        //Map Generation
        MapGeneration mapGeneration = new MapGeneration(this.mainHandler);



        //player
        //this.mainHandler.addGameObjectToList(new Player(100, 150, this.keyboardInput));
        this.mainHandler.addGameObjectToList(new Player(100, 150, this.keyboardInput, this.mainHandler));


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
        //handler
        if(this.mainHandler != null){
            this.mainHandler.tick();
        }
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
        if(this.mainHandler != null){
            this.mainHandler.render(g);
        }

        bs.show();
        g.dispose();
    }
}