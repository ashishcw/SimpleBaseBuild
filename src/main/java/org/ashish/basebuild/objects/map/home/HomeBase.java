package org.ashish.basebuild.objects.map.home;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;

import java.awt.*;

public class HomeBase extends GameObjectModelMain {
    public HomeBase(int xPos, int yPos) {
        super(xPos, yPos);
        this.setBaseObjectType(BaseObjectType.Base_Home);
        this.setColor(Color.WHITE);
        this.setSizeWidth(Constants.NODE_SIZE*4);
        this.setSizeHeight(Constants.NODE_SIZE*4);
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
