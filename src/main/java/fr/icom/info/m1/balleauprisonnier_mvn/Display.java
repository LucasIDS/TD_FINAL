package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.awt.*;
import java.util.ArrayList;


public class Display{
    private static Display afficheur = null;
    ArrayList<LabelGestion> labels;
    ArrayList<Button> buttons;
    private Display(){
        labels = new ArrayList<>();
        buttons = new ArrayList<>();
    }


    public static synchronized Display getInstance(){
        if(afficheur == null){
            afficheur = new Display();
        }
        return afficheur;
    }

    public void displayImg(GraphicsContext gc, Image uneImage, double x, double y ){
        gc.save(); // saves the current state on stack, including the current transform
        gc.drawImage(uneImage,x,y);
    }


    public void displayPlayer(GraphicsContext gc, ArrayList<Player> team1, ArrayList<Player> team2, int indiceTeam1, int indiceTeam2 ){
        this.displayOne(gc,team1.get(indiceTeam1));
        this.displayOne(gc,team2.get(indiceTeam2));
    }

    private void displayOne(GraphicsContext gc, Player player){
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, player.getAngle(), player.x + player.directionArrow.getWidth() / 2, player.y + player.directionArrow.getHeight() / 2);
        gc.drawImage( player.directionArrow, player.x, player.y );
        gc.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }


    void displayVie(){
        for (LabelGestion label:this.labels){
            label.setLabelXY();
            label.setText();
        }
    }

    void initLabel(ArrayList<Player>team1,ArrayList<Player>team2){
        for (Player joueur:team1){
            this.labels.add(new LabelGestion(joueur));
        }
        for (Player joueur:team2){
            this.labels.add(new LabelGestion(joueur));
        }
    }

    public void initButton() {
        javafx.scene.control.Button button1 = new Button("Strategie 1 ");
        javafx.scene.control.Button button2 = new Button("Strategie 2");
        button2.setLayoutX(600);
        button1.setLayoutX(600);
        button1.setLayoutY(50);
        buttons.add(button1);
        buttons.add(button2);
    }
}