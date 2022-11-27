package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.image.Image;
public class Ball{
    private static Ball theBall = null;
    double x;
    double y;
   private double velocityX;
   private  double velocityY;
   Image imgBall;

    public static synchronized Ball getInstance(){
        if(theBall == null){
            theBall = new Ball(0,0,0,0);
        }
        return theBall;
    }

    private Ball(double pPositionX, double pPositionY, double pVelocityX,  double pVelocityY){
        this.x = pPositionX;
        this.y = pPositionY;
        this.velocityX = pVelocityX;
        this.velocityY = pVelocityY;
        this.imgBall = new Image("assets/ball.png",25,25,true,false);
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



