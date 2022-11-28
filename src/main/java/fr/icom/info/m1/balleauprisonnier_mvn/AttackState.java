package fr.icom.info.m1.balleauprisonnier_mvn;

public class AttackState implements State{

    Team team;

    public AttackState(Team team) {
        this.team = team;
    }

    public AttackState() {
        this.team = null;
    }


    public void recieveBall(Player player,Ball myBall,Team team2){
        if (player.team.players.get(0) != player){
            player.initBall(myBall);
        }
    }

    public void setTeam(Team team){
        this.team = team;
    }


    public void changeState(Ball myBall){
        this.team.changeState(new DefenseState(this.team));
    }


    public void shoot(Player player){
        player.sprite.playShoot();
        if (player.myBall != null && player.team.getName().equals("bottom")){
            player.myBall.setVelocityY(-Math.sin(Math.toRadians(90-player.getAngle())));
            player.myBall.setVelocityX(+Math.cos(Math.toRadians(90-player.getAngle())));
        }else if (player.myBall != null && player.team.getName().equals("top")){
            player.myBall.setVelocityY(+Math.sin(Math.toRadians(90-player.getAngle())));
            player.myBall.setVelocityX(-Math.cos(Math.toRadians(90-player.getAngle())));
        }
        player.myBall = null;
    }
}
