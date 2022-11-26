package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball  extends ImageView{

    Sprite sprite;
    int velocityX;
    int velocityY;

    ImageView iv;

    Image img;

    GraphicsContext graphicsContext;

    public Ball(GraphicsContext gc ,int pPositionX, int pPositionY, int pVelocityX,  int pVelocityY) {
        this.velocityX = pVelocityX;
        this.velocityY = pVelocityY;
        this.setY(pPositionY);
        this.setX(pPositionX);
        img = new Image("assets/ball.png",30,30,true,false);

    }


    ImageView getImageView()
    {
        ImageView iv = new ImageView();
        iv.setImage(img);
        return iv;
    }


    public void bounceX(){
        this.velocityX = -this.velocityX;
    }

    public void bounceY(){
        this.velocityY = -this.velocityY;
    }
}
