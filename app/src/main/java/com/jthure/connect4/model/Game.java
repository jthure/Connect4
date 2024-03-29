package com.jthure.connect4.model;

import java.util.Observable;

import com.jthure.connect4.model.GameBoard.PlayResult;
import com.jthure.connect4.util.IO;

/**
 * Represents a game that is being played by several {@link Player}s on a {@link GameBoard}
 */

public class Game extends Observable {
    private Player[] players;
    private GameBoard board;
    private int turn;
    private Checker turnChecker;
    private FinishListener finishListener;
    private IO io;
    private boolean finished;

    /**
     * Creates a new game with specified attributes
     * @param rows the number of rows in the {@link GameBoard}
     * @param columns the number of columns in the {@link GameBoard}
     * @param playerNames the players
     * @param io The IO handler used to write persistent data
     */
    public Game(int rows, int columns,Player[]playerNames,IO io) {
        board = new GameBoard(rows, columns);
        players = playerNames;
        turnChecker=new Checker();
        turnChecker.setColor(players[0].getColor());
        this.io=io;
        String s = "New game created. Size: "+rows+" X "+columns+" Players:";
        for(Player p : playerNames){
            s=s+" Name: "+p.getName()+" Score: "+p.getWins();
        }
        io.log(s);
    }

    /**
     * Sets the {@link FinishListener}
     * @param finishListener
     */
    public void setFinishListener(FinishListener finishListener){
        this.finishListener = finishListener;
    }

    /**
     * Returns the {@link GameBoard} of this Game
     * @return the {@link GameBoard} of this Game
     */
    public GameBoard getGameBoard() {
        return board;
    }

    /**
     * Returns the player that are next in line to make a play.
     * @return the player that are next in line to make a play.
     */
    public Player getCurrentPlayer(){
        return players[turn];
    }

    /**
     * Returns a checker with the color of the player that are next in line to make a play.
     * @return a checker with the color of the player that are next in line to make a play.
     */
    public Checker getTurnChecker(){return turnChecker;}

    /**
     * Advance turn counter
     */
    private void nextTurn() {
        turn = ++turn == players.length ? 0 : turn;
        turnChecker.setColor(players[turn].getColor());
        setChanged();
        notifyObservers();
    }

    /**
     * Drops a checker at the specified column. The play could result in three possible {@link PlayResult}s
     * @param column the column to play the checker at
     */
    public void playColumn(int column) {
        if(finished) return;
        PlayResult result = board.playColumn(column, players[turn].getColor());
        switch (result) {
            case WIN:
                finished=true;
                finishListener.onGameFinished(PlayResult.WIN);
                players[turn].incrementNbrWonGames();
                io.log("Player "+players[turn].getName()+" won a game");
                io.writePlayer(players[turn]);
                break;
            case TIE:
                finished=true;
                finishListener.onGameFinished(PlayResult.TIE);
                io.log("Game was a tie");
                break;
            case VALID_PLAY:
                nextTurn();
                break;
            case INVALID_PLAY:
                break;
        }
    }

    /**
     * Listener interface to nofify the listener when a player has won the game.
     */
    public interface FinishListener {
        /**
         * Notifies the listener that a player has won the game.
         */
        void onGameFinished(PlayResult result);
    }

    /**
     * Restarts the game by resetting the {@link GameBoard}
     */
    public void restart(){
        board.reset();
        nextTurn();
        finished=false;
        io.log("Game reset");
    }
}
