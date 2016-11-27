package com.jthure.connect4.model;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Represents a player that plays a game
 */

public class Player {
    private String name;
    private int nbrWonGames;
    private Color color;

    /**
     * Creates a new player with the specified name and the specified number of won games
     * @param name The name of the player
     * @param nbrWonGames The number of won games
     */
    public Player(@NonNull String name, int nbrWonGames){
        this.name=name;
        this.nbrWonGames=nbrWonGames;
    }
    /**
     * Creates a new player with the specified name and 0 won games
     * @param name The name of the player
     */
    public  Player(String name){
        this(name,0);
    }

    /**
     * Sets the color of this players checkers
     * @param color the color of the checker
     */
    public void setColor(Color color){
        this.color=color;
    }

    /**
     * Increments the number of games by 1 for this player
     */
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

    /**
     * Returns the color of this players checkers
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the name of this player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of won games by this player
     */
    public int getWins() {
        return nbrWonGames;
    }

    /**
     * Returns a comparator for players that compares by the name of the players.
     * @return The comparator
     */
    public static Comparator<Player> nameComparator(){
        return new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getName().compareTo(player2.getName());
            }
        };
    }
    /**
     * Returns a comparator for players that compares by the score of the players.
     * @return The comparator
     */
    public static Comparator<Player> scoreComparator(){
        return new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player2.nbrWonGames-player1.nbrWonGames;
            }
        };
    }
}
