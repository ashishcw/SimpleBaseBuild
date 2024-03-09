package org.ashish.basebuild.ui;

import org.ashish.basebuild.Main;
import org.ashish.basebuild.constant.Constants;

import javax.swing.*;
import java.awt.*;

public class UIMain {

    public static int tempTotal = 21;
    private JPanel panel;
    private JFrame frame;
    private Main main;

    JLabel label = new JLabel();

    public UIMain(JFrame frameParam, Main mainParam) {
        this.panel = new JPanel();
        this.main = mainParam;
        this.frame = frameParam;
        init();
    }

    public UIMain(){

    }

    private void init(){
        if(this.panel != null){
            this.panel.setBackground(new Color(0, 0, 255, 100));


//            label.setHorizontalTextPosition(JLabel.LEFT);
//            label.setVerticalTextPosition(JLabel.EAST);
            //this.panel.setBounds(0, 0, Constants.WINDOW_WIDTH, (100));
            //this.panel.setSize(new Dimension(Constants.WINDOW_WIDTH, 100));
            //this.panel.setLayout(new GridLayout());
            this.panel.setVisible(true);
            this.panel.add(label);
            this.panel.add(this.main);
        }

        this.frame.add(this.panel);

        this.frame.pack();
    }

    public void updateTextLable(){
        label.setText("Coal : "+tempTotal);
    }


}
