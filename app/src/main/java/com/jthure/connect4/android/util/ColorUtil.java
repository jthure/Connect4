package com.jthure.connect4.android.util;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Color;

/**
 * Created by Jonas on 2016-11-25.
 */

public class ColorUtil {
    public static int getImageResource(Color color) {
        switch (color) {
            case EMPTY:
                return R.drawable.checker_empty;
            case RED:
                return R.drawable.checker_red;
            case YELLOW:
                return R.drawable.checker_yellow;
            case GREEN:
                return R.drawable.checker_green;
            case ORANGE:
                return R.drawable.checker_orange;
            case PURPLE:
                return R.drawable.checker_purple;
            default:
                return -1;

        }

    }


}
