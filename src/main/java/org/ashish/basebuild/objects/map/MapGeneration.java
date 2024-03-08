package org.ashish.basebuild.objects.map;

import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.map.Resources.CoalResource;
import org.ashish.basebuild.objects.map.Resources.IronResource;
import org.ashish.basebuild.objects.map.Resources.WaterResource;
import org.ashish.basebuild.objects.map.constants.ResourceType;
import org.ashish.basebuild.objects.map.home.HomeBase;

import java.util.ArrayList;
import java.util.List;

public class MapGeneration {
    public static ArrayList<GameObjectModelMain>interactableResources = new ArrayList<>();
    private final MainHandler mainHandler;

    //HomeBase[] homeBase = new HomeBase[9];
    HomeBase[] homeBase = new HomeBase[1];
    IronResource[] ironResources = new IronResource[1];
    WaterResource[] waterResources = new WaterResource[1];
    CoalResource[] coalResources = new CoalResource[1];

    public MapGeneration(MainHandler mainHandlerParam){
        this.mainHandler = mainHandlerParam;
        createMap();
    }
    public void createMap(){
        this.homeBase[0] = new HomeBase(0, 320);

        for (var homeItem:this.homeBase) {
            if(this.mainHandler != null){
                this.mainHandler.addGameObjectToList(homeItem);
                interactableResources.add(homeItem);
            }
        }


        ironResources[0] = new IronResource(592, 720, ResourceType.RawResourceType.Iron_Resource);
        for (var ironResourceItem:this.ironResources) {
            if(this.mainHandler != null){
                this.mainHandler.addGameObjectToList(ironResourceItem);
                interactableResources.add(ironResourceItem);
            }
        }

        waterResources[0] = new WaterResource(640, 720, ResourceType.RawResourceType.Water_Resource);
        for (var waterResourceItem:this.waterResources) {
            if(this.mainHandler != null){
                this.mainHandler.addGameObjectToList(waterResourceItem);
                interactableResources.add(waterResourceItem);
            }
        }

        coalResources[0] = new CoalResource(688, 720, ResourceType.RawResourceType.Coal_Resource);
        for (var coalResourceItem:this.coalResources) {
            if(this.mainHandler != null){
                this.mainHandler.addGameObjectToList(coalResourceItem);
                interactableResources.add(coalResourceItem);
            }
        }



    }
}
