package com.jthure.connect4.model;

/**
 * Represents the the game board which a {@link Game} is played on
 */

public class GameBoard {
    private Checker[][] board;

    /**
     *
     * @param rows
     * @param columns
     */
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
        if(checkIfTie()) return PlayResult.TIE;
        return checkIfWinningPlay(row, column) ? PlayResult.WIN : PlayResult.VALID_PLAY;
    }

    private boolean checkIfWinningPlay(int row, int column) {
        Color color = board[row][column].getColor();
        return 1+ countDirection(row, column, 1, 0, color)+ countDirection(row, column, -1, 0, color)>=4||
                1+ countDirection(row, column, 0, 1, color)+ countDirection(row, column, 0, -1, color)>=4||
                1+ countDirection(row, column, 1, 1, color)+ countDirection(row, column, -1, -1, color)>=4||
                1+ countDirection(row, column, 1, -1, color)+ countDirection(row, column, -1, 1, color)>=4;
    }

    private int countDirection(int row, int column, int xStep, int yStep, Color color) {
        int step=1;
        int nextRow=row + yStep*step;
        int nextColumn=column + xStep*step;
        while((nextRow >= 0 && nextRow < nbrRows() && nextColumn >= 0 && nextColumn < nbrColumns()) &&
                (board[nextRow][nextColumn].getColor() == color))
        {
            step++;
            nextRow=row + yStep*step;
            nextColumn=column + xStep*step;
        }
        return step-1;
    }

    private boolean checkIfTie(){
        for(Checker c:board[0]){
            if(c.getColor()==Color.EMPTY)return false;
        }
        return true;
    }

    public void reset(){
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].setColor(Color.EMPTY);
            }
        }
    }

    public int nbrRows() {
        return board.length;
    }

    public int nbrColumns() {
        return board[0].length;
    }

    public enum PlayResult {
        WIN, VALID_PLAY, TIE, INVALID_PLAY
    }
}

