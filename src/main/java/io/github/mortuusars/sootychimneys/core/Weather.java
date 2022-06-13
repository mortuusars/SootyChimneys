package io.github.mortuusars.sootychimneys.core;

import net.minecraft.world.level.Level;

public enum Weather {
    CLEAR,
    RAIN,
    THUNDER;

    public static Weather of(Level level){
        if (level.oThunderLevel > 0.0f)
            return Weather.THUNDER;
        else if (level.oRainLevel > 0.0f)
            return Weather.RAIN;
        else
            return Weather.CLEAR;
    }
}
