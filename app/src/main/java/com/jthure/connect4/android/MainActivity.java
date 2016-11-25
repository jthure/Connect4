package com.jthure.connect4.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jthure.connect4.R;
import com.jthure.connect4.android.fragment.HomeFragment;
import com.jthure.connect4.util.IO;

public class MainActivity extends AppCompatActivity {

    public static final String ARG_IO="arg_io";
    public static IO io;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        io = new IOimpl(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }
}
