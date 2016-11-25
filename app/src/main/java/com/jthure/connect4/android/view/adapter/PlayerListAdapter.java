package com.jthure.connect4.android.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.util.IO;
import com.jthure.connect4.model.Player;

import java.util.Comparator;

/**
 * Created by Jonas on 2016-11-24.
 */

public class PlayerListAdapter extends PlayerAdapter {
    public PlayerListAdapter(Context context, IO io) {
        super(context, io);
    }

    @Override
    protected Comparator<Player> comparator() {
        return Player.scoreComparator();
    }

    @Override
    protected View itemView(int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_highscore_player_list_item,null);
        ((TextView)v.findViewById(R.id.player_name)).setText(players.get(i).getName());
        ((TextView)v.findViewById(R.id.player_score)).setText(String.valueOf(players.get(i).getWins()));
        return v;
    }
}
