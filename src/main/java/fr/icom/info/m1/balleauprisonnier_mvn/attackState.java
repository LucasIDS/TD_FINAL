package fr.icom.info.m1.balleauprisonnier_mvn;

public class attackState implements State{

    Player player;


    public void setPlayer(Player player){
        this.player = player;
    }

    public void recieveBall(){
        System.out.printf("attak");
    }

}
