package com.jthure.connect4.util;

import com.jthure.connect4.model.Player;

import java.util.List;
import java.util.Observable;

/**
 * Abstract utility class to read and write persistent data. This class should be implemented by a
 * platform specific subclass. Classes that are interested in changes of the players should observe
 * this class.
 */

public abstract class IO extends Observable{
    protected static final String LOG_FILE="audit_log.txt";

    /**
     * Load the players that exists on the client
     * @return A list of players
     */
    public abstract List<Player> loadPlayers();

    /**
     * Get the score that is stored for the player with the specified name
     * @param playerName The name of the player
     * @return The score of the player
     */
    public abstract int getScore(String playerName);

    /**
     * Writes the information about the specified player to storage
     */
    public void writePlayer(Player player){
        writePlayerToStorage(player);
        setChanged();
        notifyObservers();
    }

    protected abstract void writePlayerToStorage(Player player);

    /**
     * Writes the specified message to the audit log file
     * @param msg The message to be logged
     */
    public abstract void log(String msg);
}
