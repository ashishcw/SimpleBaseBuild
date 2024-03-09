package org.ashish.basebuild.objects.map.Resources;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.objects.map.constants.ResourceType;

import java.awt.*;

public class WaterResource extends GameObjectModelMain {
    private ResourceType.RawResourceType rawResourceType;
    public WaterResource(int xPos, int yPos, ResourceType.RawResourceType rawResourceTypeParam) {
        super(xPos, yPos);
        this.setBaseObjectType(BaseObjectType.Resources);
        this.setColor(Color.BLUE);
        this.setSizeWidth(Constants.NODE_SIZE*2);
        this.setSizeHeight(Constants.NODE_SIZE*2);
        this.rawResourceType = rawResourceTypeParam;
        this.setHitBox(new Rectangle(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight()));
        this.setName("Water-Resource");
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
