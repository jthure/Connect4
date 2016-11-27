package com.jthure.connect4.android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jthure.connect4.R;
import com.jthure.connect4.android.view.adapter.ColorSpinnerAdapter;
import com.jthure.connect4.android.MainActivity;
import com.jthure.connect4.android.view.adapter.PlayerSpinnerAdapter;
import com.jthure.connect4.android.view.SelectedPlayerView;
import com.jthure.connect4.model.Color;
import com.jthure.connect4.model.Player;
import com.jthure.connect4.util.PlayerColorMap;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, NewPlayerDialogFragment.Callback {
    private static final String TAG = NewGameFragment.class.getSimpleName();

    private Spinner playerSpinner;
    private List<String> selectedPlayers;
    private LinearLayout selectedPlayersView;
    private PlayerColorMap playerColors;

    private ColorSpinnerAdapter colorSpinnerAdapter;
    private Spinner rows;
    private Spinner columns;

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
        rows = (Spinner) v.findViewById(R.id.spinner_nbr_rows);
        columns = (Spinner) v.findViewById(R.id.spinner_nbr_columns);
        rows.setSelection(2);
        columns.setSelection(3);
        v.findViewById(R.id.btn_start_game).setOnClickListener(this);
        v.findViewById(R.id.btn_add_player).setOnClickListener(this);
        v.findViewById(R.id.btn_new_player).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_game:
                if (selectedPlayers.size() < 2) {
                    Toast.makeText(getContext(), "At least 2 players must be selected", Toast.LENGTH_SHORT).show();
                    break;
                }
                String[] selectedPlayersArray = new String[selectedPlayers.size()];
                for (int i = 0; i < selectedPlayers.size(); i++) {
                    selectedPlayersArray[i] = selectedPlayers.get(i);
                }
                GameFragment gameFragment = GameFragment.newInstance(
                        selectedPlayersArray,
                        playerColors,
                        Integer.parseInt((String) rows.getSelectedItem()),
                        Integer.parseInt((String) columns.getSelectedItem()));

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, gameFragment).addToBackStack(null).commit();
                break;
            case R.id.btn_add_player:
                String selectedPlayerName = ((Player) playerSpinner.getSelectedItem()).getName();
                if (!selectedPlayers.contains(selectedPlayerName)) {
                    try {
                        playerColors.assignAvailableColor(selectedPlayerName);
                        selectedPlayers.add(selectedPlayerName);
                        selectedPlayersView.addView(SelectedPlayerView.createView(LayoutInflater.from(getContext()), selectedPlayerName, colorSpinnerAdapter, this, playerColors));
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Maximal number of players reached", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
                break;
            case R.id.btn_new_player:
                NewPlayerDialogFragment dialog = new NewPlayerDialogFragment();
                dialog.setCallback(this);
                dialog.show(getActivity().getSupportFragmentManager(), "dialog");
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        playerColors.put((String) adapterView.getTag(), (Color) adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onNewPlayer(String name) {
        MainActivity.io.writePlayer(new Player(name));
    }

}
