package com.jthure.connect4.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.util.PlayerColorMap;

import java.util.Observable;
import java.util.Observer;

/**
 * View that displays a selected player and this players selected color. It observes a {@link PlayerColorMap}
 * so that it can update changes in player colors accordingly
 */

public class SelectedPlayerView extends LinearLayout implements Observer {
    private PlayerColorMap playerColorMap;
    private Spinner colorSpinner;
    public SelectedPlayerView(Context context) {
        super(context);
    }

    public SelectedPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectedPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelectedPlayerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Creates a new view
     * @param inflater An inflater that inflates the view
     * @param playerName The name of the player that should be displayed
     * @param adapter an adapter used to provide color views to the color selection spinner
     * @param listener Listener that listens to selections in the color selection listener
     * @param playerColorMap A {@link PlayerColorMap} that holds the mappings of players and colors
     */
    public static SelectedPlayerView createView(LayoutInflater inflater, String playerName, SpinnerAdapter adapter, AdapterView.OnItemSelectedListener listener, PlayerColorMap playerColorMap){
        SelectedPlayerView v = (SelectedPlayerView)inflater.inflate(R.layout.view_selected_player_item,null);
        v.playerColorMap=playerColorMap;
        playerColorMap.addObserver(v);
        ((TextView)v.findViewById(R.id.player_name)).setText(playerName);
        Spinner colorSpinner = (Spinner)v.findViewById(R.id.spinner_player_color);
        v.colorSpinner=colorSpinner;
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(listener);
        colorSpinner.setTag(playerName);
        v.update(playerColorMap,null);
        return v;
    }

    @Override
    public void update(Observable observable, Object o) {
        SpinnerAdapter adapter = colorSpinner.getAdapter();
        for(int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).equals(playerColorMap.get((String)colorSpinner.getTag()))) {
                colorSpinner.setSelection(i);
                return;
            }
        }
    }
}
