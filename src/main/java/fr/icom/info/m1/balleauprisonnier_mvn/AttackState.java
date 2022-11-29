package fr.icom.info.m1.balleauprisonnier_mvn;

public class AttackState implements State{

    Team team;
    double wait = 0;
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
        // Partie BOT
        if (player.team.players.get(0)!=player){
            if(player.myBall!=null){
                wait+=1;
            }
            if(player.myBall!=null && wait>=50){
                player.setAngle(20 + (Math.random() * (-100 - 20)));
                if (player.myBall != null && player.team.getName().equals("bottom")){
                    player.sprite.playShoot();
                    player.myBall.setVelocityY(-Math.sin(Math.toRadians(90-player.getAngle())));
                    player.myBall.setVelocityX(+Math.cos(Math.toRadians(90-player.getAngle())));
                }else if (player.myBall != null && player.team.getName().equals("top")){
                    player.sprite.playShoot();
                    player.myBall.setVelocityY(+Math.sin(Math.toRadians(90-player.getAngle())));
                    player.myBall.setVelocityX(-Math.cos(Math.toRadians(90-player.getAngle())));
                }
                player.myBall = null;
                wait = 0;

            }
        }
        // Partie joueur
        else if (player.myBall != null && player.team.getName().equals("bottom")){
            player.sprite.playShoot();
            player.myBall.setVelocityY(-Math.sin(Math.toRadians(90-player.getAngle())));
            player.myBall.setVelocityX(+Math.cos(Math.toRadians(90-player.getAngle())));
            player.myBall = null;
        }else if (player.myBall != null && player.team.getName().equals("top")){
            player.sprite.playShoot();
            player.myBall.setVelocityY(+Math.sin(Math.toRadians(90-player.getAngle())));
            player.myBall.setVelocityX(-Math.cos(Math.toRadians(90-player.getAngle())));
            player.myBall = null;
        }

    }
}
