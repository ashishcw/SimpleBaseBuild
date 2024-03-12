package org.ashish.basebuild.objects.map.Path;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.common.Node;

import java.awt.*;

public class EnemyPathBase extends GameObjectModelMain {
    public EnemyPathBase(int xPos, int yPos) {
        super(xPos, yPos);
        this.setBaseObjectType(BaseObjectType.Enemy_Path);
        this.setColor(Color.BLACK);
        this.setSizeWidth(Constants.NODE_SIZE*1);
        this.setSizeHeight(Constants.NODE_SIZE*1);
        this.setHitBox(new Rectangle(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight()));

        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for(int j = 0; j < Constants.MAX_COLS; j++){
                if(Node.nodes[i][j].getxPos() == this.getxPos() && Node.nodes[i][j].getyPos() == this.getyPos()){
                    this.setRow(Node.nodes[i][j].getRow());
                    this.setCol(Node.nodes[i][j].getCol());
                    Node.nodes[i][j].setNodeType(Node.NodeType.block);
                }
            }
        }

//        for(int i = this.getRow(); i < this.getRow()+4; i++){
//            for(int j = this.getCol(); j < this.getCol()+4; j++){
//                Node.nodes[i][j].setNodeType(Node.NodeType.block);
//            }
//        }
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
