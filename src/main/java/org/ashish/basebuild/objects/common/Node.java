package org.ashish.basebuild.objects.common;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;

import java.awt.*;

public class Node extends GameObjectModelMain {

    public static Node[][] nodes = new Node[Constants.MAX_ROWS][Constants.MAX_COLS];

    public enum NodeType{
        start,
        end,
        path,
        block,
        none
    }
    private NodeType nodeType;

    public Node(int xPos, int yPos) {
        super(xPos, yPos);
        this.setNodeType(NodeType.none);
        this.setSizeWidth(Constants.NODE_SIZE);
        this.setSizeHeight(Constants.NODE_SIZE);
        this.setColor(Color.BLACK);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        nodeColorScheme();
        g.setColor(this.getColor());
        if(
                this.getNodeType() == NodeType.start
                ||
                this.getNodeType() == NodeType.end
                ||
                this.getNodeType() == NodeType.block
        ){
            g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
        }else {
            g.drawRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
        }

    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    private void nodeColorScheme(){
        switch (this.getNodeType()){
            case block -> this.setColor(Color.RED);
            case path -> this.setColor(Color.BLUE);
            case none -> this.setColor(Color.BLACK);
            case start -> this.setColor(Color.YELLOW);
            case end -> this.setColor(Color.GREEN);
        }
    }

    public static void createNodeGrid(){
        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                var tempNewNode = new Node(j*Constants.NODE_OFFSET, i*Constants.NODE_OFFSET);
                tempNewNode.setRow(i);
                tempNewNode.setCol(j);
                nodes[i][j] = tempNewNode;
            }
        }
    }

    public static Node getaClickedNode(Point clickedPosition){
        Node returnableNode = null;
        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                if(
                        (clickedPosition.getX() >= nodes[i][j].getxPos() && clickedPosition.getX() <= (nodes[i][j].getxPos()+Constants.NODE_SIZE))
                        &&
                        (clickedPosition.getY() >= nodes[i][j].getyPos() && clickedPosition.getY() <= (nodes[i][j].getyPos()+Constants.NODE_SIZE))
                ){
                    return returnableNode = nodes[i][j];
                }
            }
        }
        return returnableNode;
    }
}
