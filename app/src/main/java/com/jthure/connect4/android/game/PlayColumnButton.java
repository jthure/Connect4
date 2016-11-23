package com.jthure.connect4.android.game;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Game;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jonas on 2016-11-23.
 */

public class PlayColumnButton extends TextView implements Observer, View.OnClickListener{
    private int column;
    private OnColumnClickListener onColumnClickListener;
    private Game game;

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

    public static PlayColumnButton createButton(LayoutInflater inflater, ViewGroup root, OnColumnClickListener onColumnClickListener, Game game , int column) {
        PlayColumnButton button = (PlayColumnButton)inflater.inflate(R.layout.view_play_column_button,root,false);
        button.setOnClickListener(button);
        button.column=column;
        button.onColumnClickListener=onColumnClickListener;
        button.game=game;
        game.addObserver(button);
        button.update(game,null);
        return button;
    }

    @Override
    public void onClick(View view) {
        onColumnClickListener.onColumnClick(column);
    }

    @Override
    public void update(Observable observable, Object o) {
        switch(game.getCurrentPlayer().getColor()){
            case X:setText("X");break;
            case O:setText("O");break;
        }
    }

    public interface OnColumnClickListener {
        void onColumnClick(int column);
    }
}
