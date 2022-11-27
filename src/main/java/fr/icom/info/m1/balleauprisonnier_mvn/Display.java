package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;



public class Display {
    private static Display afficheur = null;
    private Display(){
    }

    public static synchronized Display getInstance(){
        if(afficheur == null){
            afficheur = new Display();
        }
        return afficheur;
    }

    public void displayBall(GraphicsContext gc, Image uneImage, double x, double y ){
        gc.save(); // saves the current state on stack, including the current transform
        gc.drawImage(uneImage,x,y);
    }

    public void displayPlayer(GraphicsContext gc, Player[] team1, Player[] team2, int indiceTeam1, int indiceTeam2 ){
        this.displayOne(gc,team1[indiceTeam1]);
        this.displayOne(gc,team2[indiceTeam2]);
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

}