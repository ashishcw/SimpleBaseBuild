package org.ashish.basebuild.objects.player;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.handler.input.KeyboardInput;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.manager.ResourceManager;
import org.ashish.basebuild.objects.map.MapGeneration;
import org.ashish.basebuild.objects.map.Resources.CoalResource;
import org.ashish.basebuild.objects.map.Resources.IronResource;
import org.ashish.basebuild.objects.map.Resources.WaterResource;
import org.ashish.basebuild.objects.map.constants.ResourceType;
import org.ashish.basebuild.objects.map.home.HomeBase;
import org.ashish.basebuild.utils.MathHelper;

import java.awt.*;
import java.util.Map;

public class Player extends GameObjectModelMain {

    private float acc = 1f;

    private float dcc = 0.5f;

    private float gatheringRate = 0.5f, dumpingRate = 0.5f, ironResources = 0.0f, waterResources = 0.0f, coalResources = 0.0f;
    private float ironResourcesMAX = 20.0f, waterResourcesMAX = 20.0f, coalResourcesMAX = 20.0f;

    private KeyboardInput keyInputHandler;

    private GameObjectModelMain IronResourceObject, WoodResourceObject, WaterResourceObject;

    private MainHandler handler;

    public Player(int xPos, int yPos, KeyboardInput keyInputHandlerParam, MainHandler handlerParam) {
        super(xPos, yPos);
        this.keyInputHandler = keyInputHandlerParam;
        this.handler = handlerParam;
        this.setSizeWidth(Constants.NODE_SIZE);
        this.setSizeHeight(Constants.NODE_SIZE);
        this.setColor(Color.GREEN);
        this.setActiveinScene(true);
        this.setVisible(true);
        this.setBaseObjectType(BaseObjectType.Player);
        this.setHitBox(new Rectangle((int)this.getxPos(), (int)this.getyPos(), this.getSizeWidth()*2, this.getSizeHeight()*2));


    }

    @Override
    public void tick() {
        if(this.isActiveinScene()){
            move();

            //check collision
            collision();

        }
    }

    @Override
    public void render(Graphics g) {

        if(this.isActiveinScene()){
            if(this.isVisible()){
                g.setColor(this.getColor());
                g.fillRect((int)this.getxPos(), (int)this.getyPos(), this.getSizeWidth(), this.getSizeHeight());

                ////hitbox
                //g.setColor(Color.RED);
                //g.drawRect(this.getHitBox().x, this.getHitBox().y, this.getHitBox().width, this.getHitBox().height);
            }
        }
    }

    private void move(){

        var xpos = this.getxPos();
        var ypos = this.getyPos();
        xpos += this.getxVel();
        ypos += this.getyVel();

        //Horizontal movement(A & D Key press)
        if(this.keyInputHandler.keys[0]){
            var xvel = this.getxVel();
            this.setxVel(xvel + this.acc);
        }
        else if(this.keyInputHandler.keys[1]){
            var xvel = this.getxVel();
            this.setxVel(xvel - this.acc);
        }else if(!this.keyInputHandler.keys[1] && !this.keyInputHandler.keys[0]){
            if(this.getxVel() > 0){
                var xvel = this.getxVel();
                this.setxVel(xvel - this.dcc);
                //this.velX -= this.dcc;
            } else if (this.getxVel() < 0) {
                //this.velX += this.dcc;
                var xvel = this.getxVel();
                this.setxVel(xvel + this.dcc);
            }
        }



        //Horizontal movement(W & S Key press)
        if(this.keyInputHandler.keys[3]){
            var yvel = this.getyVel();
            this.setyVel(yvel + this.acc);
        }
        else if(this.keyInputHandler.keys[2]){
            var yvel = this.getyVel();
            this.setyVel(yvel - this.acc);
        }else if(!this.keyInputHandler.keys[2] && !this.keyInputHandler.keys[3]){
            if(this.getyVel() > 0){
                var yvel = this.getyVel();
                this.setyVel(yvel - this.dcc);
                //this.velX -= this.dcc;
            } else if (this.getyVel() < 0) {
                //this.velX += this.dcc;
                var yvel = this.getyVel();
                this.setyVel(yvel + this.dcc);
            }
        }

        //setting max and minimum movement speed
        this.setxVel(MathHelper.Clamp((int)this.getxVel(), 5, -5));
        this.setyVel(MathHelper.Clamp((int)this.getyVel(), 5, -5));
        //velY = MathHelper.Clamp(velY, 5, -5);


        //setting up the hitbox
        this.getHitBox().x = (int)this.getxPos()-(this.getSizeWidth()/2);
        this.getHitBox().y = (int)this.getyPos()-(this.getSizeHeight()/2);

        this.setxPos(xpos);
        this.setyPos(ypos);
    }

    private void collision(){
        for(int i = 0; i < MapGeneration.interactableResources.size(); i++){
            var mapGenerationInteractableResourceTemp = MapGeneration.interactableResources.get(i);
            if(
                    mapGenerationInteractableResourceTemp.getBaseObjectType() == BaseObjectType.Base_Home
                    ||
                    mapGenerationInteractableResourceTemp.getBaseObjectType() == BaseObjectType.Resources
            ){
                if(MathHelper.isColliding(this.getHitBox(), mapGenerationInteractableResourceTemp.getHitBox())){
                    //System.out.println("Player collision with " + MapGeneration.interactableResources.get(i).getClass().getSimpleName());
                    if(mapGenerationInteractableResourceTemp.getName().contains("Coal")){
                        var tempResource = (CoalResource)mapGenerationInteractableResourceTemp;
                        gatherResource(tempResource.getRawResourceType());
                    }

                    if(mapGenerationInteractableResourceTemp.getName().contains("Water")){
                        var tempResource = (WaterResource)mapGenerationInteractableResourceTemp;
                        gatherResource(tempResource.getRawResourceType());
                    }

                    if(mapGenerationInteractableResourceTemp.getName().contains("Iron")){
                        var tempResource = (IronResource)mapGenerationInteractableResourceTemp;
                        gatherResource(tempResource.getRawResourceType());
                    }

                    if(mapGenerationInteractableResourceTemp.getName().contains("Home")){
                        var tempResource = (HomeBase)mapGenerationInteractableResourceTemp;
                        //gatherResource(tempResource.getRawResourceType());
                        this.dumpResource(tempResource.getBaseObjectType());
                    }

                }
            }

        }

    }

    private void gatherResource(ResourceType.RawResourceType rawResourceType){
        if(this.keyInputHandler.keys[4]){
            //Key pressed
            if(rawResourceType == ResourceType.RawResourceType.Coal_Resource){
                if(coalResources < coalResourcesMAX){
                    coalResources += gatheringRate;
                    System.out.println(rawResourceType + " : " + coalResources);
                }

            }

            if(rawResourceType == ResourceType.RawResourceType.Iron_Resource){
                if(ironResources < ironResourcesMAX){
                    ironResources += gatheringRate;
                    System.out.println(rawResourceType + " : " + ironResources);
                }

            }

            if(rawResourceType == ResourceType.RawResourceType.Water_Resource){
                if(waterResources < waterResourcesMAX){
                    waterResources += gatheringRate;
                    System.out.println(rawResourceType + " : " + waterResources);
                }

            }

        }
    }

    private void dumpResource(BaseObjectType baseObjectType){
        if(this.keyInputHandler.keys[4]){

            if(baseObjectType == BaseObjectType.Base_Home){
                if(coalResources > 0.0f){
                    coalResources-=dumpingRate;
                    ResourceManager.COLLECTED_COAL += dumpingRate;
                    coalResources = MathHelper.ClampFloat(coalResources, coalResourcesMAX, 0);
                    //System.out.println("Collected Coal : " + coalResources);
                    System.out.println("Collected Coal : " + ResourceManager.COLLECTED_COAL);
                }

                if(ironResources > 0.0f){
                    ironResources-=dumpingRate;
                    ResourceManager.COLLECTED_IRON += dumpingRate;
                    ironResources = MathHelper.ClampFloat(ironResources, ironResourcesMAX, 0);
                    //System.out.println("Collected Iron : " + ironResources);
                    System.out.println("Collected Iron : " + ResourceManager.COLLECTED_IRON);
                }

                if(waterResources > 0.0f){
                    waterResources-=dumpingRate;
                    ResourceManager.COLLECTED_WATER += dumpingRate;
                    waterResources = MathHelper.ClampFloat(waterResources, waterResourcesMAX, 0);
                    //System.out.println("Collected Water : " + waterResources);
                    System.out.println("Collected Water : " + ResourceManager.COLLECTED_WATER);
                }

            }





        }

    }
}