package org.ashish.basebuild.objects.map.Resources;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.common.Node;
import org.ashish.basebuild.objects.map.constants.ResourceType;

import java.awt.*;

public class CoalResource extends GameObjectModelMain {
    private ResourceType.RawResourceType rawResourceType;
    public CoalResource(int xPos, int yPos, ResourceType.RawResourceType rawResourceTypeParam) {
        super(xPos, yPos);
        this.setBaseObjectType(BaseObjectType.Resources);
        this.setColor(Color.BLACK);
        this.setSizeWidth(Constants.NODE_SIZE*2);
        this.setSizeHeight(Constants.NODE_SIZE*2);
        this.rawResourceType = rawResourceTypeParam;
        this.setHitBox(new Rectangle(this.getxPos(), this.getyPos()-2, this.getSizeWidth(), this.getSizeHeight()));
        this.setName("Coal-Resource");

        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                if(Node.nodes[i][j].getxPos() == this.getxPos() && Node.nodes[i][j].getyPos() == this.getyPos()){
                    this.setRow(Node.nodes[i][j].getRow());
                    this.setCol(Node.nodes[i][j].getCol());
                }
            }
        }

        for(int i = this.getRow(); i < this.getRow()+2; i++){
            for(int j = this.getCol(); j < this.getCol()+2; j++){
                Node.nodes[i][j].setNodeType(Node.NodeType.block);
            }
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());

        ////hitbox
        //g.setColor(Color.RED);
        //g.drawRect((int)this.getHitBox().getX(), (int)this.getHitBox().getY(), this.getHitBox().width, this.getHitBox().height);
    }

    public ResourceType.RawResourceType getRawResourceType() {
        return rawResourceType;
    }

    public void setRawResourceType(ResourceType.RawResourceType rawResourceType) {
        this.rawResourceType = rawResourceType;
    }
}
