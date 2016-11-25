package com.jthure.connect4.android.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableRow;

import com.jthure.connect4.R;
import com.jthure.connect4.android.view.CheckerView;
import com.jthure.connect4.model.GameBoard;

/**
 * Created by Jonas on 2016-11-22.
 */

public class GameBoardRow extends TableRow{
    public GameBoardRow(Context context) {
        super(context);
    }

    public GameBoardRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoardRow(Context context, GameBoard board, int row) {
        super(context);
        setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
        setWeightSum(board.nbrColumns());
        LayoutInflater inflater = LayoutInflater.from(context);
        for(int col=0;col<board.nbrColumns();col++){
            CheckerView checkerView = CheckerView.createCheckerView(inflater,this,board.getChecker(row,col));
            addView(checkerView);
        }
    }
}
