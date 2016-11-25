package com.jthure.connect4.model;

/**
 * Colors representing different types of checkers. A player will have one of these colors when
 * playing.
 */
public enum Color {
    YELLOW, RED, ORANGE,GREEN,PURPLE,
    /**
     * Used to represent the absence of a checker in the {@link GameBoard}
     */
    EMPTY
}
