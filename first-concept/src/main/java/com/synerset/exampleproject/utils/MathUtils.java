package com.synerset.exampleproject.utils;


public final class MathUtils {

    private MathUtils() {}

    public static double linearInterpolation(double x1, double f_x1, double x2, double f_x2, double x) {
        return f_x1 + ((x - x1) / (x2 - x1)) * (f_x2 - f_x1);
    }

}
