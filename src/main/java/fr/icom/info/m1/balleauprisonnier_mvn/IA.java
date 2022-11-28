package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class IA extends Player {
    Image tilesheetImage;
    private double xMax;
    private double xMin;
    IA(GraphicsContext gc, String color, double xInit, double yInit, Ball myBall,int pVie,Team team, double xMax, double xMin) {
        super(gc, color, xInit, yInit, myBall,pVie,team);

        this.vie = pVie;
        this.xMax = xMax;
        this.xMin = xMin;

        if(Objects.equals(this.team.getName(), "top")){
            tilesheetImage = new Image("assets/PlayerBlue.png");
        }
        else{
            tilesheetImage = new Image("assets/PlayerRed.png");
        }
        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), this.team.getName());
        sprite.setX(getX());
        sprite.setY(getY());
        this.vitesse = 1;
    }

    private void changementDirectionBot(double w5g,double w5d, int feinte){
        if (this.x > w5d || this.x < w5g){
            this.vitesse = -this.vitesse;
        }
        //Gestion de la feinte
        if (feinte != 0 && this.x < w5d && this.x > w5g)  {
            Random randomGenerator = new Random();
            feinteMax = randomGenerator.nextInt(feinte);
            if (feinteMax==2){
                this.vitesse = -this.vitesse;
            }
        }
    }

    @Override
    void deplacement(int feinte){
        this.changementDirectionBot(this.xMin, this.xMax, feinte);
        this.x += this.step * this.vitesse;
        spriteAnimate();
    }

    void suivit(){
        double difference = this.team.players.get(0).xInit - this.xInit;
        if (this.vie!=0) {
            this.x = this.team.players.get(0).x - difference;
            spriteAnimate();
        }
    }


    double getxMax(){
        return this.xMax;
    }
    double getxMin(){
        return this.xMin;
    }
}

