package org.ashish.basebuild.handler.input.mouseinput;

import org.ashish.basebuild.objects.common.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        int x = e.getX();
        int y = e.getY();

//        System.out.println("X Position : " + x);
//        System.out.println("Y Position : " + y);

        var clickedNode = Node.getaClickedNode(new Point(x, y));

        if(clickedNode != null){
            System.out.println("Row Position : " + clickedNode.getRow());
            System.out.println("Col Position : " + clickedNode.getCol());
            System.out.println("x Position : " + clickedNode.getxPos());
            System.out.println("y Position : " + clickedNode.getyPos());
            System.out.println("***************");
        }

    }
}
