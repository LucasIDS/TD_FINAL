package fr.icom.info.m1.balleauprisonnier_mvn;

public interface State {

    public void recieveBall(Player player,Ball myBall,Team team2);

    public void shoot(Player player);

    public void changeState(Ball myBall);

    void setTeam(Team team);

}
