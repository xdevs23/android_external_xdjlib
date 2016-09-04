package org.xdevs23.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

public class ColorUtil {

    private ColorUtil() {
        
    }

    public static int getColor(@ColorRes int colres, Context context) {
        return ContextCompat.getColor(context, colres);
    }

    public static int getBrightnessBasedForeground(int color) {
        return isDarkBackground(color) ? Color.WHITE : Color.BLACK;
    }

    public static boolean isDarkBackground(int color) {
        float hsv[] = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[2] <= 0.56f || hsv[1] > 0.56f;
    }

    public static float[] colorToHSV(int color) {
        float hsv[] = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv;
    }

    public static float getHue(int color) {
        return colorToHSV(color)[0];
    }

    public static float getSaturation(int color) {
        return colorToHSV(color)[1];
    }

    public static float getValue(int color) {
        return colorToHSV(color)[2];
    }

}
