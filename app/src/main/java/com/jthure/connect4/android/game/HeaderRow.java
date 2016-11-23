package com.jthure.connect4.android.game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableRow;

import com.jthure.connect4.model.Game;
import com.jthure.connect4.model.GameBoard;

/**
 * Created by Jonas on 2016-11-23.
 */

public class HeaderRow extends TableRow {
    public HeaderRow(Context context) {
        super(context);
    }

    public HeaderRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public HeaderRow(Context context, Game game, PlayColumnButton.OnColumnClickListener onColumnClickListener) {
        super(context);
        int nbrColumns = game.getGameBoard().nbrColumns();
        setWeightSum(nbrColumns);
        LayoutInflater inflater = LayoutInflater.from(context);
        for(int col=0;col<nbrColumns;col++){
            PlayColumnButton playColumnButton= PlayColumnButton.createButton(inflater,this,onColumnClickListener,game,col);
            addView(playColumnButton);
        }
    }
}
