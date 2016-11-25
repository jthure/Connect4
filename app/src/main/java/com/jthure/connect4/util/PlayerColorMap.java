package com.jthure.connect4.util;

import com.jthure.connect4.model.Color;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Jonas on 2016-11-24.
 */

public class PlayerColorMap extends Observable {
    private Map<String, Color> map;

    public PlayerColorMap() {
        map = new HashMap<>();
    }

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

    public Color get(String player) {
        return map.get(player);
    }

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
