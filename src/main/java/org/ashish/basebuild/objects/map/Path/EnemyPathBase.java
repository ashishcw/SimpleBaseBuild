package org.ashish.basebuild.objects.map.Path;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;

import java.awt.*;

public class EnemyPathBase extends GameObjectModelMain {
    public EnemyPathBase(int xPos, int yPos) {
        super(xPos, yPos);
        this.setBaseObjectType(BaseObjectType.Enemy_Path);
        this.setColor(Color.BLACK);
        this.setSizeWidth(Constants.NODE_SIZE*1);
        this.setSizeHeight(Constants.NODE_SIZE*1);
        this.setHitBox(new Rectangle(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight()));
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
