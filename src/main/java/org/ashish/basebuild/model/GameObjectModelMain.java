package org.ashish.basebuild.model;

import java.awt.*;
import java.util.UUID;

public abstract class GameObjectModelMain {
    private UUID id;
    private int xPos, yPos;
    private int sizeWidth, sizeHeight;
    private float xVel, yVel;
    private int row, col;
    private Color color;
    private String name;
    private boolean isActiveinScene = false;
    private boolean isVisible = false;
    private Rectangle HitBox;

    public enum BaseObjectType{
        Player,
        Enemy,
        Enemy_Path,
        Path,
        Resources,
        NPC_Dumper,
        NPC_Gatherer,
        Base_Home,
        None
    }

    private BaseObjectType baseObjectType;


    public GameObjectModelMain(int xPos, int yPos) {
        this.id = UUID.randomUUID();
        this.xPos = xPos;
        this.yPos = yPos;
        this.baseObjectType = BaseObjectType.None;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public UUID getId() {
        return id;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(int sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public int getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(int sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActiveinScene() {
        return isActiveinScene;
    }

    public void setActiveinScene(boolean activeinScene) {
        isActiveinScene = activeinScene;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Rectangle getHitBox() {
        return HitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        HitBox = hitBox;
    }

    public BaseObjectType getBaseObjectType() {
        return baseObjectType;
    }

    public void setBaseObjectType(BaseObjectType baseObjectType) {
        this.baseObjectType = baseObjectType;
    }
}
