package com.jthure.connect4.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableRow;

import com.jthure.connect4.model.Game;

/**
 * An header row consisting of buttons, one for each column, used to make a play at that column.
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
