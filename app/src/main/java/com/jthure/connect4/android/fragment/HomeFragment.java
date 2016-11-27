package com.jthure.connect4.android.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jthure.connect4.R;
import com.jthure.connect4.android.MainActivity;
import com.jthure.connect4.android.view.adapter.PlayerListAdapter;

/**
 * A {@link Fragment} that is first fragment to be shown when th app starts. It contains the highscore
 * list.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        v.findViewById(R.id.btn_new_game).setOnClickListener(this);
        ((ListView)v.findViewById(R.id.list_highscore)).setAdapter(new PlayerListAdapter(getContext(), MainActivity.io) );
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_new_game:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewGameFragment()).addToBackStack(null).commit();
        }

    }
}
