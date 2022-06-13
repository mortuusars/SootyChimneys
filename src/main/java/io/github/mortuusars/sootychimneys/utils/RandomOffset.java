package io.github.mortuusars.sootychimneys.utils;

import java.util.Random;

public class RandomOffset {
    public static double offset(double input, double range, Random random){
        if (range <= 0.0)
            return input;

        return input + random.nextDouble(range * -1, range);
    }
}
