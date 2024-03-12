package org.ashish.basebuild.handler.input.mouseinput;

import org.ashish.basebuild.Main;
import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.objects.NPCs.WaterGatherer;
import org.ashish.basebuild.objects.common.Node;
import org.ashish.basebuild.objects.map.constants.ResourceType;
import org.ashish.basebuild.pathfinding.AStarPathfinding;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    //WaterGatherer waterGatherer = new WaterGatherer(100, 100, ResourceType.RawResourceType.Water_Resource);

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

//        WaterGatherer waterGatherer = null;
//        for(var waterGathererItem : MainHandler.allObjectsList){
//            if(waterGathererItem.getName() != null){
//                if(waterGathererItem.getName().contains("Water-Resource-Gatherer")){
//                    waterGatherer = (WaterGatherer) waterGathererItem;
//                    break;
//                }
//            }
//
//        }
//
//
//
//        AStarPathfinding aStarPathfinding = new AStarPathfinding();
////        System.out.println("Water Row " + waterGatherer.getRow());
////        System.out.println("Water Col " + waterGatherer.getCol());
//        var allNodes = aStarPathfinding.search(Node.nodes[waterGatherer.getRow()][waterGatherer.getCol()], clickedNode);
////        for (var allTempNode:allNodes) {
////            allTempNode.setNodeType(Node.NodeType.path);
////            Node.nodes[allTempNode.getRow()][allTempNode.getCol()] = allTempNode;
////        }
//
//
//
//        if(waterGatherer != null){
//            //waterGatherer.setGoalTarget(new Point(clickedNode.getxPos(), clickedNode.getyPos()));
//            waterGatherer.goalTarget = allNodes;
//            for (var tempNode : allNodes) {
//                //waterGatherer.goalTarget.add(new Point(tempNode.getxPos(), tempNode.getyPos()));
////                waterGatherer.setGoalTarget(tempNode);
////                waterGatherer.setGoalReached(false);
//            }
//
//        }
//
//
//
////        if(!MainHandler.allObjectsList.contains(waterGatherer)){
////            MainHandler.addGameObjectToList(waterGatherer);
////        }



    }
}
