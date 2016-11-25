package com.jthure.connect4.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Game;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jonas on 2016-11-25.
 */

public class GameTitleTextView extends TextView implements Observer, Game.WinListener {
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
    public void observerGame(Game game){
        this.game=game;
        game.addObserver(this);
        update(game,null);
    }

    @Override
    public void onPlayerWon() {
        String s=game.getCurrentPlayer().getName()+getResources().getString(R.string.tv_game_title_won);
        setText(s);
    }
}
