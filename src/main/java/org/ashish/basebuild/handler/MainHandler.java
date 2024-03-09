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

    public void addGameObjectToList(GameObjectModelMain tempObj){
        if(!this.allObjectsList.contains(tempObj)){
            this.allObjectsList.add(tempObj);
        }
    }

    public void removeGameObjectFromList(GameObjectModelMain tempObj){
        if(this.allObjectsList.contains(tempObj)){
            this.allObjectsList.remove(tempObj);
        }
    }



}
