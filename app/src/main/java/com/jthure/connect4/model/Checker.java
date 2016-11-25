package com.jthure.connect4.model;

import java.util.Observable;

/**
 * Represents the physical checker used when playing the game. The only attribute is a {@link Color}
 * and the changong of the color is observable.
 */
public class Checker extends Observable {
    private Color color;

    /**
     * Creates a new checker with the color {@link Color#EMPTY}
     */
    public Checker(){
        color=Color.EMPTY;
    }

    /**
     * Sets the color of this checker
     * @param color the new color
     */
    public void setColor(Color color){
        this.color=color;
        setChanged();
        notifyObservers();
    }

    /**
     * Get the color of this checker
     * @return the color of this checker
     */
    public Color getColor(){
        return color;
    }

    /**
     * Checks wether this checker's color is {@link Color#EMPTY} or not.
     * @return true if this checker's color is {@link Color#EMPTY}, false otherwise
     */
    public boolean isEmpty(){
        return color==Color.EMPTY;
    }
}
