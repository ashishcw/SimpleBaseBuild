package org.ashish.basebuild.objects.player;

import org.ashish.basebuild.constant.Constants;
import org.ashish.basebuild.handler.MainHandler;
import org.ashish.basebuild.handler.input.KeyboardInput;
import org.ashish.basebuild.model.GameObjectModelMain;
import org.ashish.basebuild.utils.MathHelper;

import java.awt.*;

public class Player extends GameObjectModelMain {

    private float acc = 1f;

    private float dcc = 0.5f;

    private KeyboardInput keyInputHandler;

    private GameObjectModelMain IronResourceObject, WoodResourceObject, WaterResourceObject;

    private MainHandler handler;

    public Player(int xPos, int yPos, KeyboardInput keyInputHandlerParam, MainHandler handlerParam) {
        super(xPos, yPos);
        this.keyInputHandler = keyInputHandlerParam;
        this.handler = handlerParam;
        this.setSizeWidth(Constants.NODE_SIZE);
        this.setSizeHeight(Constants.NODE_SIZE);
        this.setColor(Color.GREEN);
        this.setActiveinScene(true);
        this.setVisible(true);
        this.setBaseObjectType(BaseObjectType.Player);
        this.setHitBox(new Rectangle((int)this.getxPos(), (int)this.getyPos(), this.getSizeWidth()*2, this.getSizeHeight()*2));


    }

    @Override
    public void tick() {
        if(this.isActiveinScene()){
            move();
        }


    }

    @Override
    public void render(Graphics g) {

        if(this.isActiveinScene()){
            if(this.isVisible()){
                g.setColor(this.getColor());
                g.fillRect((int)this.getxPos(), (int)this.getyPos(), this.getSizeWidth(), this.getSizeHeight());

                ////hitbox
                //g.setColor(Color.RED);
                //g.drawRect(this.getHitBox().x, this.getHitBox().y, this.getHitBox().width, this.getHitBox().height);
            }
        }
    }

    private void move(){

        var xpos = this.getxPos();
        var ypos = this.getyPos();
        xpos += this.getxVel();
        ypos += this.getyVel();

        //Horizontal movement(A & D Key press)
        if(this.keyInputHandler.keys[0]){
            var xvel = this.getxVel();
            this.setxVel(xvel + this.acc);
        }
        else if(this.keyInputHandler.keys[1]){
            var xvel = this.getxVel();
            this.setxVel(xvel - this.acc);
        }else if(!this.keyInputHandler.keys[1] && !this.keyInputHandler.keys[0]){
            if(this.getxVel() > 0){
                var xvel = this.getxVel();
                this.setxVel(xvel - this.dcc);
                //this.velX -= this.dcc;
            } else if (this.getxVel() < 0) {
                //this.velX += this.dcc;
                var xvel = this.getxVel();
                this.setxVel(xvel + this.dcc);
            }
        }



        //Horizontal movement(W & S Key press)
        if(this.keyInputHandler.keys[3]){
            var yvel = this.getyVel();
            this.setyVel(yvel + this.acc);
        }
        else if(this.keyInputHandler.keys[2]){
            var yvel = this.getyVel();
            this.setyVel(yvel - this.acc);
        }else if(!this.keyInputHandler.keys[2] && !this.keyInputHandler.keys[3]){
            if(this.getyVel() > 0){
                var yvel = this.getyVel();
                this.setyVel(yvel - this.dcc);
                //this.velX -= this.dcc;
            } else if (this.getyVel() < 0) {
                //this.velX += this.dcc;
                var yvel = this.getyVel();
                this.setyVel(yvel + this.dcc);
            }
        }

        //setting max and minimum movement speed
        this.setxVel(MathHelper.Clamp((int)this.getxVel(), 5, -5));
        this.setyVel(MathHelper.Clamp((int)this.getyVel(), 5, -5));
        //velY = MathHelper.Clamp(velY, 5, -5);


        //setting up the hitbox
        this.getHitBox().x = (int)this.getxPos()-(this.getSizeWidth()/2);
        this.getHitBox().y = (int)this.getyPos()-(this.getSizeHeight()/2);

        this.setxPos(xpos);
        this.setyPos(ypos);
    }
}