package io.github.mortuusars.sootychimneys.utils;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.util.RandomSource;

import java.util.Random;

public class RandomOffset {
    public static double offset(double input, double range){
        if (range <= 0.0)
            return input;

        return input + SootyChimneys.RANDOM.nextDouble(range * -1, range);
    }
}
