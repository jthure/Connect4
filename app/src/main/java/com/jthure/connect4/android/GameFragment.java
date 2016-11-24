package com.jthure.connect4.android;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jthure.connect4.R;
import com.jthure.connect4.android.game.GameBoardTable;
import com.jthure.connect4.android.game.PlayColumnButton;
import com.jthure.connect4.model.Color;
import com.jthure.connect4.model.Game;
import com.jthure.connect4.model.Player;
import com.jthure.connect4.util.PlayerColorMap;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements PlayColumnButton.OnColumnClickListener, Game.WinListener {
    private static final String ARG_PLAYERS="arg_players";
    private static final String ARG_ROWS="arg_rows";
    private static final String ARG_COLUMNS="arg_columns";
    private static final String ARG_COLOR="arg_color_";


    private Game game;


    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance(String[] players, PlayerColorMap selectedColors, int rows, int columns){
        Bundle args = new Bundle();
        args.putStringArray(ARG_PLAYERS,players);
        args.putInt(ARG_ROWS,rows);
        args.putInt(ARG_COLUMNS,columns);
        for(String player: players){
            args.putSerializable(ARG_COLOR+player,selectedColors.get(player));
        }
        GameFragment fragment=new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        String[] playerNames = getArguments().getStringArray(ARG_PLAYERS);
        Player[] players = new Player[playerNames.length];
        for(int i =0;i<playerNames.length;i++){
            players[i]=new Player(playerNames[i],MainActivity.io.getScore(playerNames[i]));
            players[i].setColor((Color)getArguments().getSerializable(ARG_COLOR+playerNames[i]));
        }
        game = new Game(getArguments().getInt(ARG_ROWS),getArguments().getInt(ARG_COLUMNS),players,this,MainActivity.io);
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
