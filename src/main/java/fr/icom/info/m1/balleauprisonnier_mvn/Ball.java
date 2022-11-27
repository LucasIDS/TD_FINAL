package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball  extends ImageView{
    double x;
    double y;
   private double velocityX;
   private  double velocityY;
    ImageView ivBall;
    Image imgBall;
    GraphicsContext graphicsContext;

    public Ball(GraphicsContext gc ,double pPositionX, double pPositionY, double pVelocityX,  double pVelocityY) {
        this.x = pPositionX;
        this.y = pPositionY;

        this.velocityX = pVelocityX;
        this.velocityY = pVelocityY;

        this.graphicsContext = gc;

        this.imgBall = new Image("assets/ball.png",25,25,true,false);
        this.ivBall = new ImageView(imgBall);
    }

    void display()
    {
        this.graphicsContext.save(); // saves the current state on stack, including the current transform
        this.graphicsContext.drawImage(imgBall, this.x, this.y);
    }

    boolean collisionWithPlayer(Player player){
        boolean haveCollision = false;
        if(this.velocityY != 0){
            haveCollision = player.lostVie(1);
        }
        return haveCollision;
    }

    void deplacementBall()
    {
        this.x +=  3*velocityX ;
        this.y +=  3*velocityY;

    }

    void setVelocityY(double pVelocityY){
        this.velocityY = pVelocityY;
    }
    void setVelocityX(double pVelocityX){
        this.velocityX = pVelocityX;
    }

    public void bounceX(){
        this.velocityX = -this.velocityX;
    }
}



