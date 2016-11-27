package com.jthure.connect4.android.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jthure.connect4.util.IO;
import com.jthure.connect4.model.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Abstract adapter responsible to provide views of existing players. It observes the {@link IO} so
 * that any changes in the player storage can be updated accordingly.
 */

public abstract class PlayerAdapter extends BaseAdapter implements Observer {
    private IO io;
    protected List<Player> players;
    protected Context context;

    public PlayerAdapter(Context context, IO io){
        this.io = io;
        io.addObserver(this);
        this.context=context;
        update(io,null);
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return itemView(i);
    }

    @Override
    public void update(Observable observable, Object o) {
        players=io.loadPlayers();
        Collections.sort(players,comparator());
        notifyDataSetChanged();
    }

    /**
     * Returns the {@link Comparator} to use when sorting the players
     */
    protected abstract Comparator<Player> comparator();

    /**
     * Returns the View of the color at position i, that the adapter should return.
     */
    protected abstract View itemView(int i);


}
