package com.jthure.connect4.model;

/**
 * Represents the the game board which a {@link Game} is played on
 */

public class GameBoard {
    private Checker[][] board;

    /**
     * Creates a new gameboard
     *
     * @param rows    the number of rows
     * @param columns the number of columns
     */
    public GameBoard(int rows, int columns) {
        board = new Checker[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = new Checker();
            }
        }
    }

    /**
     * Returns the checker at row, col
     */
    public Checker getChecker(int row, int col) {
        return board[row][col];
    }

    /**
     * Places a checker at the specified column. The play results in a {@link PlayResult} depending
     * on the outcome of the play
     *
     * @param column the column to place the checker
     * @param color  the color of the checker
     * @return the result of the play
     */
    public PlayResult playColumn(int column, Color color) {
        int row = board.length - 1;
        //Traverse the column from the button to find the first available space, or determine the
        // column is full
        while (!board[row][column].isEmpty()) {
            if (row == 0) return PlayResult.INVALID_PLAY;
            row--;
        }
        board[row][column].setColor(color); //Place the checker at row, column

        //Determine the result of the play
        return checkIfWinningPlay(row, column) ? PlayResult.WIN : (checkIfTie() ? PlayResult.TIE : PlayResult.VALID_PLAY);
    }

    /**
     * Counts the number of equally colored checkers that are connected in a row relative to the
     * that was played to see if there exists a row of four equally colored checkers. Four
     * directions need to be checked.
     */
    private boolean checkIfWinningPlay(int row, int column) {
        Color color = board[row][column].getColor();
        return 1 + countDirection(row, column, 1, 0, color) + countDirection(row, column, -1, 0, color) >= 4 ||
                1 + countDirection(row, column, 0, 1, color) + countDirection(row, column, 0, -1, color) >= 4 ||
                1 + countDirection(row, column, 1, 1, color) + countDirection(row, column, -1, -1, color) >= 4 ||
                1 + countDirection(row, column, 1, -1, color) + countDirection(row, column, -1, 1, color) >= 4;
    }

    /**
     * Counts the number of equally colored checkers that are connected from th checker at row,column
     * in the direction specified by xStep, yStep.
     *
     * @param xStep may be -1,0 or 1
     * @param yStep may be -1,0 or 1
     */
    private int countDirection(int row, int column, int xStep, int yStep, Color color) {
        int step = 1;
        int nextRow = row + yStep * step;
        int nextColumn = column + xStep * step;
        while ((nextRow >= 0 && nextRow < nbrRows() && nextColumn >= 0 && nextColumn < nbrColumns()) &&
                (board[nextRow][nextColumn].getColor() == color)) {
            step++;
            nextRow = row + yStep * step;
            nextColumn = column + xStep * step;
        }
        return step - 1;
    }

    /**
     * If all the checkers at the top row is colored then the game is a tie.
     */
    private boolean checkIfTie() {
        for (Checker c : board[0]) {
            if (c.getColor() == Color.EMPTY) return false;
        }
        return true;
    }

    /**
     * Resets the gameboard by removing all checkers
     */
    public void reset() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].setColor(Color.EMPTY);
            }
        }
    }

    /**
     * Returns the number of rows in this gameboard
     */
    public int nbrRows() {
        return board.length;
    }

    /**
     * Returns the number of columns in this gameboard
     */
    public int nbrColumns() {
        return board[0].length;
    }

    /**
     * Possible outcomes from playing a checker at a column.
     */
    public enum PlayResult {
        WIN, VALID_PLAY, TIE, INVALID_PLAY
    }
}

