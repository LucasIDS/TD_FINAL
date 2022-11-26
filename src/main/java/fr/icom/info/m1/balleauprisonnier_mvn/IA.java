package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class IA extends Player {

    IA(GraphicsContext gc, String color, int xInit, int yInit, String side) {
        super(gc, color, xInit, yInit, side);

        Image tilesheetImage;
        if(Objects.equals(side, "top")){
           tilesheetImage = new Image("assets/PlayerBlue.png");
        }
        else{
           tilesheetImage = new Image("assets/PlayerRed.png");
        }

        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
        sprite.setX(x);
        sprite.setY(y);

    }

    private void changementDirectionBot(int w5g,int w5d, int feinte){




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

    void deplacement(int w5g,int w5d, int feinte){
        this.changementDirectionBot(w5g, w5d, feinte);
        this.x += this.step * this.vitesse;
        spriteAnimate();

    }

}

