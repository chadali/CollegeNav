package com.vappna.collegenav;

import android.content.res.Resources;
import android.graphics.Color;

/**
 * Created by vnair on 8/24/2015.
 */
public class Colors {
    private static final int GUILFORD_COLLEGE_COLOR = Color.rgb(183, 28, 28);
    private static final int GUILFORD_COLLEGE_COLOR_CLEAR = Color.argb(85, 183, 28, 28);
    private static final int UNCCH_COLOR_CLEAR = Color.argb(95, 153, 186, 221);
    private static final int UNCCH_COLOR = Color.rgb(153, 186, 221);

    public static int getGuilfordCollegeColor() {
        return GUILFORD_COLLEGE_COLOR;
    }

    public static int getGuilfordCollegeColorClear() {
        return GUILFORD_COLLEGE_COLOR_CLEAR;
    }

    public static int getUncchColorClear() {
        return UNCCH_COLOR_CLEAR;
    }

    public static int getUncchColor() {
        return UNCCH_COLOR;
    }

    public Colors(){

    }

}
