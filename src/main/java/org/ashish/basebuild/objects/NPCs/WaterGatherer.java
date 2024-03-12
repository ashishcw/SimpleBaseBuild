package org.ashish.basebuild.objects.NPCs;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.common.Node;
import org.ashish.basebuild.objects.manager.ResourceManager;
import org.ashish.basebuild.objects.map.Resources.WaterResource;
import org.ashish.basebuild.objects.map.constants.ResourceType;
import org.ashish.basebuild.objects.map.home.HomeBase;
import org.ashish.basebuild.pathfinding.AStarPathfinding;
import org.ashish.basebuild.utils.MathHelper;
import org.ashish.basebuild.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WaterGatherer extends GameObjectModelMain {
    private float acc = 1f;

    private float dcc = 2f;

    private float maxSpeed = 1.5f, minSpeed = -2.0f;

    //private Point resourceCollectionLocation = new Point(656, 704), resourceDumpLocation = new Point(64, 336);
    WaterResource waterResource = null;
    HomeBase homeBase = null;


    private float gatheringRate = 0.5f, dumpingRate = 0.5f, waterResourceCollected = 0.0f;
    private float waterResourcesMAX = 20.0f;
    private long waitTimeBeforeAction;

    private AStarPathfinding aStarPathfinding = new AStarPathfinding();

    private boolean goalReached = false;

    Point goalTargetTemp = null;

    private int tempIndex = 1;

    private boolean isOnTheWayToCollectResources = true, isOnTheWayToDumpResources = false, isDumpingResources = false, isCollectingResources = false;

    public List<Node> goalTarget = new ArrayList<>();

    private ResourceType.RawResourceType rawResourceType;

    //temp
    Node currentNode = null, homeNode = null;
    public WaterGatherer(int xPos, int yPos, ResourceType.RawResourceType rawResourceTypeParam) {
        super(xPos, yPos);
        this.setBaseObjectType(BaseObjectType.NPC_Gatherer);
        this.setColor(Color.BLUE);
        this.setSizeWidth(Constants.NODE_SIZE);
        this.setSizeHeight(Constants.NODE_SIZE);
        this.rawResourceType = rawResourceTypeParam;
        this.setHitBox(new Rectangle(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight()));
        this.setName("Water-Resource-Gatherer");

        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                if(Node.nodes[i][j].getxPos() == xPos && Node.nodes[i][j].getyPos() == yPos){
                    this.setRow(Node.nodes[i][j].getRow());
                    this.setCol(Node.nodes[i][j].getCol());
                    break;
                }
            }
        }

        for(var waterResourceLocation : MainHandler.allObjectsList){
            if(waterResourceLocation.getName() != null && waterResourceLocation.getName().contains("Water-Resource")){
                waterResource = (WaterResource)waterResourceLocation;
            }

            if(waterResourceLocation.getName() != null && waterResourceLocation.getName().contains("Home-Base")){
                this.homeBase = (HomeBase) waterResourceLocation;
            }

            if(this.waterResource != null && this.homeBase != null){
                break;
            }
        }

        //Node.nodes[this.homeBase.getRow()][this.homeBase.getCol()].setNodeType(Node.NodeType.block);
    }

    @Override
    public void tick() {

        if(this.goalTarget != null){
            if(goalTarget.size() > 0 && tempIndex < goalTarget.size()){
                if(!goalReached){
                    if(goalTargetTemp == null){
                        var agoalTargetTemp = goalTarget.get(tempIndex);
                        goalTargetTemp = new Point(agoalTargetTemp.getxPos(), agoalTargetTemp.getyPos());
                    }

                }

            }
        }


        if(goalTargetTemp != null){
            if(Utils.IsTargetReached(new Point(this.getxPos(), this.getyPos()), goalTargetTemp) && !goalReached){
                goalTargetTemp = null;
                //goalReached = false;
                tempIndex++;

                if(tempIndex == goalTarget.size()){
                    tempIndex = 0;
                    goalTarget.clear();
                    waitTimeBeforeAction = System.currentTimeMillis();
                }

            }else {
                goalReached = false;
                move(goalTargetTemp);

                //setting up the hitbox
                this.getHitBox().x = (int)this.getxPos()-(1);
                this.getHitBox().y = (int)this.getyPos()-(1);
            }
        }


        //Resource Collect and Dump
        //1. Move to Resource Collection
        if(isOnTheWayToCollectResources){
            if(waterResource != null){
                //System.out.println(waterResource);
                goalTarget = aStarPathfinding.search(Node.nodes[this.getRow()][this.getCol()], Node.nodes[waterResource.getRow()-1][waterResource.getCol()]);
                isOnTheWayToCollectResources = false;
            }
        }else {
            if(MathHelper.isColliding(this.getHitBox(), waterResource.getHitBox()) && this.waterResourceCollected < this.waterResourcesMAX){
                isCollectingResources = true;
            }

        }

        //2. Collecting Resources
        if(isCollectingResources){

            if((this.waitTimeBeforeAction+100) < System.currentTimeMillis()){
//                this.waterResourceCollected += this.gatheringRate;
//                this.waterResourceCollected = MathHelper.ClampFloat(this.waterResourceCollected, this.waterResourcesMAX, 0.0f);
//                System.out.println("Water Collected : " + this.waterResourceCollected);

                if(this.waterResourceCollected < this.waterResourcesMAX){
                    this.waterResourceCollected += this.gatheringRate;
                    this.waterResourceCollected = MathHelper.ClampFloat(this.waterResourceCollected, this.waterResourcesMAX, 0.0f);
                    //System.out.println("Water Collected : " + this.waterResourceCollected);

                }else {
                    isCollectingResources = false;

                    if(!isOnTheWayToDumpResources){
                        isOnTheWayToDumpResources = true;
                    }

                }

                this.waitTimeBeforeAction = System.currentTimeMillis();
            }

        }


        //3. Move to Dump the resource
        if(isOnTheWayToDumpResources){
            if(this.homeBase != null){
                //System.out.println(waterResource);
                currentNode = Node.nodes[this.getRow()][this.getCol()];
                homeNode = Node.nodes[this.homeBase.getRow()][this.homeBase.getCol()+4];
                goalTarget = aStarPathfinding.search(currentNode,homeNode);
                isOnTheWayToDumpResources = false;
            }
        }
        else {
            if(MathHelper.isColliding(this.getHitBox(), this.homeBase.getHitBox()) && this.waterResourceCollected >= this.waterResourcesMAX){
                isDumpingResources = true;
            }
        }

        //4. Dumping Resources
        if(isDumpingResources){
            if((this.waitTimeBeforeAction+100) < System.currentTimeMillis()){
                if(this.waterResourceCollected > 0){
                    this.waterResourceCollected -= this.gatheringRate;
                    this.waterResourceCollected = MathHelper.ClampFloat(this.waterResourceCollected, this.waterResourcesMAX, 0.0f);
                }else{
                    ResourceManager.COLLECTED_WATER += this.waterResourcesMAX;
                    System.out.println("Water Collected : " + ResourceManager.COLLECTED_WATER);
                    isDumpingResources = false;

                    if(!isOnTheWayToCollectResources){
                        isOnTheWayToCollectResources = true;
                    }
                }
                this.waitTimeBeforeAction = System.currentTimeMillis();
            }
        }






    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());

        ////hitbox
        //g.setColor(Color.RED);
        //g.drawRect((int)this.getHitBox().getX(), (int)this.getHitBox().getY(), this.getHitBox().width, this.getHitBox().height);

//        g.setColor(Color.RED);
//        //Node.nodes[this.homeBase.getRow()][this.homeBase.getCol()].setNodeType(Node.NodeType.block);
//        //Node.nodes[this.homeBase.getRow()][this.homeBase.getCol()].setNodeType(Node.NodeType.block);
//        var node = Node.nodes[this.homeBase.getRow()][this.homeBase.getCol()+4];
//        g.drawRect(node.getxPos(), node.getyPos(), node.getSizeWidth(), node.getSizeHeight());
    }

    private void move(Point targetToMove){

        var xpos = this.getxPos();
        var ypos = this.getyPos();

//        xpos += this.getxVel();
//        ypos += this.getyVel();


        if(this.getxPos() < targetToMove.getX()){
            var xvel = this.getxVel();
            this.setxVel(xvel+this.acc);
            xpos += this.getxVel();
        }

        if(this.getxPos() > targetToMove.getX()){
            var xvel = this.getxVel();
            this.setxVel(xvel - this.dcc);
            xpos -= this.dcc;
        }

        if(this.getyPos() < targetToMove.getY()){
            var yvel = this.getyVel();
            this.setyVel(yvel+this.acc);
            ypos += this.getyVel();
        }

        if(this.getyPos() > targetToMove.getY()){
            var yvel = this.getyVel();
            this.setyVel(yvel-this.dcc);
            ypos -= this.dcc;
        }


        //setting max and minimum movement speed
        this.setxVel(MathHelper.ClampFloat(this.getxVel(), maxSpeed, minSpeed));
        this.setyVel(MathHelper.ClampFloat(this.getyVel(), maxSpeed, minSpeed));
        //velY = MathHelper.Clamp(velY, 5, -5);


        //setting up the hitbox
//        this.getHitBox().x = (int)this.getxPos()-(this.getSizeWidth());
//        this.getHitBox().y = (int)this.getyPos()-(this.getSizeHeight()/2);

        this.setxPos(xpos);
        this.setyPos(ypos);

        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                if(Node.nodes[i][j].getxPos() == this.getxPos() && Node.nodes[i][j].getyPos() == this.getyPos()){
                    this.setRow(Node.nodes[i][j].getRow());
                    this.setCol(Node.nodes[i][j].getCol());
                    break;
                }
            }
        }
    }



    public boolean isGoalReached() {
        return goalReached;
    }

    public void setGoalReached(boolean goalReached) {
        this.goalReached = goalReached;
    }


}
