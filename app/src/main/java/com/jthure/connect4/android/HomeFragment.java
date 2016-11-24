package com.jthure.connect4.android;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jthure.connect4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    //private IOimpl io;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //io=(IOimpl) getArguments().getSerializable(MainActivity.ARG_IO);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        v.findViewById(R.id.btn_new_game).setOnClickListener(this);
        ((ListView)v.findViewById(R.id.list_highscore)).setAdapter(new PlayerListAdapter(getContext(),MainActivity.io) );
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_new_game:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewGameFragment()).commit();
        }

    }
//    public static HomeFragment newInstance(IOimpl io){
//        Bundle args = new Bundle();
//        args.putSerializable(MainActivity.ARG_IO,io);
//        HomeFragment fragment = new HomeFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
}
