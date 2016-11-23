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
    private List<Player> players;
    private GameBoard board;
    private int turn;
    private Map<String, Color> selectedColors;
    private WinListener winListener;

    public Game(int rows, int columns, WinListener winListener) {
        board = new GameBoard(rows, columns);
        players = new ArrayList<>();
        this.winListener=winListener;
    }

    public boolean addPlayer(Player player) {
        if (players.contains(player)) return false;
        return players.add(player);
    }

    @Deprecated
    public void createGameBoard(int rows, int columns) {
        board = new GameBoard(rows, columns);
    }

    public GameBoard getGameBoard() {
        return board;
    }

    public Player getCurrentPlayer(){
        return players.get(turn);
    }

    private void nextTurn() {
        turn = ++turn == players.size() ? 0 : turn;
        setChanged();
        notifyObservers();
    }

    public void playColumn(int column) {
        PlayResult result = board.playColumn(column, players.get(turn).getColor());
        switch (result) {
            case WIN:
                winListener.onPlayerWon();
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
