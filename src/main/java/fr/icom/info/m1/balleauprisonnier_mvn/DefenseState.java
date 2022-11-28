package fr.icom.info.m1.balleauprisonnier_mvn;

public class DefenseState implements State{

    Team team;

    public DefenseState(Team team){
        this.team = team;
    }

    public DefenseState(){
    }

    public void setTeam(Team team){
        this.team = team;
    }

    public void recieveBall(Player player,Ball myBall,Team team2){
        player.lostVie(1);
        if(player.vie > 0){
            this.team.changeState(new AttackState(this.team));
            player.initBall(myBall);
        }
        else {
            this.changeState(myBall);
        }
        team2.state.changeState(myBall);
    }

    public void changeState(Ball myBall){
        this.team.changeState(new AttackState(this.team));
        this.team.players.get(0).initBall(myBall);
    }

    public void shoot(Player player){
        System.out.printf("i cant shoot");
    }

}
