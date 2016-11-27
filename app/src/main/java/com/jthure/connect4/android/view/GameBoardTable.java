package com.jthure.connect4.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

import com.jthure.connect4.model.Game;
import com.jthure.connect4.model.GameBoard;

/**
 * A view representing the GameBoard.
 */

public class GameBoardTable extends TableLayout {
    private Context context;
    public GameBoardTable(Context context) {
        super(context);
        this.context=context;
    }

    public GameBoardTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

    }

    /**
     * Creates the row views that this view consists of. The first row is not part of the GameBoard
     * but a row with controls for playing a checker at each column
     * @param game A {@link Game} that this view should display
     * @param onColumnClickListener A listener that listens for clicks that indicate that a row has
     *                              been played
     */
    public void setUp(Game game, PlayColumnButton.OnColumnClickListener onColumnClickListener) {
        addView(new HeaderRow(context,game,onColumnClickListener));
        GameBoard board=game.getGameBoard();
        for(int row=0;row<board.nbrRows();row++){
            addView(new GameBoardRow(context, board,row));
        }
    }
}
