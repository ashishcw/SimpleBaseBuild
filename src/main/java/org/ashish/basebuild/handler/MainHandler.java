package org.ashish.basebuild.handler;

import org.ashish.basebuild.model.GameObjectModelMain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainHandler {
    public static List<GameObjectModelMain>allObjectsList = new ArrayList<>();

    public void tick(){
        if(this.allObjectsList.size() > 0){
            for(int i = 0; i < this.allObjectsList.size(); i++){
                this.allObjectsList.get(i).tick();
            }
        }
    }

    public void render(Graphics g){
        if(this.allObjectsList.size() > 0){
            for(int i = 0; i < this.allObjectsList.size(); i++){
                this.allObjectsList.get(i).render(g);
            }
        }
    }

    public static void addGameObjectToList(GameObjectModelMain tempObj){
        if(!allObjectsList.contains(tempObj)){
            allObjectsList.add(tempObj);
        }
    }

    public static void removeGameObjectFromList(GameObjectModelMain tempObj){
        if(allObjectsList.contains(tempObj)){
            allObjectsList.remove(tempObj);
        }
    }



}
