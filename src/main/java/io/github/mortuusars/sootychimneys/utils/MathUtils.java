package io.github.mortuusars.sootychimneys.utils;

public class MathUtils {
    public static double clamp(double input, double minValue, double maxValue){
        return Math.max(minValue, Math.min(maxValue, input));
    }

    public static float clamp(float input, float minValue, float maxValue){
        return Math.max(minValue, Math.min(maxValue, input));
    }
}
