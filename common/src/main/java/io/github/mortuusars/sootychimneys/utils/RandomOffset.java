package io.github.mortuusars.sootychimneys.utils;

import java.util.Random;

public class RandomOffset {
    public static final Random RANDOM = new Random();

    public static double offset(double input, double range){
        if (range <= 0.0)
            return input;

        return input + RANDOM.nextDouble(range * -1, range);
    }
}
