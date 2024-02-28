package org.ashish.basebuild.objects;

import org.ashish.basebuild.model.GameObjectModelMain;

import java.awt.*;

public class TempObj extends GameObjectModelMain {
    public TempObj(int xPos, int yPos) {
        super(xPos, yPos);
        this.setSizeWidth(16);
        this.setSizeHeight(16);
        this.setColor(Color.CYAN);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
    }
}
