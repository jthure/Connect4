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

public class PlayerSpinnerAdapter extends PlayerAdapter {
    public PlayerSpinnerAdapter(Context context, IO io) {
        super(context, io);
    }

    @Override
    protected Comparator<Player> comparator() {
        return Player.nameComparator();
    }

    @Override
    protected View itemView(int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_spinner_player_select_item,null);
        ((TextView)v.findViewById(R.id.player_name)).setText(players.get(i).getName());
        return v;
    }
}
