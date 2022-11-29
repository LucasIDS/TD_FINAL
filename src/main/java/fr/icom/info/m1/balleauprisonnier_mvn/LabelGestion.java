package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class LabelGestion extends Label{
    Player player;
    Image img = new Image("assets/coeur.png",15,15,true,false);
    ImageView view = new ImageView(img);

    public LabelGestion(Player player){
        super();
        this.player=player;
        this.setGraphic(view);
    }

    void setLabelXY(){
        this.setTranslateX(player.x+25);
        if (player.team.getName() == "top"){
            this.setTranslateY(player.y-10);
        }
        else if(player.team.getName() == "bottom") {
            this.setTranslateY(player.y+65);
        }
    }
    void setText(){
        this.setText("" +player.vie);
    }
}
