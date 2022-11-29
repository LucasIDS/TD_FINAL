package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.image.Image;
public class Ball{
    private static Ball theBall = null;
   double x;
   double y;
   private double velocityX;
   private  double velocityY;
   private Image imgBall;

   private double boost = 1;
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
        this.imgBall = new Image("assets/Ball.png",25,25,true,false);
    }

    void collisionWithPlayer(Player player,Team team){
        if(this.velocityY != 0){
            player.recieveBall(this,team);
        }
    }

    double getX(){
        return this.x;
    }

    double getY(){
        return this.y;
    }

    Image getImgBall(){
        return this.imgBall;
    }

    void deplacementBall()
    {
        this.boostBall();
        this.x +=  3*velocityX*boost ;
        this.y +=  3*velocityY*boost;

    }
    void boostBall(){
        if (boost<5){
            boost+=0.001;
        }
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



