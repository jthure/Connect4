package com.jthure.connect4.android.game;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Checker;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jonas on 2016-11-22.
 */

public class CheckerView extends FrameLayout implements Observer{
    private static final String TAG = CheckerView.class.getSimpleName();
    private Checker checker;
    private ImageView checkerImage;

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void update(Observable observable, Object o) {
        switch (checker.getColor()) {
            case EMPTY:
                checkerImage.setImageResource(R.drawable.checker_empty);
                break;
            case RED:
                checkerImage.setImageResource(R.drawable.checker_red);
                break;
            case YELLOW:
                checkerImage.setImageResource(R.drawable.checker_yellow);
                break;
        }
    }




    public static CheckerView createCheckerView(LayoutInflater inflater, ViewGroup root, Checker checker) {
        CheckerView checkerView = (CheckerView) inflater.inflate(R.layout.view_checker, root, false);
        checker.addObserver(checkerView);
        checkerView.checker = checker;
        checkerView.checkerImage = (ImageView)checkerView.findViewById(R.id.checker_view_image);
        return checkerView;
    }

}
