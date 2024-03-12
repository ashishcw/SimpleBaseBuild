package org.ashish.basebuild.handler.input;

import org.ashish.basebuild.Main;
import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.NPCs.CoalGatherer;
import org.ashish.basebuild.objects.NPCs.IronGatherer;
import org.ashish.basebuild.objects.NPCs.WaterGatherer;
import org.ashish.basebuild.objects.common.Node;
import org.ashish.basebuild.objects.map.constants.ResourceType;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    //public boolean[] keys = new boolean[4];
    public boolean[] keys = new boolean[5];
    //keys 0 = Right
    //keys 1 = left
    //keys 2 = up
    //keys 3 = down

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            keys[0] = true;
        }

        if (key == KeyEvent.VK_A) {
            keys[1] = true;
        }

        if (key == KeyEvent.VK_W) {
            keys[2] = true;
        }

        if (key == KeyEvent.VK_S) {
            keys[3] = true;
        }

        if (key == KeyEvent.VK_E) {
            keys[4] = true;
        }


    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            keys[0] = false;

        }

        if (key == KeyEvent.VK_A) {
            keys[1] = false;
        }

        if (key == KeyEvent.VK_W) {
            keys[2] = false;
        }

        if (key == KeyEvent.VK_S) {
            keys[3] = false;
        }

        if (key == KeyEvent.VK_E) {
            keys[4] = false;
        }

        if (key == KeyEvent.VK_SPACE) {
            GameObjectModelMain playerObj = null;
            for (var playerItem : MainHandler.allObjectsList) {
                if (playerItem.getBaseObjectType() == GameObjectModelMain.BaseObjectType.Player) {
                    playerObj = playerItem;
                    break;
                }
            }
            var clickedNode = Node.getaClickedNode(new Point(playerObj.getxPos(), playerObj.getyPos()));

            if (clickedNode != null) {
                System.out.println("Row Position : " + clickedNode.getRow());
                System.out.println("Col Position : " + clickedNode.getCol());
                System.out.println("x Position : " + clickedNode.getxPos());
                System.out.println("y Position : " + clickedNode.getyPos());
                System.out.println("***************");
            }

            clickedNode.setNodeType(Node.NodeType.block);
            Node.nodes[clickedNode.getRow()][clickedNode.getCol()] = clickedNode;

        }

        if(key == KeyEvent.VK_B){
            MainHandler.addGameObjectToList(new WaterGatherer(0, 304, ResourceType.RawResourceType.Water_Resource));
        }

        if(key == KeyEvent.VK_X){
            MainHandler.addGameObjectToList(new IronGatherer(0, 304, ResourceType.RawResourceType.Iron_Resource));
        }

        if(key == KeyEvent.VK_C){
            MainHandler.addGameObjectToList(new CoalGatherer(0, 304, ResourceType.RawResourceType.Coal_Resource));
        }


    }


}