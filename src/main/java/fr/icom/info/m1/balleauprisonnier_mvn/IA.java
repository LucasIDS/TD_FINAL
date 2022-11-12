package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class IA extends Player {

    IA(GraphicsContext gc, String color, int xInit, int yInit, String side) {
        super(gc, color, xInit, yInit, side);
        // TODO Auto-generated constructor stub

        Image tilesheetImage;
        if(side=="top"){
           tilesheetImage = new Image("assets/PlayerBlue.png");
        }
        else{
           tilesheetImage = new Image("assets/PlayerRed.png");
        }

        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
        sprite.setX(x);
        sprite.setY(y);

    }

}

