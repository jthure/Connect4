package com.jthure.connect4.util;

import com.jthure.connect4.model.Color;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Utility class that maps playerNames to their selected color. Classes interested in changes of the
 * players colors should observe this class.
 */

public class PlayerColorMap extends Observable {
    private Map<String, Color> map;

    public PlayerColorMap() {
        map = new HashMap<>();
    }

    /**
     * Maps the specified color to the specified player name. If another player already has the color
     * that player will be assigned the old color of the player that selected the new color.
     * @param selectedPlayerName
     * @param color
     */
    public void put(String selectedPlayerName, Color color) {
        for (Map.Entry<String, Color> entry : map.entrySet()) {
            if (entry.getValue() == color) {
                map.put(entry.getKey(), map.put(selectedPlayerName, color));
            }
        }
        map.put(selectedPlayerName, color);
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the color of the specified player
     */
    public Color get(String player) {
        return map.get(player);
    }

    /**
     * Maps a color that is not mapped to any other player to the specified player
     * @param playerName The player to assign the color to
     * @throws MaxNumberPlayersReachedException If there are no available colors left
     */
    public void assignAvailableColor(String playerName) throws MaxNumberPlayersReachedException {
        for (Color c : Color.values()) {
            if (c != Color.EMPTY && !map.values().contains(c)) {
                map.put(playerName,c);
                return;
            }
        }
        throw new MaxNumberPlayersReachedException();
    }

    public class MaxNumberPlayersReachedException extends Exception {
        public MaxNumberPlayersReachedException() {
            super("Maximum number of players reached");
        }
    }
}
