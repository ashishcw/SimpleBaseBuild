package org.ashish.basebuild.objects.map.home;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.common.Node;

import java.awt.*;

public class HomeBase extends GameObjectModelMain {
    public HomeBase(int xPos, int yPos) {
        super(xPos, yPos);

        this.setBaseObjectType(BaseObjectType.Base_Home);
        this.setColor(Color.WHITE);
        this.setSizeWidth(Constants.NODE_SIZE*4);
        this.setSizeHeight(Constants.NODE_SIZE*4);
        this.setHitBox(new Rectangle(this.getxPos(), this.getyPos()-2, this.getSizeWidth(), this.getSizeHeight()));
        this.setName("Home-Base");

        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                if(Node.nodes[i][j].getxPos() == this.getxPos() && Node.nodes[i][j].getyPos() == this.getyPos()){
                    this.setRow(Node.nodes[i][j].getRow());
                    this.setCol(Node.nodes[i][j].getCol());
                }
            }
        }

        for(int i = this.getRow(); i < this.getRow()+4; i++){
            for(int j = this.getCol(); j < this.getCol()+4; j++){
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
}
