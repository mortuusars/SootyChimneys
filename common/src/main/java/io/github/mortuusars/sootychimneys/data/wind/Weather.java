package io.github.mortuusars.sootychimneys.data.wind;

import net.minecraft.world.level.Level;

public enum Weather {
    CLEAR,
    RAIN,
    THUNDER;

    public static Weather of(Level level){
        if (level.getThunderLevel(0) > 0.0f)
            return Weather.THUNDER;
        else if (level.getRainLevel(0) > 0.0f)
            return Weather.RAIN;
        else
            return Weather.CLEAR;
    }
}
