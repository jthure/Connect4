package com.jthure.connect4.model;

import java.util.Observable;

/**
 * Created by Jonas on 2016-11-21.
 */
public class Checker extends Observable {
    private Color color;

    public Checker(){
        color=Color.EMPTY;
    }

    public void setColor(Color color){
        this.color=color;
        setChanged();
        notifyObservers();
    }

    public Color getColor(){
        return color;
    }
    public boolean isEmpty(){
        return color==Color.EMPTY;
    }
}
