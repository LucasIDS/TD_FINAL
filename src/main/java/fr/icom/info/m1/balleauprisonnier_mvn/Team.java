package fr.icom.info.m1.balleauprisonnier_mvn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {
    public static final int MAXPLAYERS = 5;
    private int nombreDeJoueurActuel;
    private String name;
    ArrayList<Player> players;

    State state;

    public Team(String  name){
        this.players = new ArrayList<>();
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
