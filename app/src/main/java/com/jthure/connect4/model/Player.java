package com.jthure.connect4.model;

import android.support.annotation.NonNull;

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
}
