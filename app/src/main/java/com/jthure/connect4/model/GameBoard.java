package com.jthure.connect4.model;

import android.util.Log;

import java.util.Observer;

/**
 * Created by Jonas on 2016-11-21.
 */

public class GameBoard {
    private Checker[][] board;

    public GameBoard(int rows, int columns) {
        board = new Checker[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = new Checker();
            }
        }
    }

    public Checker getChecker(int row, int col) {
        return board[row][col];
    }

    public PlayResult playColumn(int column, Color color) {
        int row = board.length - 1;
        while (!board[row][column].isEmpty()) {
            if (row == 0) return PlayResult.INVALID_PLAY;
            row--;
        }
        board[row][column].setColor(color);
        return checkIfWinningPlay(row, column) ? PlayResult.WIN : PlayResult.VALID_PLAY;
    }

    private boolean checkIfWinningPlay(int row, int column) {
        Color color = board[row][column].getColor();
        for (int yStep = -1; yStep <= 1; yStep++) {
            for (int xStep = -1; xStep <= 1; xStep++) {
                if(!(yStep==0&&xStep==0)) {
                    if (checkDirection(row, column, xStep, yStep, color)) return true;
                }
            }
        }
        return false;

    }

    private boolean checkDirection(int row, int column, int xStep, int yStep, Color color) {
        for (int count = 1; count <= 3; count++) {

            int nextRow=row + yStep*count;
            int nextColumn=column + xStep*count;

            if (nextRow >= 0 && nextRow < nbrRows() && nextColumn >= 0 && nextColumn < nbrColumns()) {
                if (!(board[nextRow][nextColumn].getColor() == color)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public int nbrRows() {
        return board.length;
    }

    public int nbrColumns() {
        return board[0].length;
    }

    public enum PlayResult {
        WIN, VALID_PLAY, INVALID_PLAY
    }
}

