package org.ashish.basebuild.objects.map;

import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.map.Path.EnemyPathBase;
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

    EnemyPathBase[] enemyPathBases = new EnemyPathBase[31];

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



        enemyPathBases[0] = new EnemyPathBase(720, 32);
        enemyPathBases[1] = new EnemyPathBase(608, 32);
        enemyPathBases[2] = new EnemyPathBase(448, 32);
        enemyPathBases[3] = new EnemyPathBase(288, 32);
        enemyPathBases[4] = new EnemyPathBase(128, 32);
        enemyPathBases[5] = new EnemyPathBase(16, 32);
        enemyPathBases[6] = new EnemyPathBase(16, 96);
        enemyPathBases[7] = new EnemyPathBase(16, 160);
        enemyPathBases[8] = new EnemyPathBase(128, 160);
        enemyPathBases[9] = new EnemyPathBase(288, 160);
        enemyPathBases[10] = new EnemyPathBase(448, 160);
        enemyPathBases[11] = new EnemyPathBase(608, 160);
        enemyPathBases[12] = new EnemyPathBase(720, 160);
        enemyPathBases[13] = new EnemyPathBase(720, 256);
        enemyPathBases[14] = new EnemyPathBase(720, 336);
        enemyPathBases[15] = new EnemyPathBase(720, 416);
        enemyPathBases[16] = new EnemyPathBase(720, 496);
        enemyPathBases[17] = new EnemyPathBase(720, 570);
        enemyPathBases[18] = new EnemyPathBase(720, 640);
        enemyPathBases[19] = new EnemyPathBase(608, 640);
        enemyPathBases[20] = new EnemyPathBase(528, 640);
        enemyPathBases[21] = new EnemyPathBase(448, 640);
        enemyPathBases[22] = new EnemyPathBase(384, 640);
        enemyPathBases[23] = new EnemyPathBase(320, 640);
        enemyPathBases[24] = new EnemyPathBase(256, 640);
        enemyPathBases[25] = new EnemyPathBase(176, 640);
        enemyPathBases[26] = new EnemyPathBase(112, 640);
        enemyPathBases[27] = new EnemyPathBase(16, 640);
        enemyPathBases[28] = new EnemyPathBase(16, 544);
        enemyPathBases[29] = new EnemyPathBase(16, 480);
        enemyPathBases[30] = new EnemyPathBase(16, 400);


        for (var pathBaseItem:this.enemyPathBases) {
            if(this.mainHandler != null){
                this.mainHandler.addGameObjectToList(pathBaseItem);
                interactableResources.add(pathBaseItem);
            }
        }




    }
}
