package com.jthure.connect4.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Game;
import com.jthure.connect4.model.GameBoard;

import java.util.Observable;
import java.util.Observer;

/**
 * The text title that shows which of the players turn it is to make a play, or the result if the
 * game has finished.
 */

public class GameTitleTextView extends TextView implements Observer, Game.FinishListener {
    private Game game;

    public GameTitleTextView(Context context) {
        super(context);
    }

    public GameTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameTitleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void update(Observable observable, Object o) {
        String s=game.getCurrentPlayer().getName()+getResources().getString(R.string.tv_game_title_turn);
        setText(s);
    }
    public void observeGame(Game game){
        this.game=game;
        game.addObserver(this);
        update(game,null);
    }

    @Override
    public void onGameFinished(GameBoard.PlayResult result) {
        String s;
        if(result== GameBoard.PlayResult.WIN)
            s=game.getCurrentPlayer().getName()+getResources().getString(R.string.tv_game_title_won);
        else
            s=getResources().getString(R.string.tv_game_title_tie);
        setText(s);
    }
}
