package fr.icom.info.m1.balleauprisonnier_mvn;

public class defenseState implements State{

    Team team;

    public defenseState(Team team){
        this.team = team;
    }

    public defenseState(){
    }

    public void setTeam(Team team){
        this.team = team;
    }


    public void recieveBall(Player player,Ball myBall,Team team2){
        player.lostVie(1);
        this.team.changeState(new attackState(this.team));
        player.initBall(myBall);
        team2.state.changeState(myBall);
    }

    public void changeState(Ball myBall){
        this.team.changeState(new attackState(this.team));
        this.team.players.get(0).initBall(myBall);
    }

    public void shoot(Player player){
        System.out.printf("i cant shoot");
    }

}
