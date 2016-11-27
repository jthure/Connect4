package com.jthure.connect4.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jthure.connect4.R;
import com.jthure.connect4.android.util.ColorUtil;
import com.jthure.connect4.model.Checker;

import java.util.Observable;
import java.util.Observer;

/**
 * View that displays the color of a checker as an oval shape. It observes {@link Checker} and
 * changes the displayed color accordingly
 */

public class CheckerView extends FrameLayout implements Observer {
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
        checkerImage.setImageResource(ColorUtil.getImageResource(checker.getColor()));
    }

    /**
     * Creates a new view.
     * @param inflater An inflater that inflates the view
     * @param root The parent view that provides layout params
     * @param checker The {@link Checker} that this view should observer
     * @return
     */
    public static CheckerView createCheckerView(LayoutInflater inflater, ViewGroup root, Checker checker) {
        CheckerView checkerView = (CheckerView) inflater.inflate(R.layout.view_checker, root, false);
        checker.addObserver(checkerView);
        checkerView.checker = checker;
        checkerView.checkerImage = (ImageView) checkerView.findViewById(R.id.checker_view_image);
        checkerView.update(checker, null);
        return checkerView;
    }

}
