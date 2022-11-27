package fr.icom.info.m1.balleauprisonnier_mvn;
import java.util.ArrayList;

public class Team {
    public static final int MAXPLAYERS = 5;
    private int nombreDeJoueurActuel;
    private String name;
    ArrayList<Player> players;

    State state;

    public Team(String  name,State state){
        this.players = new ArrayList<>();
        this.name = name;
        this.nombreDeJoueurActuel = this.players.size();
        this.state = state;
    }

    public int getNombreDeJoueurActuel() {
        return this.nombreDeJoueurActuel;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public String getName(){
        return this.name;
    }
}
