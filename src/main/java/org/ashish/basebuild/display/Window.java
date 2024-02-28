package org.ashish.basebuild.display;

import org.ashish.basebuild.Main;
import org.ashish.basebuild.constant.Constants;

import javax.swing.*;
import java.awt.*;

public class Window {
    private Main main;
    private JFrame frame;

    public Window(Main mainParameter) {
        this.main = mainParameter;
        init();
    }

    private void init(){
        if(this.frame == null){
            this.frame = new JFrame();
        }
        this.frame.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.frame.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.frame.setMinimumSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        this.frame.setTitle("Simple Base Building v." + Constants.VERSION);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(this.main);

        this.frame.pack();
    }
}
