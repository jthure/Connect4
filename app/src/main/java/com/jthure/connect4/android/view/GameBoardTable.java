package com.jthure.connect4.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

import com.jthure.connect4.model.Game;
import com.jthure.connect4.model.GameBoard;

/**
 * Created by Jonas on 2016-11-22.
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
    public void setUp(Game game, PlayColumnButton.OnColumnClickListener onColumnClickListener) {
        addView(new HeaderRow(context,game,onColumnClickListener));
        GameBoard board=game.getGameBoard();
        for(int row=0;row<board.nbrRows();row++){
            addView(new GameBoardRow(context, board,row));
        }
    }
}
