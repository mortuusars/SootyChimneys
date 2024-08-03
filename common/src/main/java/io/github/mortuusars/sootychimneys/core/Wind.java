package io.github.mortuusars.sootychimneys.core;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class Wind {
    private final static WindData wind = new WindData(0, 0f);

    public static WindData getWind() {
        return wind;
    }

    public static void update(Level level) {
        RandomSource random = level.random;
        double addDegrees = Math.exp(random.nextDouble() * 2.5f) * (random.nextBoolean() ? -1 : 1);
        wind.update(addDegrees, getWindStrengthChange(level) * 0.5f);
    }

    private static float getWindStrengthChange(Level level) {
        final TimeOfDay timeOfDay = TimeOfDay.of(level);
        final Weather weather = Weather.of(level);
        final RandomSource random = level.getRandom();

        if (weather == Weather.THUNDER)
            return random.nextFloat() * 0.2f - 0.1f + Math.max(0.0f, 0.4f - wind.getStrength());
        else if (weather == Weather.RAIN)
            return random.nextFloat() * 0.18f - 0.09f + Math.max(0.0f, 0.2f - wind.getStrength()) - Math.max(0.0f, wind.getStrength() - 0.6f);
        else if (timeOfDay != TimeOfDay.DAY)
            return random.nextFloat() * 0.08f - 0.04f + Math.max(0.0f, 0.008f - wind.getStrength()) - Math.max(0.0f, wind.getStrength() - 0.2f);
        else
            return random.nextFloat() * 0.1f - 0.05f + Math.max(0.0f, 0.008f - wind.getStrength()) - Math.max(0.0f, wind.getStrength() - 0.3f);
    }
}
