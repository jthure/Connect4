package com.jthure.connect4.android.util;

import com.jthure.connect4.R;
import com.jthure.connect4.model.Color;

/**
 * Utility class used to determine the drawable to be used for a {@link Color}
 */

public class ColorUtil {
    /**
     * Determine the drawable to be used for a the specified color
     * @param color the color that a drawable is needed for
     * @return the drawable corresponding to the specified color
     */
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
