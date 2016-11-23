package com.jthure.connect4.android;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jthure.connect4.R;
import com.jthure.connect4.android.game.CheckerView;
import com.jthure.connect4.android.game.GameBoardTable;
import com.jthure.connect4.android.game.PlayColumnButton;
import com.jthure.connect4.model.Color;
import com.jthure.connect4.model.Game;
import com.jthure.connect4.model.Player;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements PlayColumnButton.OnColumnClickListener, Game.WinListener {
    private Game game;


    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        game = new Game(7,6,this);
        Player jonas = new Player("Jonas");
        jonas.setColor(Color.X);
        Player amanda = new Player("Amanda");
        amanda.setColor(Color.O);
        game.addPlayer(jonas);
        game.addPlayer(amanda);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        ((GameBoardTable)v.findViewById(R.id.game_board_table)).setUp(game,this);
        return v;
    }

    @Override
    public void onColumnClick(int column) {
        game.playColumn(column);
    }

    @Override
    public void onPlayerWon() {
        Toast.makeText(getContext(),game.getCurrentPlayer().getName(),Toast.LENGTH_LONG).show();
    }
}
