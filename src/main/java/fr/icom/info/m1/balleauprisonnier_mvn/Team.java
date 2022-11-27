package fr.icom.info.m1.balleauprisonnier_mvn;
import java.util.List;

public class Team {
    public static final int MAXPLAYERS = 5;
    private int nombreDeJoueurActuel;
    private String name;
    List<Player> players;

    State state;

    public Team(List<Player> pPlayers, String  name){
        this.players = pPlayers;
        this.name = name;
        this.nombreDeJoueurActuel = this.players.size();
    }


    public int getNombreDeJoueurActuel() {
        return this.nombreDeJoueurActuel;
    }

    public String getName(){
        return this.name;
    }
}
