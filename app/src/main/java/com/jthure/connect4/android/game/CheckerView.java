package com.jthure.connect4.android.game;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Checker;
import com.jthure.connect4.model.Color;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jonas on 2016-11-22.
 */

public class CheckerView extends TextView implements Observer{
    private static final String TAG = CheckerView.class.getSimpleName();
    private Checker checker;

    public CheckerView(Context context) {
        super(context);
    }

    public CheckerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CheckerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void update(Observable observable, Object o) {
        switch (checker.getColor()) {
            case EMPTY:
                setText("Empty");
                break;
            case X:
                setText("X");
                break;
            case O:
                setText("O");
                break;
        }
    }




    public static CheckerView createCheckerView(LayoutInflater inflater, ViewGroup root, Checker checker, int column) {
        CheckerView checkerView = (CheckerView) inflater.inflate(R.layout.view_checker, root, false);
        checker.addObserver(checkerView);
        checkerView.checker = checker;
        return checkerView;
    }

}
