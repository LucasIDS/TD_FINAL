package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball  extends ImageView{

    Sprite sprite;


    double angle;
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

        this.imgBall = new Image("assets/ball.png");
        this.ivBall = new ImageView();
        this.ivBall.setImage(imgBall);
        this.ivBall.setFitWidth(1);
        this.ivBall.setPreserveRatio(true);
        this.ivBall.setSmooth(true);
        this.ivBall.setCache(true);


    }

    void display()
    {
        this.graphicsContext.save(); // saves the current state on stack, including the current transform
        this.graphicsContext.drawImage(imgBall, this.x, this.y);
    }


    void deplacementBall()
    {
        this.x +=  velocityX ;
        this.y +=  velocityY;
    }

    void setY(int y){
        this.y = y;
    }
    void setX(int x){
        this.x = x;
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

    public void bounceY(){
        this.velocityY = -this.velocityY;
    }
}
