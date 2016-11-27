package com.jthure.connect4.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Game;

/**
 * Button that is related to a column in a {@link com.jthure.connect4.model.GameBoard}. When clicked
 * it notifies its {@link OnColumnClickListener}
 */

public class PlayColumnButton extends FrameLayout implements  View.OnClickListener {
    private int column;
    private OnColumnClickListener onColumnClickListener;

    public PlayColumnButton(Context context) {
        super(context);
    }

    public PlayColumnButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayColumnButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayColumnButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static PlayColumnButton createButton(LayoutInflater inflater, ViewGroup root, OnColumnClickListener onColumnClickListener, Game game, int column) {
        PlayColumnButton button = (PlayColumnButton) inflater.inflate(R.layout.view_play_column_button, root, false);
        button.setOnClickListener(button);
        button.column = column;
        button.onColumnClickListener = onColumnClickListener;
        CheckerView checkerView=CheckerView.createCheckerView(inflater,button,game.getTurnChecker());
        button.addView(checkerView);
        return button;
    }

    @Override
    public void onClick(View view) {
        onColumnClickListener.onColumnClick(column);
    }

    /**
     * Listner interface for the {@link PlayColumnButton}
     */
    public interface OnColumnClickListener {
        /**
         * Called when the button corresponding to the specified column is clicked
         */
        void onColumnClick(int column);
    }
}
