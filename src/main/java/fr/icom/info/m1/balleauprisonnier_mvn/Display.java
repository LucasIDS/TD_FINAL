package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

    public class Display {
    private static Display theDisplayer = null;
    private Display(){
    }

    public static synchronized Display getInstance(){
        if(theDisplayer == null){
            theDisplayer = new Display();
        }
        return theDisplayer;
    }

    public boolean display(GraphicsContext gc,Image uneImage,double x, double y ){
        boolean canDisplay = true;
        gc.save(); // saves the current state on stack, including the current transform
        gc.drawImage(uneImage,x,y);
        return canDisplay;
    }

}
