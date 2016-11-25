package com.jthure.connect4.util;

import com.jthure.connect4.model.Player;

import java.util.List;
import java.util.Observable;

/**
 * Created by Jonas on 2016-11-24.
 */

public abstract class IO extends Observable{
    protected static final String LOG_FILE="audit_log.txt";
    public abstract List<Player> loadPlayers();
    public abstract int getScore(String playerName);
    public void writePlayer(Player player){
        writePlayerToStorage(player);
        setChanged();
        notifyObservers();
    }

    protected abstract void writePlayerToStorage(Player player);

    public abstract void log(String msg);
}
