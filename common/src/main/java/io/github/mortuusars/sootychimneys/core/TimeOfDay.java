package io.github.mortuusars.sootychimneys.core;

import net.minecraft.world.level.Level;

public enum TimeOfDay {
    MORNING,
    DAY,
    EVENING,
    NIGHT;

    public static TimeOfDay fromSunAngle(double degrees){
        if (degrees <= 290 && degrees > 270)
            return TimeOfDay.MORNING;
        else if (degrees <= 270 && degrees > 95)
            return TimeOfDay.NIGHT;
        else if (degrees <= 95 && degrees > 75)
            return TimeOfDay.EVENING;
        else
            return TimeOfDay.DAY;
    }

    public static TimeOfDay of(Level level){
        return fromSunAngle((level.getSunAngle(level.getDayTime()) * 180/Math.PI));
    }
}
