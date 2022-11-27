package fr.icom.info.m1.balleauprisonnier_mvn;

public class defenseState implements State{

    Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public void recieveBall(){
        System.out.printf("recieve with defense state");
    }

    public void shoot(){
        System.out.printf("shoot with defense state");
    }

}
