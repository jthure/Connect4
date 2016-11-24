package com.jthure.connect4.model;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by Jonas on 2016-11-23.
 */

public class Player {
    private String name;
    private int nbrWonGames;
    private Color color;

    public Player(@NonNull String name, int nbrWonGames){
        this.name=name;
        this.nbrWonGames=nbrWonGames;
    }
    public  Player(String name){
        this(name,0);
    }
    
    public void setColor(Color color){
        this.color=color;
    }
    public void incrementNbrWonGames(){nbrWonGames++;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name.equals(player.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    public int getWins() {
        return nbrWonGames;
    }

    public static Comparator<Player> nameComparator(){
        return new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getName().compareTo(player2.getName());
            }
        };
    }
    public static Comparator<Player> scoreComparator(){
        return new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player2.nbrWonGames-player1.nbrWonGames;
            }
        };
    }
}
