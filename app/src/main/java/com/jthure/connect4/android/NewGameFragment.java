package com.jthure.connect4.android;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Color;
import com.jthure.connect4.model.Player;
import com.jthure.connect4.util.PlayerColorMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner playerSpinner;
    private List<String> selectedPlayers;
    private LinearLayout selectedPlayersView;
    private PlayerColorMap playerColors;

    private ColorSpinnerAdapter colorSpinnerAdapter;

    public NewGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        playerColors = new PlayerColorMap();
        selectedPlayers = new LinkedList<>();
        super.onCreate(savedInstanceState);
        colorSpinnerAdapter = new ColorSpinnerAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_game, container, false);
        playerSpinner = (Spinner) v.findViewById(R.id.spinner_player_selection);
        playerSpinner.setAdapter(new PlayerSpinnerAdapter(getContext(), MainActivity.io));
        selectedPlayersView = (LinearLayout) v.findViewById(R.id.selected_players);
        v.findViewById(R.id.btn_start_game).setOnClickListener(this);
        v.findViewById(R.id.btn_add_player).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_game:
                String[] selectedPlayersArray = new String[selectedPlayers.size()];
                for (int i = 0; i < selectedPlayers.size(); i++) {
                    selectedPlayersArray[i] = selectedPlayers.get(i);
                }
                GameFragment gameFragment = GameFragment.newInstance(selectedPlayersArray, playerColors, 6, 7);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, gameFragment).commit();
                break;
            case R.id.btn_add_player:
                String selectedPlayerName = ((Player) playerSpinner.getSelectedItem()).getName();
                if (!selectedPlayers.contains(selectedPlayerName)) {
                    try {
                        Color color = getAvailableColor();
                        playerColors.put(selectedPlayerName, color);
                        selectedPlayers.add(selectedPlayerName);
                        selectedPlayersView.addView(SelectedPlayerView.createView(LayoutInflater.from(getContext()), selectedPlayerName, colorSpinnerAdapter, this,playerColors));
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Maximal number of players reached", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
                break;

        }
    }

    private Color getAvailableColor() throws Exception {
        for (Color c : Color.values()) {
            if (c != Color.EMPTY && !playerColors.values().contains(c)) {
                return c;
            }
        }
        throw new Exception("Maximal number of players reached");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
