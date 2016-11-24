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
    private Map<String,Color> map;
    public PlayerColorMap(){
        map=new HashMap<>();
    }

    public void put(String selectedPlayerName, Color color) {
        map.put(selectedPlayerName,color);
    }

    public Color get(String player) {
        return map.get(player);
    }

    public Collection values() {
        return map.values();
    }
}
