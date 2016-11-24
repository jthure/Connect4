package com.jthure.connect4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.jthure.connect4.model.GameBoard.PlayResult;

/**
 * Created by Jonas on 2016-11-23.
 */

public class Game extends Observable {
    private Player[] players;
    private GameBoard board;
    private int turn;
    private Checker turnChecker;
    private WinListener winListener;
    private IO io;

    public Game(int rows, int columns,Player[]playerNames, WinListener winListener,IO io) {
        board = new GameBoard(rows, columns);
        players = playerNames;
        this.winListener=winListener;
        turnChecker=new Checker();
        this.io=io;
    }

    public GameBoard getGameBoard() {
        return board;
    }

    public Player getCurrentPlayer(){
        return players[turn];
    }

    public Checker getTurnChecker(){return turnChecker;}

    private void nextTurn() {
        turn = ++turn == players.length ? 0 : turn;
        turnChecker.setColor(players[turn].getColor());
        setChanged();
        notifyObservers();
    }

    public void playColumn(int column) {
        PlayResult result = board.playColumn(column, players[turn].getColor());
        switch (result) {
            case WIN:
                winListener.onPlayerWon();
                players[turn].incrementNbrWonGames();
                io.writePlayer(players[turn]);
                break;
            case VALID_PLAY:
                nextTurn();
                break;
            case INVALID_PLAY:
                break;
        }
    }
    public interface WinListener{
        void onPlayerWon();
    }
}
